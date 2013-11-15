package com.entities;

import com.ejb.SB_Planilla_horas;
import com.entities.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "planillaHorasController")
@ViewScoped
public class PlanillaHorasController extends AbstractController<PlanillaHoras> implements Serializable {
    @EJB
    private MovDpFacade movDpFacade;
    @EJB
    private SB_Planilla_horas sB_Planilla_horas;    
    @EJB
    private ProgramacionPlaFacade programacionPlaFacade;
    @EJB
    private DepartamentosFacade departamentosFacade;    
    @EJB
    private EmpleadosFacade empleadosFacade;
    List<Empleados> empPuestos;
    @EJB
    private PlanillaHorasFacade ejbFacade;
    ProgramacionPla programacionpla;
    List <ProgramacionPla> programacionplas;
    public  List <MovDp> MovDps;
    @ManagedProperty("#{controllerB}")
    private ReadXls readXls;
    
    public MovDp selectMovDp;

    public ReadXls getReadXls() {
        return readXls;
    }

    public void setReadXls(ReadXls readXls) {
        this.readXls = readXls;
    }
    
    
    public MovDp getSelectMovDp() {
	return selectMovDp;
    }

    public void setSelectMovDp(MovDp selectMovDp) {
	this.selectMovDp = selectMovDp;
    }
    public List<MovDp> getMovDps() {
	return MovDps;
    }

    public void setMovDps(List<MovDp> MovDps) {
	this.MovDps = MovDps;
    }
    List <PlanillaHoras> tplanillaHoras;
    String estado;
    Mensaje msg= new Mensaje();
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
    
    public PlanillaHorasController() {
	super(PlanillaHoras.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }
    

    @Override
    protected void setEmbeddableKeys() {
	/*
	this.getSelected().getPlanillaHorasPK().setCodDp(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodDp());
	this.getSelected().getPlanillaHorasPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
	this.getSelected().getPlanillaHorasPK().setCodCia(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodCia());
	this.getSelected().getPlanillaHorasPK().setSecuencia(programacionpla.getProgramacionPlaPK().getSecuencia());
*/
    }

    @Override
    protected void initializeEmbeddableKey() {
	PlanillaHorasPK mas= new com.entities.PlanillaHorasPK();
	if(programacionpla != null ){
	mas.setSecuencia(programacionpla.getProgramacionPlaPK().getSecuencia() );
	}
		
	this.getSelected().setPlanillaHorasPK(mas);
	//listaEmpsPuesto();
	
    }
    
    public void listaEmpsPuesto(){            
	    List<Departamentos> deptos= departamentosFacade.findByPuesto(); 
	    this.empPuestos =  empleadosFacade.findbyDeptos(deptos);  
	    this.getSelected().setProgramacionPla(programacionpla);
	   
    }

    public List<Empleados> getEmpPuestos() {	
	return empPuestos;
    }

    public void setEmpPuestos(List<Empleados> empPuestos) {	
	this.empPuestos = empPuestos;
    }   

    public void ChangeEstadoPlanilla() {  
        if(estado !=null && !estado.equals(""))  
            programacionplas = programacionPlaFacade.findByEstado(estado);
        
    }      

    public List<PlanillaHoras> consultar (){		
	this.items = ejbFacade.findByFiltro(this.getProgramacionpla() );
	
	if (!isValidationFailed()) {
	    this.setSelected(null); 
	}
	JsfUtil.contar_registros(items.size() );
	
	return this.items;
    }    

    public List<PlanillaHoras> getTplanillaHoras() {
	return tplanillaHoras;
    }

    public void setTplanillaHoras(List<PlanillaHoras> tplanillaHoras) {	
	
	this.tplanillaHoras = tplanillaHoras;
    }
    
    
    public void trasladar (){		
	this.setMovDps(movDpFacade.findAll());
	msg =sB_Planilla_horas.validar_traslado(programacionpla, items) ;
	 JsfUtil.addSuccessMessage( msg);
	 	    
    }
    
     @Override   
     public PlanillaHoras prepareCreate(ActionEvent event) throws NoSuchFieldException {
	
	try {
	    PlanillaHoras newItem;
	    newItem = itemClass.newInstance();
	    this.setSelected(newItem);
	    listaEmpsPuesto();
	    /*LoginBean lb= new LoginBean();	
	    String user = lb.ssuser();
	    this.getSelected().setUsuario(user);*/
	    this.inicializar();
	    
	    initializeEmbeddableKey();
	    return newItem;
	} catch (InstantiationException ex) {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }
     
    @Override  
    public void save(ActionEvent event) {	
	msg = sB_Planilla_horas.validar_planilla_horas(programacionpla);
	msg.setDescripcion( this.getSelected().getEmpleados().getNombreNit());	    
	if (msg.getTitulo().equals("ok")){
	    msg.setMensajes("Registro Modificado Correctamente ");	    	    
	    
	    persist(AbstractController.PersistAction.UPDATE, msg);
	}else{	    
	    JsfUtil.addSuccessMessage( msg);	    
	}
    }

    @Override      
    public void saveNew(ActionEvent event) {
	this.getSelected().getPlanillaHorasPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp() );
	this.getSelected().getPlanillaHorasPK().setCodDp(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodDp());
	msg = sB_Planilla_horas.validar_planilla_horas(programacionpla);
	msg.setDescripcion( this.getSelected().getEmpleados().getNombreNit());	    
	if (msg.getTitulo().equals("ok")){
	    msg.setMensajes("Registro Creado Correctamente ");	    	    
	    
	    persist(AbstractController.PersistAction.CREATE, msg);
	    consultar();		
	}else{
	      JsfUtil.addSuccessMessage( msg);
	}	    
	
	
	
    }  
    
 public void upload(FileUploadEvent event) throws IOException {  
    
      ReadXls a = new ReadXls();
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
           
        String destination = "/opt/lib/"+event.getFile().getFileName();
        try {
            a.copyFile(event.getFile().getFileName(), event.getFile().getInputstream());            
            this.sB_Planilla_horas.read(destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

 
    
 
 
    @Override      
    public void delete(ActionEvent event) {
	msg = sB_Planilla_horas.validar_planilla_horas(programacionpla);
	msg.setDescripcion( this.getSelected().getEmpleados().getNombreNit());	    
	if (msg.getTitulo().equals("ok")){
	    msg.setMensajes("Registro Eliminado Correctamente empleado:");	    	    
	    
	    persist(AbstractController.PersistAction.DELETE, msg);
	    consultar();
	}else{
	     JsfUtil.addSuccessMessage( msg);
	}
    }    
}
