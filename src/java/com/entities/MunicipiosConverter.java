package com.entities;

import com.entities.Municipios;
import com.entities.MunicipiosFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class MunicipiosConverter implements Converter {

    @EJB
    private MunicipiosFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.MunicipiosPK getKey(String value) {
        com.entities.MunicipiosPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.MunicipiosPK();
        key.setCodPais(Short.parseShort(values[0]));
        key.setCodDepto(Short.parseShort(values[1]));
        key.setCodMuni(Short.parseShort(values[2]));
        key.setCodZona(Integer.parseInt(values[3]));
        return key;
    }

    String getStringKey(com.entities.MunicipiosPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodPais());
        sb.append(SEPARATOR);
        sb.append(value.getCodDepto());
        sb.append(SEPARATOR);
        sb.append(value.getCodMuni());
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
        if (object instanceof Municipios) {
            Municipios o = (Municipios) object;
            return getStringKey(o.getMunicipiosPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Municipios.class.getName()});
            return null;
        }
    }
}
