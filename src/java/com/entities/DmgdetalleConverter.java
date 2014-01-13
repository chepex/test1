package com.entities;

import com.entities.Dmgdetalle;
import com.entities.DmgdetalleFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class DmgdetalleConverter implements Converter {

    @EJB
    private DmgdetalleFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.DmgdetallePK getKey(String value) {
        com.entities.DmgdetallePK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.DmgdetallePK();
        key.setCodCia(Short.parseShort(values[0]));
        key.setTipoDocto(values[1]);
        key.setNumPoliza(Integer.parseInt(values[2]));
        key.setFecha(java.sql.Date.valueOf(values[3]));
        key.setCorrelat(Integer.parseInt(values[4]));
        return key;
    }

    String getStringKey(com.entities.DmgdetallePK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodCia());
        sb.append(SEPARATOR);
        sb.append(value.getTipoDocto());
        sb.append(SEPARATOR);
        sb.append(value.getNumPoliza());
        sb.append(SEPARATOR);
        sb.append(value.getFecha());
        sb.append(SEPARATOR);
        sb.append(value.getCorrelat());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Dmgdetalle) {
            Dmgdetalle o = (Dmgdetalle) object;
            return getStringKey(o.getDmgdetallePK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Dmgdetalle.class.getName()});
            return null;
        }
    }
}
