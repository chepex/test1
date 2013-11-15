package com.entities;

import com.entities.OtrosMovDp;
import com.entities.OtrosMovDpFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class OtrosMovDpConverter implements Converter {

    @EJB
    private OtrosMovDpFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
	    return null;
	}
	return this.ejbFacade.find(getKey(value));
    }

    com.entities.OtrosMovDpPK getKey(String value) {
	com.entities.OtrosMovDpPK key;
	String values[] = value.split(SEPARATOR_ESCAPED);
	key = new com.entities.OtrosMovDpPK();
	key.setCodCia(Short.parseShort(values[0]));
	key.setSecuencia(Long.parseLong(values[1]));
	key.setCodEmp(Integer.parseInt(values[2]));
	key.setCodDp(Short.parseShort(values[3]));
	return key;
    }

    String getStringKey(com.entities.OtrosMovDpPK value) {
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
	if (object instanceof OtrosMovDp) {
	    OtrosMovDp o = (OtrosMovDp) object;
	    return getStringKey(o.getOtrosMovDpPK());
	} else {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), OtrosMovDp.class.getName()});
	    return null;
	}
    }
}
