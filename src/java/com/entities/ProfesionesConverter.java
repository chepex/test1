package com.entities;

import com.entities.Profesiones;
import com.entities.ProfesionesFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class ProfesionesConverter implements Converter {

    @EJB
    private ProfesionesFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
	    return null;
	}
	return this.ejbFacade.find(getKey(value));
    }

    java.lang.Short getKey(String value) {
	java.lang.Short key;
	key = Short.valueOf(value);
	return key;
    }

    String getStringKey(java.lang.Short value) {
	StringBuffer sb = new StringBuffer();
	sb.append(value);
	return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
	if (object == null
		|| (object instanceof String && ((String) object).length() == 0)) {
	    return null;
	}
	if (object instanceof Profesiones) {
	    Profesiones o = (Profesiones) object;
	    return getStringKey(o.getCodProfesion());
	} else {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Profesiones.class.getName()});
	    return null;
	}
    }
}
