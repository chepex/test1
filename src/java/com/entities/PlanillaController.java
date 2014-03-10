package com.entities;


import com.ejb.SB_Reportes;
import com.ejb.SB_Planilla;
import com.entities.util.JsfUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "planillaController")
@ViewScoped
public class PlanillaController extends AbstractController<Planilla> implements Serializable {
    @EJB
    private VplanillaFacade vPlanillaFacade;
    @EJB
    private ProgramacionPlaFacade programacionPlaFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private MovDpFacade movDpFacade;
    @EJB
    private SB_Reportes reportes;    
    @EJB
    
    private SB_Planilla sBPlanilla;
    List <MovDp> deduciones;
    List <MovDp> prestaciones;
    ProgramacionPla programacionpla;
    String estado;
    List <ProgramacionPla> programacionplas;
    List <Empleados> ListEmpleados;
    short anio;
    short mes;
    int correlativo;
    String todosdptos;  
    String reciboEstado;
    List <ProgramacionPla> reciboProgramacioPlas;
    List <Vplanilla> listvplanilla;
    
    Integer progress;  
   

    public Integer getProgress() {
        
        LoginBean lb= new LoginBean();	
	    int proceso = lb.sspro();
         
            progress =  proceso;
         
          
        return progress;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    
    public void setProgress(Integer progress) {
        this.progress = progress;
    }
    
    


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Empleados> getListEmpleados() {
        return ListEmpleados;
    }

    public void setListEmpleados(List<Empleados> ListEmpleados) {
        this.ListEmpleados = ListEmpleados;
    }


    

    
    @EJB
    private PlanillaFacade ejbFacade;

    public PlanillaController() {

	super(Planilla.class);

    }

    public String getReciboEstado() {
        return reciboEstado;
    }

    public void setReciboEstado(String reciboEstado) {
        this.reciboEstado = reciboEstado;
    }

    public List<ProgramacionPla> getReciboProgramacioPlas() {
        return reciboProgramacioPlas;
    }

    public void setReciboProgramacioPlas(List<ProgramacionPla> reciboProgramacioPlas) {
        this.reciboProgramacioPlas = reciboProgramacioPlas;
    }
    
    public String getTodosdptos() {
        return todosdptos;
    }

    public void setTodosdptos(String todosdptos) {
        this.todosdptos = todosdptos;
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

    
    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);

    }

    @Override
    protected void setEmbeddableKeys() {
	this.getSelected().getPlanillaPK().setSecuencia(this.getSelected().getProgramacionPla().getProgramacionPlaPK().getSecuencia());
	this.getSelected().getPlanillaPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
	this.getSelected().getPlanillaPK().setCodCia(this.getSelected().getDepartamentos().getDepartamentosPK().getCodCia());
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setPlanillaPK(new com.entities.PlanillaPK());
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public short getMes() {
        return mes;
    }

    public void setMes(short mes) {
        this.mes = mes;
    }

    
    public List<MovDp> getDeduciones() {
        if(this.getSelected()!=null ){
              this.setDeduciones(movDpFacade.findByTotal(this.getSelected().getEmpleados(), "R",this.getSelected().getProgramacionPla())); 
        return deduciones;                   
        }
            
        return deduciones;
    }

    public List<MovDp> getPrestaciones() {
      if(this.getSelected()!=null ){
              this.setPrestaciones(movDpFacade.findByTotal(this.getSelected().getEmpleados(), "S",this.getSelected().getProgramacionPla())); 
              return prestaciones;                   
        }        
        return prestaciones;
    }

    public void setPrestaciones(List<MovDp> prestaciones) {
        this.prestaciones = prestaciones;
    }

    
    public void setDeduciones(List<MovDp> deduciones) {
        
        this.deduciones = deduciones;
    }
    
    public void ChangeEstadoPlanilla() {  
        if(estado !=null && !estado.equals(""))  
            programacionplas = programacionPlaFacade.findByEstado(estado);        
    }  
    
    public void ChangeEstadoPlanilla2() {  
        if(reciboEstado !=null && !reciboEstado.equals(""))  
            reciboProgramacioPlas = programacionPlaFacade.findByEstado(reciboEstado);        
    }    
    
    public void ChangePlanilla() {  
        if(reciboProgramacioPlas !=null && !reciboProgramacioPlas.equals(""))  
            this.ListEmpleados= empleadosFacade.findbytipoPla(programacionpla);
    }     
    
    
    public String generar(){    
        this.anio= 0;
        this.mes = 0;
      
             sBPlanilla.Generar();
             consultar();
             JsfUtil.addSuccessMessage( "Planilla generada correctamente");
          
     return "";
	
   }   
    
    
    public String cerrar(){    
        this.anio= 0;
        this.mes = 0;    
        try{
            if (sBPlanilla.Cerrar().equals("ok")){
                JsfUtil.addSuccessMessage("Cierre ejecutado con exito");
            }else{
                JsfUtil.addErrorMessage("Surgio un error al ejecutar el proceso de cierre");
            }
            
            
        }catch(Exception ex){
            JsfUtil.addErrorMessage(ex, "Surgio un error al ejecutar el proceso de cierre");
        }      
        return "";	
   }    
    
    public String negativos(){    
        this.anio= 0;
        this.mes = 0;    
        try{
            if (sBPlanilla.Negativos().equals("ok")){
                JsfUtil.addSuccessMessage("Cierre ejecutado con exito");
            }else{
                JsfUtil.addErrorMessage("Surgio un error al ejecutar el proceso de cierre");
            }
            
            
        }catch(Exception ex){
            JsfUtil.addErrorMessage(ex, "Surgio un error al ejecutar el proceso de cierre");
        }      
        return "";	
   }       
        
    
     public String reportePlanilla2() throws NamingException, SQLException, JRException, IOException{         
        HashMap params = new HashMap();  
        long cia= 1;
        long secuencia= 20140141;
        params.put("mas",secuencia ); 
        params.put("cia",cia ); 
        reportes.GenerarReporte("/reportes/Planilla.jasper", params);
        
        return "";           
    }    
     
     public String Gcvs()  {                
        try{
          FacesContext fc = FacesContext.getCurrentInstance();
          HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
          response.setContentType("text/csv"); 
          fc.responseComplete();         
          response.setHeader ( "Content-disposition", "attachment; filename=\"Reporting-" + 
          new Date().getTime() + ".csv\"" );
          ServletOutputStream output = response.getOutputStream();      
          
          String encabezado = sBPlanilla.generarTxt(this.getCorrelativo());
          InputStream is = new ByteArrayInputStream( encabezado.getBytes("UTF-8") );
          int nextChar;
           while ((nextChar = is.read()) != -1) 
           {
              output.write(nextChar);
           }
           output.flush();
           output.close();
          } catch ( IOException  e )
            {           
                e.printStackTrace();
            }
        return "";           
    }      
     
    public List<Planilla> consultar (){	
        if(this.anio>0 && this.mes >0){            
            this.items = ejbFacade.findByAnioMes( this.anio, this.mes);		
        }else{
            this.items = ejbFacade.findByStatus( );		
        }
	
	if (!isValidationFailed()) {
	    this.setSelected(null); 
	}
	JsfUtil.contar_registros(items.size() );
	return this.items;
    }  
    
   
    
    

    public String GenerarBoletas() throws NamingException, SQLException, JRException, IOException{         
        HashMap params = new HashMap();          
        String SUMA = "S";
        String RESTA = "R";
        BigDecimal codEmp =  new BigDecimal (this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
        long secuencia= this.getProgramacionpla().getProgramacionPlaPK().getSecuencia();        
        params.put("SUMA",SUMA ); 
        params.put("RESTA",RESTA );
        params.put("CODEMP",codEmp );
        params.put("SECUENCIA",secuencia );        
        reportes.GenerarReporte("/reportes/BoletaPago2.jasper", params); 
        return "";     
    }

    
    public String reportePlanilla() throws NamingException, SQLException, JRException, IOException{  
        HashMap params = new HashMap(); 
        LoginBean lb= new LoginBean();	
	long codCia = lb.sscia();
        long secuencia;
        secuencia = this.getProgramacionpla().getProgramacionPlaPK().getSecuencia();
        params.put("cia",codCia ); 
        params.put("mas",secuencia ); 
        ListEmpleados = empleadosFacade.findbyPuestos((short)418);
        for(Empleados emp:  ListEmpleados){
            params.put("encargadoplanilla",emp.getNombres()+" "+emp.getApellidos()); 
            params.put("puestoplanilla",emp.getPuestos().getNomPuesto());
        }
        ListEmpleados = empleadosFacade.findbyPuestos((short)325);
        for(Empleados emp:  ListEmpleados){
            params.put("gerenteConta",emp.getNombres()+" "+emp.getApellidos());
            params.put("puestoconta",emp.getPuestos().getNomPuesto());
        }
        ListEmpleados = empleadosFacade.findbyPuestos((short)57);
        for(Empleados emp:  ListEmpleados){
            params.put("gerenteRrhh",emp.getNombres()+" "+emp.getApellidos());
            params.put("puestorrhh",emp.getPuestos().getNomPuesto() );
        }
        ListEmpleados = empleadosFacade.findbyPuestos((short)111);
        for(Empleados emp:  ListEmpleados){
            params.put("directorFinanza",emp.getNombres()+" "+emp.getApellidos());
            params.put("puestofinanza",emp.getPuestos().getNomPuesto());
        }

        if(this.todosdptos == null || "1".equals(this.todosdptos)){
            reportes.GenerarReporte("/reportes/Planilla.jasper", params);
        }else{
            reportes.GenerarReporte("/reportes/PlanillaEjecutivos.jasper", params);
        }
        return "";           
    } 

    
    public String reciboPlanilla() throws NamingException, SQLException, JRException, IOException{  
        HashMap params = new HashMap(); 
        LoginBean lb= new LoginBean();	
	long codCia = lb.sscia();
        long secuencia;
        secuencia = this.getProgramacionpla().getProgramacionPlaPK().getSecuencia();
        String suma = "S";
        String resta = "R";
        params.put("CIA",codCia ); 
        params.put("SECUENCIA",secuencia ); 
        params.put("SUMA",suma ); 
        params.put("RESTA",resta ); 

        if(this.todosdptos == null || "1".equals(this.todosdptos)){
            reportes.GenerarReporte("/reportes/BoletaPago_Pla.jasper", params);
        }else{
            reportes.GenerarReporte("/reportes/PlanillaEjecutivos.jasper", params);
        }
        return "";           
    } 
 
    public String reporteMail(){
            String a = reportes.mario();
            return a;
    }
    
    
    public HashMap Parameter() {
        HashMap params = new HashMap(); 
            LoginBean lb= new LoginBean();	
            long codCia = lb.sscia();
            long secuencia;
            secuencia = this.getProgramacionpla().getProgramacionPlaPK().getSecuencia();
            params.put("cia",codCia ); 
            params.put("mas",secuencia ); 
            ListEmpleados = empleadosFacade.findbyPuestos((short)148);
            for(Empleados emp:  ListEmpleados){
                params.put("encargadoplanilla",emp.getNombreNit()); 
                params.put("puestoplanilla",emp.getPuestos().getNomPuesto());
            }
            ListEmpleados = empleadosFacade.findbyPuestos((short)325);
            for(Empleados emp:  ListEmpleados){
                params.put("gerenteConta",emp.getNombreNit());
                params.put("puestoconta",emp.getPuestos().getNomPuesto());
            }
            ListEmpleados = empleadosFacade.findbyPuestos((short)57);
            for(Empleados emp:  ListEmpleados){
                params.put("gerenteRrhh",emp.getNombreNit());
                params.put("puestorrhh",emp.getPuestos().getNomPuesto() );
            }
            ListEmpleados = empleadosFacade.findbyPuestos((short)111);
            for(Empleados emp:  ListEmpleados){
                params.put("directorFinanza",emp.getNombreNit());
                params.put("puestofinanza",emp.getPuestos().getNomPuesto());
            }

        return params;
    }



   
}
