/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Mensaje;
import com.entities.MovDp;
import com.entities.MovDpFacade;
import com.entities.MovDpPK;
import com.entities.PlanillaHoras;


import com.entities.ProgramacionPla;

import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_Planilla_horas {

    @EJB
    private MovDpFacade movDpFacade;
    
    Mensaje msg = new Mensaje();

/**FECHA DE CORTE FALTA AGREGAR LA VALIDACION**/
 /* programacionPla.getFechaCorte().compareTo(date)*/
    public Mensaje validar_planilla_horas(ProgramacionPla programacionPla) {
	Calendar currentDate = Calendar.getInstance();
	
	msg.setTitulo("ok");	    
	if ( programacionPla.getStatus().equals("C") ){	
	    msg.setTitulo("error");
	    msg.setMensajes("Esta planilla ya fue cerrada ");
	}
	Boolean a ;
	a= programacionPla.getFechaCorte().before(currentDate.getTime());
	if( a  ){
	    msg.setTitulo("error");
	    msg.setMensajes("Ha caducado la fecha de corte");
	}	   
	return  msg;    
    }
    
    public Mensaje validar_traslado(ProgramacionPla programacionPla,List <PlanillaHoras> planillahoras){
	
	Calendar currentDate = Calendar.getInstance();
	
	msg.setTitulo("ok");	    
	if (  programacionPla.getStatus().equals("C") ){	
	    msg.setTitulo("error");
	    msg.setMensajes("Esta planilla ya fue cerrada ");
	}
	Boolean a ;
	a= programacionPla.getFechaCorte().after(currentDate.getTime());
	if( a  ){
	    msg.setTitulo("error");
	    msg.setMensajes("Aun no ha caducado la fecha de corte");
	}
	if(msg.getTitulo().equals("ok")){
            
	  msg= trasladar(planillahoras);  
          
	} 
	return  msg;   
    }   
    
    public Mensaje trasladar(List <PlanillaHoras> planillahoras ){
	
	for( PlanillaHoras e : planillahoras ){ 
	       
	    MovDpPK  movdppk = new MovDpPK(e.getPlanillaHorasPK().getCodCia(), 
		    e.getProgramacionPla().getProgramacionPlaPK().getSecuencia(),
		    e.getEmpleados().getEmpleadosPK().getCodEmp(),
		    e.getDeducPresta().getDeducPrestaPK().getCodDp() );	
	    MovDp movdp = new MovDp(movdppk);	    
	    movdp.setValor(e.getValor());	    
	    movDpFacade.create(movdp );			
	}	
	msg.setTitulo("ok");
	msg.setMensajes("Informacion traslada correctamente");
	msg.setDescripcion(planillahoras.size()+" Registros");	
	return msg;
    }
    
    public  void ejecutar_fun_almacenada(){
	/*la idea es guardar el ombre de la formula y ejectuarla, asi ejucutaremos cada formula segun sea requerido por el mov*/
	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = manager.getEngineByName("js");
	Object result = null;
	try {
	result = engine.eval("Funcion();");
	} catch (ScriptException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}

    }
    
  
}

