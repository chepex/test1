package com.entities;

import com.entities.TiposPlanilla;
import com.entities.TiposPlanillaFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TiposPlanillaConverter implements Converter {

    @EJB
    private TiposPlanillaFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
	    return null;
	}
	return this.ejbFacade.find(getKey(value));
    }

    com.entities.TiposPlanillaPK getKey(String value) {
	com.entities.TiposPlanillaPK key;
	String values[] = value.split(SEPARATOR_ESCAPED);
	key = new com.entities.TiposPlanillaPK();
	key.setCodCia(Short.parseShort(values[0]));
	key.setCodTipopla(Short.parseShort(values[1]));
	return key;
    }

    String getStringKey(com.entities.TiposPlanillaPK value) {
	StringBuffer sb = new StringBuffer();
	sb.append(value.getCodCia());
	sb.append(SEPARATOR);
	sb.append(value.getCodTipopla());
	return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
	if (object == null
		|| (object instanceof String && ((String) object).length() == 0)) {
	    return null;
	}
	if (object instanceof TiposPlanilla) {
	    TiposPlanilla o = (TiposPlanilla) object;
	    return getStringKey(o.getTiposPlanillaPK());
	} else {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TiposPlanilla.class.getName()});
	    return null;
	}
    }
}
