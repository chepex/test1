package com.entities;

import com.entities.PlanillaIsss;
import com.entities.PlanillaIsssFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class PlanillaIsssConverter implements Converter {

    @EJB
    private PlanillaIsssFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.PlanillaIsssPK getKey(String value) {
        com.entities.PlanillaIsssPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.PlanillaIsssPK();
        key.setCodCia(Short.parseShort(values[0]));
        key.setAnio(Short.parseShort(values[1]));
        key.setMes(Short.parseShort(values[2]));
        key.setCodEmp(Integer.parseInt(values[3]));
        return key;
    }

    String getStringKey(com.entities.PlanillaIsssPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodCia());
        sb.append(SEPARATOR);
        sb.append(value.getAnio());
        sb.append(SEPARATOR);
        sb.append(value.getMes());
        sb.append(SEPARATOR);
        sb.append(value.getCodEmp());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof PlanillaIsss) {
            PlanillaIsss o = (PlanillaIsss) object;
            return getStringKey(o.getPlanillaIsssPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PlanillaIsss.class.getName()});
            return null;
        }
    }
}
