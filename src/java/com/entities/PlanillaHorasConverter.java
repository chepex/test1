package com.entities;

import com.entities.PlanillaHoras;
import com.entities.PlanillaHorasFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class PlanillaHorasConverter implements Converter {

    @EJB
    private PlanillaHorasFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
	    return null;
	}
	return this.ejbFacade.find(getKey(value));
    }

    com.entities.PlanillaHorasPK getKey(String value) {
	com.entities.PlanillaHorasPK key;
	String values[] = value.split(SEPARATOR_ESCAPED);
	key = new com.entities.PlanillaHorasPK();
	key.setSecuencia(Long.parseLong(values[0]));
	key.setCodCia(Short.parseShort(values[1]));
	key.setCodEmp(Integer.parseInt(values[2]));
	key.setCodDp(Short.parseShort(values[3]));
	return key;
    }

    String getStringKey(com.entities.PlanillaHorasPK value) {
	StringBuffer sb = new StringBuffer();
	sb.append(value.getSecuencia());
	sb.append(SEPARATOR);
	sb.append(value.getCodCia());
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
	if (object instanceof PlanillaHoras) {
	    PlanillaHoras o = (PlanillaHoras) object;
	    return getStringKey(o.getPlanillaHorasPK());
	} else {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PlanillaHoras.class.getName()});
	    return null;
	}
    }
}
