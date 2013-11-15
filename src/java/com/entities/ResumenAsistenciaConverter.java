package com.entities;

import com.entities.ResumenAsistencia;
import com.entities.ResumenAsistenciaFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class ResumenAsistenciaConverter implements Converter {

    @EJB
    private ResumenAsistenciaFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
	    return null;
	}
	return this.ejbFacade.find(getKey(value));
    }

    com.entities.ResumenAsistenciaPK getKey(String value) {
	com.entities.ResumenAsistenciaPK key;
	String values[] = value.split(SEPARATOR_ESCAPED);
	key = new com.entities.ResumenAsistenciaPK();
	key.setCodCia(Short.parseShort(values[0]));
	key.setSecuencia(Long.parseLong(values[1]));
	key.setCodEmp(Integer.parseInt(values[2]));
	return key;
    }

    String getStringKey(com.entities.ResumenAsistenciaPK value) {
	StringBuffer sb = new StringBuffer();
	sb.append(value.getCodCia());
	sb.append(SEPARATOR);
	sb.append(value.getSecuencia());
	sb.append(SEPARATOR);
	sb.append(value.getCodEmp());
	return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
	if (object == null
		|| (object instanceof String && ((String) object).length() == 0)) {
	    return null;
	}
	if (object instanceof ResumenAsistencia) {
	    ResumenAsistencia o = (ResumenAsistencia) object;
	    return getStringKey(o.getResumenAsistenciaPK());
	} else {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ResumenAsistencia.class.getName()});
	    return null;
	}
    }
}
