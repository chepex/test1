package com.entities;

import com.entities.Renta;
import com.entities.RentaFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class RentaConverter implements Converter {

    @EJB
    private RentaFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.RentaPK getKey(String value) {
        com.entities.RentaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.RentaPK();
        key.setCodCia(Short.parseShort(values[0]));
        key.setId(Short.parseShort(values[1]));
        key.setSecuencia(Short.parseShort(values[2]));
        return key;
    }

    String getStringKey(com.entities.RentaPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodCia());
        sb.append(SEPARATOR);
        sb.append(value.getId());
        sb.append(SEPARATOR);
        sb.append(value.getSecuencia());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Renta) {
            Renta o = (Renta) object;
            return getStringKey(o.getRentaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Renta.class.getName()});
            return null;
        }
    }
}
