package com.entities;

import com.entities.Observaciones;
import com.entities.ObservacionesFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class ObservacionesConverter implements Converter {

    @EJB
    private ObservacionesFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.ObservacionesPK getKey(String value) {
        com.entities.ObservacionesPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.ObservacionesPK();
        key.setCodCia(Short.parseShort(values[0]));
        key.setId(Short.parseShort(values[1]));
        return key;
    }

    String getStringKey(com.entities.ObservacionesPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodCia());
        sb.append(SEPARATOR);
        sb.append(value.getId());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Observaciones) {
            Observaciones o = (Observaciones) object;
            return getStringKey(o.getObservacionesPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Observaciones.class.getName()});
            return null;
        }
    }
}
