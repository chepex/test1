package com.entities;

import com.entities.Prestamos;
import com.entities.PrestamosFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class PrestamosConverter implements Converter {

    @EJB
    private PrestamosFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
	    return null;
	}
	return this.ejbFacade.find(getKey(value));
    }

    com.entities.PrestamosPK getKey(String value) {
	com.entities.PrestamosPK key;
	String values[] = value.split(SEPARATOR_ESCAPED);
	key = new com.entities.PrestamosPK();
	key.setCodCia(Short.parseShort(values[0]));
	key.setCodEmp(Integer.parseInt(values[1]));
	key.setCodDp(Short.parseShort(values[2]));
	key.setCodPresta(Short.parseShort(values[3]));
	return key;
    }

    String getStringKey(com.entities.PrestamosPK value) {
	StringBuffer sb = new StringBuffer();
	sb.append(value.getCodCia());
	sb.append(SEPARATOR);
	sb.append(value.getCodEmp());
	sb.append(SEPARATOR);
	sb.append(value.getCodDp());
	sb.append(SEPARATOR);
	sb.append(value.getCodPresta() );
	return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
	if (object == null
		|| (object instanceof String && ((String) object).length() == 0)) {
	    return null;
	}
	if (object instanceof Prestamos) {
	    Prestamos o = (Prestamos) object;
	    return getStringKey(o.getPrestamosPK());
	} else {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Prestamos.class.getName()});
	    return null;
	}
    }
}
