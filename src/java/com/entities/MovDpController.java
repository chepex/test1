package com.entities;


import com.ejb.SB_Calculos;
import com.ejb.SB_Planilla_horas;
import com.ejb.SB_ProgramacionPla;
import com.ejb.SB_readXLS;
import com.entities.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import jxl.read.biff.BiffException;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "movDpController")
@ViewScoped
public class MovDpController extends AbstractController<MovDp> implements Serializable {
    @EJB
    private ResumenAsistenciaFacade resumenAsistenciaFacade;
    @EJB
    private SB_Calculos sB_Calculos;
    @EJB
    private SB_Planilla_horas sB_Planilla_horas;    
    @EJB
    private SB_ProgramacionPla sB_ProgramacionPla;    
    @EJB
    private SB_readXLS sB_readXLS;       
    @EJB
    private MovDpFacade ejbFacade;
    @EJB
    private ProgramacionPlaFacade programacionPlaFacade;    
    
    

    
    private ProgramacionPla programacionpla;    
    private List <ProgramacionPla> programacionplas;      
    private String estado;
    private Mensaje msg= new Mensaje(); 
    public String mensaje;
    public MovDpController() {
	super(MovDp.class);
    }

    public ProgramacionPla getProgramacionpla() {
        return programacionpla;
    }

    public void setProgramacionpla(ProgramacionPla programacionpla) {
        this.programacionpla = programacionpla;
    }

    public List<ProgramacionPla> getProgramacionplas() {
        return programacionplas;
    }

    public void setProgramacionplas(List<ProgramacionPla> programacionplas) {
        this.programacionplas = programacionplas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public void upload(FileUploadEvent event) throws IOException, BiffException  {      
     
        ReadXls a = new ReadXls();
        String destination = "/opt/lib/"+event.getFile().getFileName();
        a.copyFile(event.getFile().getFileName(), event.getFile().getInputstream()); 
        String Mensaje ="";
        
        if(this.getProgramacionpla()==null ){
            mensaje=sB_readXLS.read(destination,null);        
        }else{
           mensaje= sB_readXLS.read(destination,this.getProgramacionpla());        
        }
        
        if(!mensaje.equals("error")){
           consultar();        
        }          
     
     JsfUtil.addSuccessMessage( mensaje); 
     
    }  

    public List<MovDp> consultar (){	
        if(this.getProgramacionpla()==null){
            this.items = ejbFacade.findByStatus("P" );
        }else{
            this.items = ejbFacade.findByFiltro(this.getProgramacionpla() );
        }
	
	
	if (!isValidationFailed()) {
	    this.setSelected(null); 
	}
	JsfUtil.contar_registros(items.size() );
	
	return this.items;
    }    

    public void ChangeEstadoPlanilla() {  
        if(estado !=null && !estado.equals(""))  
            programacionplas = programacionPlaFacade.findByEstado(estado);
        
    }   

    
  
    
    
    
    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
       
            this.getSelected().getMovDpPK().setCodCia(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodCia());
            this.getSelected().getMovDpPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
            this.getSelected().getMovDpPK().setCodDp(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodDp());
            this.getSelected().getMovDpPK().setSecuencia(this.getSelected().getProgramacionPla().getProgramacionPlaPK().getSecuencia());            
       
	
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setMovDpPK(new com.entities.MovDpPK());
    }
    
    @Override      
    public void delete(ActionEvent event) {
	mensaje    = sB_ProgramacionPla.validarEstado(programacionpla);
	
	if (mensaje.equals("ok")){
	    mensaje = "Registro Eliminado Correctamente empleado:";	    	    	    
	    persist(AbstractController.PersistAction.DELETE, msg);
	    consultar();
	}else{
	     JsfUtil.addSuccessMessage( mensaje);
	}
    }    
    

    @Override  
    public void save(ActionEvent event) {	
	mensaje    = sB_ProgramacionPla.validarEstado(programacionpla);
	
	if (mensaje.equals("ok")){
            calcular_horas();
	    mensaje = "Registro Modificado Correctamente ";	    	    
	    
	    persist(AbstractController.PersistAction.UPDATE, mensaje);
	}else{	    
	    JsfUtil.addSuccessMessage( mensaje);	    
	}
    }

    @Override      
    public void saveNew(ActionEvent event) {
	//this.getSelected().getPlanillaHorasPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp() );
	//this.getSelected().getPlanillaHorasPK().setCodDp(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodDp());
  
	mensaje = sB_ProgramacionPla.validarEstado(programacionpla);
	
	if (mensaje.equals("ok")){
                  calcular_horas();
                  
	    mensaje = "Registro Creado Correctamente ";	    	    
	    
	    persist(AbstractController.PersistAction.CREATE, mensaje);
	    consultar();		
	}else{
	      JsfUtil.addSuccessMessage( mensaje);
	}	    
	
	
	
    }   
    
   public void calcular_horas(){
       if(this.getSelected().getDeducPresta().getCatDp().getDescripcion().equals("HorasExtras"))
       {
        BigDecimal valor = new BigDecimal(0);
        setEmbeddableKeys();
        ResumenAsistencia ra = resumenAsistenciaFacade.ByProgramacionEmp(programacionpla, this.getSelected().getEmpleados());
        System.out.println(this.getSelected());
        sB_Calculos.inicializar( ra );
        this.getSelected().setValor(this.getSelected().getCantidad());
        sB_Calculos.movdp= this.getSelected();        
        valor = BigDecimal.valueOf( sB_Calculos.HoraExtra(this.getSelected()));
        this.getSelected().setValor(valor);
       }       
   } 
    
    
}
