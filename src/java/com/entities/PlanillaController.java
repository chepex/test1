package com.entities;


import com.ejb.SB_Reportes;
import com.ejb.SB_Planilla;
import com.entities.util.JsfUtil;
import java.io.IOException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;
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
         Mensaje msg = sBPlanilla.Generar();
         consultar();
	 JsfUtil.addSuccessMessage( msg);
         
         return "";
	
   }    
    
     public String reportePlanilla() throws NamingException, SQLException, JRException, IOException{         
        HashMap params = new HashMap();  
        long secuencia= 1111;
        params.put("mas",secuencia ); 
        reportes.GenerarReporte("/reportes/Planilla.jasper", params);
        
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

    

}
