package com.entities;


import com.ejb.SB_ProgramacionPla;
import com.ejb.SB_readXLS;
import com.entities.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import jxl.read.biff.BiffException;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "movDpController")
@ViewScoped
public class MovDpController extends AbstractController<MovDp> implements Serializable {
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
     mensaje = sB_ProgramacionPla.validarEstado(programacionpla);
     
     if(mensaje.equals("ok")){
        ReadXls a = new ReadXls();
        String destination = "/opt/lib/"+event.getFile().getFileName();
        a.copyFile(event.getFile().getFileName(), event.getFile().getInputstream());  
        sB_readXLS.read(destination,this.getProgramacionpla());        
        if(mensaje.equals("ok")){
           consultar();
        }  
     }          
     JsfUtil.addSuccessMessage( msg);  
    }  

    public List<MovDp> consultar (){		
	this.items = ejbFacade.findByFiltro(this.getProgramacionpla() );
	
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
}
