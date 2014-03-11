package com.entities;

import com.entities.Zonas;
import com.entities.ZonasFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class ZonasConverter implements Converter {

    @EJB
    private ZonasFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.ZonasPK getKey(String value) {
        com.entities.ZonasPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.ZonasPK();
        key.setCodPais(Short.parseShort(values[0]));
        key.setCodZona(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(com.entities.ZonasPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodPais());
        sb.append(SEPARATOR);
        sb.append(value.getCodZona());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Zonas) {
            Zonas o = (Zonas) object;
            return getStringKey(o.getZonasPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Zonas.class.getName()});
            return null;
        }
    }
}
