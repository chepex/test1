package com.entities;

import com.ejb.SB_Asistencia;
import com.ejb.SB_ProgramacionPla;
import com.entities.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CellEditEvent;


@ManagedBean(name = "resumenAsistenciaController")
@ViewScoped
public class ResumenAsistenciaController extends AbstractController<ResumenAsistencia> implements Serializable {
    @EJB
    private SB_ProgramacionPla sB_ProgramacionPla;
    
    @EJB
    private ProgramacionPlaFacade programacionPlaFacade;    
    List <ResumenAsistencia> Hoja_Asistencia;
    @EJB
    private SB_Asistencia sB_Asistencia;
    @EJB
    private ResumenAsistenciaFacade ejbFacade;
    String msg;    
    ProgramacionPla programacionpla;
    List <ProgramacionPla> programacionplas;
    String estado;
    public List<ProgramacionPla> getProgramacionplas() {
	return programacionplas;
    }

    public void setProgramacionplas(List<ProgramacionPla> programacionplas) {
	this.programacionplas = programacionplas;
    }
    

    
    public ProgramacionPla getProgramacionpla() {
	return programacionpla;
    }
    public void setProgramacionpla(ProgramacionPla programacionpla) {
	this.programacionpla = programacionpla;
    }
    public String getEstado() {
	return estado;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }    

    
    public List<ResumenAsistencia> getHoja_Asistencia() {
	return Hoja_Asistencia;
    }
    
    public void setHoja_Asistencia(List<ResumenAsistencia> Hoja_Asistencia) {
	this.Hoja_Asistencia = Hoja_Asistencia;
    }

    
    public ResumenAsistenciaController() {
	super(ResumenAsistencia.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
	this.getSelected().getResumenAsistenciaPK().setCodCia(this.getSelected().getEmpleados().getEmpleadosPK().getCodCia());
	this.getSelected().getResumenAsistenciaPK().setSecuencia(this.programacionpla.getProgramacionPlaPK().getSecuencia());
	this.getSelected().getResumenAsistenciaPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setResumenAsistenciaPK(new com.entities.ResumenAsistenciaPK());
    }
    
    public String generar (){	
    try { 
	 msg =sB_Asistencia.Validar_existe_asistencia( this.getProgramacionpla() ) ; 
	consultar();
	
    }     catch (EJBException ex) {
        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
    }
    
	return "";
        
    }

    public List<ResumenAsistencia> consultar (){		
	this.items = ejbFacade.findByFiltro(this.getProgramacionpla() );		
	if (!isValidationFailed()) {
	    this.setSelected(null); 
	}
	JsfUtil.contar_registros(items.size() );
	return this.items;
    }
    
    /**
    * Elimina la hoja de asistencia completa
    */    
   public void eliminar (){	
	 msg =sB_ProgramacionPla.validarEstado(programacionpla);   
        if (msg.equals("ok")  ){
	    msg =sB_Asistencia.eliminar_Asistencia(this.getProgramacionpla() ) ;	    
	    JsfUtil.addSuccessMessage(msg);    
	    consultar();
	 }else{
	    JsfUtil.addSuccessMessage(msg);    
	}	
    }
   
    /**
    * Edita un registro del datagrid
    */      
    public void onCellEdit(CellEditEvent event) {         
	 msg = sB_ProgramacionPla.validarEstado(programacionpla);

	if (msg.equals("ok")  ){		    
	    persist(AbstractController.PersistAction.UPDATE, msg);        	          
	    }else{
		JsfUtil.addSuccessMessage(msg);          
	    }	
    }  
 
    
   
    public void ChangeEstadoPlanilla() {  
        if(estado !=null && !estado.equals(""))  
            programacionplas = programacionPlaFacade.findByEstado(estado);        
    }  

    
    @Override  
    public void save(ActionEvent event) {	
     msg = sB_ProgramacionPla.validarEstado(programacionpla);   
     
     if (msg.equals("ok")  ){
            this.getSelected().setProgramacionPla(programacionpla);
	    persist(AbstractController.PersistAction.UPDATE, msg);
	    consultar();        
     }else{	
	JsfUtil.addSuccessMessage(msg);          
    }	
    }    
    
    @Override      
    public void saveNew(ActionEvent event) {
	 msg = sB_ProgramacionPla.validarEstado(programacionpla);     

	 this.getSelected().setProgramacionPla(programacionpla);
     if (msg.equals("ok")  ){	    	    
	    
	    persist(AbstractController.PersistAction.CREATE, msg);
	    consultar();        
     }else{	
	JsfUtil.addSuccessMessage(msg);          
    }	
	
    }      
    
    @Override      
    public void delete(ActionEvent event) {
	 msg = sB_ProgramacionPla.validarEstado(programacionpla);

        if (msg.equals("ok")  ){

	    
	    persist(AbstractController.PersistAction.DELETE, msg);
	    consultar();
	 }else{	    
	    JsfUtil.addSuccessMessage(msg);    
	}
    }        
    
}
