package com.entities;

import com.entities.NivelesSeguridad;
import com.entities.NivelesSeguridadFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class NivelesSeguridadConverter implements Converter {

    @EJB
    private NivelesSeguridadFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.NivelesSeguridadPK getKey(String value) {
        com.entities.NivelesSeguridadPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.NivelesSeguridadPK();
        key.setCodSeguridad(Short.parseShort(values[0]));
        key.setCodCia(Short.parseShort(values[1]));
        return key;
    }

    String getStringKey(com.entities.NivelesSeguridadPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodSeguridad());
        sb.append(SEPARATOR);
        sb.append(value.getCodCia());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof NivelesSeguridad) {
            NivelesSeguridad o = (NivelesSeguridad) object;
            return getStringKey(o.getNivelesSeguridadPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), NivelesSeguridad.class.getName()});
            return null;
        }
    }
}
