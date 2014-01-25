package com.entities;


import com.ejb.SB_Reportes;
import com.ejb.SB_Planilla;
import com.entities.util.JsfUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "planillaController")
@ViewScoped
public class PlanillaController extends AbstractController<Planilla> implements Serializable {
    @EJB
    private MovDpFacade movDpFacade;
    @EJB
    private SB_Reportes reportes;    
    @EJB
    private SB_Planilla sBPlanilla;
    
    List <MovDp> deduciones;
    List <MovDp> prestaciones;
    short anio;
    short mes;

    @EJB
    private PlanillaFacade ejbFacade;

    public PlanillaController() {

	super(Planilla.class);

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
    
     public String reportePlanilla() throws NamingException, SQLException, JRException, IOException{         
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
          String encabezado = sBPlanilla.generarTxt();
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
        String STA = "P";
        String SUMA = "S";
        String RESTA = "R";
        params.put("STA",STA ); 
        params.put("SUMA",SUMA ); 
        params.put("RESTA",RESTA ); 
        reportes.GenerarReporte("/reportes/BoletaPago2.jasper", params); 
        return "";     
    }
    
}
