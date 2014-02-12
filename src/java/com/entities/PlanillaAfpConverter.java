package com.entities;

import com.entities.PlanillaAfp;
import com.entities.PlanillaAfpFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class PlanillaAfpConverter implements Converter {

    @EJB
    private PlanillaAfpFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.PlanillaAfpPK getKey(String value) {
        com.entities.PlanillaAfpPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.PlanillaAfpPK();
        key.setCodCia(Short.parseShort(values[0]));
        key.setCodEmp(Short.parseShort(values[1]));
        key.setAnio(Short.parseShort(values[2]));
        key.setMes(Short.parseShort(values[3]));
        return key;
    }

    String getStringKey(com.entities.PlanillaAfpPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodCia());
        sb.append(SEPARATOR);
        sb.append(value.getCodEmp());
        sb.append(SEPARATOR);
        sb.append(value.getAnio());
        sb.append(SEPARATOR);
        sb.append(value.getMes());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof PlanillaAfp) {
            PlanillaAfp o = (PlanillaAfp) object;
            return getStringKey(o.getPlanillaAfpPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PlanillaAfp.class.getName()});
            return null;
        }
    }
}
