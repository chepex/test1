package com.entities;

import com.entities.Dmgpoliza;
import com.entities.DmgpolizaFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class DmgpolizaConverter implements Converter {

    @EJB
    private DmgpolizaFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.DmgpolizaPK getKey(String value) {
        com.entities.DmgpolizaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.DmgpolizaPK();
        key.setCodCia(Short.parseShort(values[0]));
        key.setTipoDocto(values[1]);
        key.setNumPoliza(Integer.parseInt(values[2]));
        key.setFecha(java.sql.Date.valueOf(values[3]));
        return key;
    }

    String getStringKey(com.entities.DmgpolizaPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodCia());
        sb.append(SEPARATOR);
        sb.append(value.getTipoDocto());
        sb.append(SEPARATOR);
        sb.append(value.getNumPoliza());
        sb.append(SEPARATOR);
        sb.append(value.getFecha());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Dmgpoliza) {
            Dmgpoliza o = (Dmgpoliza) object;
            return getStringKey(o.getDmgpolizaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Dmgpoliza.class.getName()});
            return null;
        }
    }
}
