package com.entities;

import com.entities.Deptos;
import com.entities.DeptosFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class DeptosConverter implements Converter {

    @EJB
    private DeptosFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.DeptosPK getKey(String value) {
        com.entities.DeptosPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.DeptosPK();
        key.setCodPais(Short.parseShort(values[0]));
        key.setCodDepto(Short.parseShort(values[1]));
        key.setZona(Integer.parseInt(values[2]));
        return key;
    }

    String getStringKey(com.entities.DeptosPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodPais());
        sb.append(SEPARATOR);
        sb.append(value.getCodDepto());
        sb.append(SEPARATOR);
        sb.append(value.getZona());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Deptos) {
            Deptos o = (Deptos) object;
            return getStringKey(o.getDeptosPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Deptos.class.getName()});
            return null;
        }
    }
}
