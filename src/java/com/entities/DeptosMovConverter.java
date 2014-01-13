package com.entities;

import com.entities.DeptosMov;
import com.entities.DeptosMovFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class DeptosMovConverter implements Converter {

    @EJB
    private DeptosMovFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.DeptosMovPK getKey(String value) {
        com.entities.DeptosMovPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.DeptosMovPK();
        key.setCodCia(Short.parseShort(values[0]));
        key.setCodDepto(Short.parseShort(values[1]));
        key.setCodCat(Short.parseShort(values[2]));
        return key;
    }

    String getStringKey(com.entities.DeptosMovPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodCia());
        sb.append(SEPARATOR);
        sb.append(value.getCodDepto());
        sb.append(SEPARATOR);
        sb.append(value.getCodCat());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof DeptosMov) {
            DeptosMov o = (DeptosMov) object;
            return getStringKey(o.getDeptosMovPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DeptosMov.class.getName()});
            return null;
        }
    }
}
