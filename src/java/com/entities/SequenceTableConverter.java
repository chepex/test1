package com.entities;

import com.entities.SequenceTable;
import com.entities.SequenceTableFacade;
import com.entities.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class SequenceTableConverter implements Converter {

    @EJB
    private SequenceTableFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    com.entities.SequenceTablePK getKey(String value) {
        com.entities.SequenceTablePK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.entities.SequenceTablePK();
        key.setSeqName(values[0]);
        key.setCodCia(Short.parseShort(values[1]));
        return key;
    }

    String getStringKey(com.entities.SequenceTablePK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getSeqName());
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
        if (object instanceof SequenceTable) {
            SequenceTable o = (SequenceTable) object;
            return getStringKey(o.getSequenceTablePK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SequenceTable.class.getName()});
            return null;
        }
    }
}
