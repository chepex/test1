package com.entities;

import com.entities.Horarios;
import com.entities.HorariosFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class HorariosConverter implements Converter {

    @EJB
    private HorariosFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.HorariosPK getKey(String value) {
        com.entities.HorariosPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.HorariosPK();
        key.setCodCia(Short.parseShort(values[0]));
        key.setCodHorario(Short.parseShort(values[1]));
        key.setCodDepto(Short.parseShort(values[2]));
        return key;
    }

    String getStringKey(com.entities.HorariosPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodCia());
        sb.append(SEPARATOR);
        sb.append(value.getCodHorario());
        sb.append(SEPARATOR);
        sb.append(value.getCodDepto());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Horarios) {
            Horarios o = (Horarios) object;
            return getStringKey(o.getHorariosPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Horarios.class.getName()});
            return null;
        }
    }
}
