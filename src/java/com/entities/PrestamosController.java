package com.entities;

import com.ejb.SB_Prestamos;
import com.entities.util.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "prestamosController")
@ViewScoped
public class PrestamosController extends AbstractController<Prestamos> implements Serializable {
    String estado;
    @EJB
    private SB_Prestamos sB_Prestamos;

    @EJB
    private PrestamosFacade ejbFacade;

    public PrestamosController() {
	super(Prestamos.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    public String getEstado() {
	return estado;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    
    @Override
    protected void setEmbeddableKeys() {
	this.getSelected().getPrestamosPK().setCodPresta( ejbFacade.SeqNext()); 	
	this.getSelected().getPrestamosPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
	this.getSelected().getPrestamosPK().setCodCia(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodCia());
	this.getSelected().getPrestamosPK().setCodDp(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodDp());
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setPrestamosPK(new com.entities.PrestamosPK());
    }
    
    @Override
    public void saveNew(ActionEvent event) {
	String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Created");
	this.setSelected(sB_Prestamos.CalcularMonto(this.getSelected()));	
	persist(AbstractController.PersistAction.CREATE, msg);
	
	/*if (!isValidationFailed()) {
	    items = null; // Invalidate list of items to trigger re-query.
	}*/
	//consultar();
    }

    @Override    
    public void delete(ActionEvent event) {
        if( this.getSelected().getCuotasP()==0  ){
	    String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Deleted");
	    persist(AbstractController.PersistAction.DELETE, msg);	    
	}else{
	    Mensaje msg = new Mensaje();
	    msg.setMensajes("Este prestamo ya tiene cuotas descontadas no se puede eliminar");
	    msg.setTitulo( "error");
	    JsfUtil.addSuccessMessage(msg);
	} 
	
	if (!isValidationFailed()) {
	    this.setSelected(null);
	   // items = null; // Invalidate list of items to trigger re-query.
	}
    }

    @Override        
    public void save(ActionEvent event) {
	if( this.getSelected().getCuotasP()==0  ){
	    String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Updated");
	    persist(AbstractController.PersistAction.UPDATE, msg);
	//    consultar();
	}else{
	    Mensaje msg = new Mensaje();
	    msg.setMensajes("Este prestamo ya tiene cuotas descontadas no se puede actualizar");
	    msg.setTitulo( "error");
	    JsfUtil.addSuccessMessage(msg);
	} 


    }
    
    

    
    public List<Prestamos> consultar (){		
	this.items = ejbFacade.findByFiltro(this.getEstado() );
	
	if (!isValidationFailed()) {
	    this.setSelected(null); 
	}
	JsfUtil.contar_registros(items.size() );
	
	return this.items;
    }       
    
}
