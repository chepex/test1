package com.entities;

import com.entities.util.JsfUtil;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;

import java.util.ResourceBundle;
import javax.ejb.EJBException;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 */
public abstract class AbstractController<T> {

    private AbstractFacade<T> ejbFacade;
    public Class<T> itemClass;
    private T selected;
    public List<T> items;

    public enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    protected AbstractFacade<T> getFacade() {
        return ejbFacade;
    }

    protected void setFacade(AbstractFacade<T> ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public T getSelected() {
        if (this.selected == null) {
            T newItem;
            try {
                newItem = itemClass.newInstance();
                this.selected = newItem;
                initializeEmbeddableKey();
                return newItem;
            } catch (InstantiationException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.selected = selected;
        }
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Returns all items in a List object
     *
     * @return
     */
    public List<T> getItems() {
        if (items == null) {
            items = this.ejbFacade.findAll();

        }
        return items;
    }
    
         
 


    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Updated");
        persist(AbstractController.PersistAction.UPDATE, msg);
    }

    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Created");
        persist(AbstractController.PersistAction.CREATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public void delete(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Deleted");
        persist(AbstractController.PersistAction.DELETE, msg);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
        }
    }

    public void persist(AbstractController.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != AbstractController.PersistAction.DELETE) {
                    this.ejbFacade.edit(selected);
                } else {
                    this.ejbFacade.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public void persist(AbstractController.PersistAction persistAction, Mensaje successMessage) {
        if (selected != null) {

            try {
                this.setEmbeddableKeys();
                if (persistAction != AbstractController.PersistAction.DELETE) {
                    this.ejbFacade.edit(selected);
                } else {
                    
                    this.ejbFacade.remove(selected);                  
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @return
     */
    public T prepareCreate(ActionEvent event) throws NoSuchFieldException {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            inicializar();
            initializeEmbeddableKey();
            return newItem;
        } catch (InstantiationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    public void inicializar() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        LoginBean lb = new LoginBean();
        /*
         * se podra la codcia en las pk de cada clase 
         * Field cia = this.selected.getClass().getDeclaredField("codCia");
         cia.setAccessible(true);
         cia.set(this.selected, lb.sscia());*/
        Field usr = this.selected.getClass().getDeclaredField("usuario");
        usr.setAccessible(true);
        usr.set(this.selected, lb.ssuser());
        Field creacion = this.selected.getClass().getDeclaredField("fechaReg");
        creacion.setAccessible(true);
        creacion.set(this.selected, lb.sdate());
    }
}