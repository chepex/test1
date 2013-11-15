package com.entities;

import com.entities.MovDp;
import com.entities.MovDpFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class MovDpConverter implements Converter {

    @EJB
    private MovDpFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
	    return null;
	}
	return this.ejbFacade.find(getKey(value));
    }

    com.entities.MovDpPK getKey(String value) {
	com.entities.MovDpPK key;
	String values[] = value.split(SEPARATOR_ESCAPED);
	key = new com.entities.MovDpPK();
	key.setCodCia(Short.parseShort(values[0]));
	key.setSecuencia(Long.parseLong(values[1]));
	key.setCodEmp(Integer.parseInt(values[2]));
	key.setCodDp(Short.parseShort(values[3]));
	return key;
    }

    String getStringKey(com.entities.MovDpPK value) {
	StringBuffer sb = new StringBuffer();
	sb.append(value.getCodCia());
	sb.append(SEPARATOR);
	sb.append(value.getSecuencia());
	sb.append(SEPARATOR);
	sb.append(value.getCodEmp());
	sb.append(SEPARATOR);
	sb.append(value.getCodDp());
	return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
	if (object == null
		|| (object instanceof String && ((String) object).length() == 0)) {
	    return null;
	}
	if (object instanceof MovDp) {
	    MovDp o = (MovDp) object;
	    return getStringKey(o.getMovDpPK());
	} else {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MovDp.class.getName()});
	    return null;
	}
    }
}
