package com.entities;

import com.ejb.SB_Prestamos;
import com.ejb.SB_readXLS;
import com.entities.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import jxl.read.biff.BiffException;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "prestamosController")
@ViewScoped
public class PrestamosController extends AbstractController<Prestamos> implements Serializable {
    @EJB
    private SB_readXLS sB_readXLS;    
    @EJB
    private MovDpFacade movDpFacade;
    String estado;
    @EJB
    private SB_Prestamos sB_Prestamos;

    @EJB
    private PrestamosFacade ejbFacade;
    List<MovDp> listaCuotas;
    
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
        
        if(this.getSelected().getFrecuencia()==3){
            float cutaq= this.getSelected().getVcuota().floatValue()/2;
            this.getSelected().setVcuota(BigDecimal.valueOf(cutaq) );      
            int cuotas= getSelected().getCuotas().shortValue() *2;
            this.getSelected().setCuotas(  (short)cuotas);
        }
            
	String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Created");
	this.setSelected(sB_Prestamos.CalcularMonto(this.getSelected()));	
	persist(AbstractController.PersistAction.CREATE, msg);
	

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
    

      
    public void cancelar(ActionEvent event) {
        this.getSelected().setCuotasP(this.getSelected().getCuotas());
        this.getSelected().setSaldo(BigDecimal.ZERO);
        ejbFacade.edit(this.getSelected());
        String msg = "Prestamos cancelado correctamente";
        JsfUtil.addSuccessMessage(msg);
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

    public List<MovDp> getListaCuotas() {         
        
        return listaCuotas;
    }
    
    public void consultarCoutas(){
         List<MovDp> lista= movDpFacade.findByCodPresta(this.getSelected());                    
         listaCuotas=lista;
    }
    
    
    
    public void upload(FileUploadEvent event) throws IOException, BiffException  {      
     
        ReadXls a = new ReadXls();
        String destination = "/opt/lib/"+event.getFile().getFileName();
        a.copyFile(event.getFile().getFileName(), event.getFile().getInputstream()); 
        String Mensaje ="";
        Mensaje=sB_readXLS.readPrestamos(destination);        
                  
     
     JsfUtil.addSuccessMessage( Mensaje); 
     
    }  
 
    
    

    
}
