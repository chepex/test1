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
import com.entities.ReadXls;
import java.io.File;
import java.io.IOException;

import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_Planilla_horas {

    @EJB
    private MovDpFacade movDpFacade;
    public String inputFile;
File inputWorkbook;
    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
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
    
public void uploadXls(  Sheet sheet) throws IOException  {
    
         
     
      
    try {
      
    for (int i = 0; i < sheet.getRows(); i++) {
      for (int j = 0; j < sheet.getColumns(); j++) {
        /*secuencia,cod_cia,cod_emp,cod_dp,valor*/
          Cell cell = sheet.getCell(j, i);
          
          
          if(j==0){
              System.out.println("Secuencia: "+ cell.getContents());
          }
          if(j==1){
              System.out.println("cod_cia: "+ cell.getContents());
          }          
          if(j==2){
              System.out.println("cod_emp: "+ cell.getContents());
          }          
          if(j==3){
              System.out.println("cod_dp: "+ cell.getContents());
          }          
          if(j==4){
              System.out.println("valor: "+ cell.getContents());
          }                    
          
          

        }
      }
    
    } catch (Exception e) {
      e.printStackTrace();
    }
}    
    


  
  public void read(String mas) throws IOException  {  
       this.setInputFile(mas);
  this.inputWorkbook= new File(this.getInputFile());     
    try {
     Workbook w=null;
      w = Workbook.getWorkbook(inputWorkbook);     
      Sheet sheet = w.getSheet(0);      
      for (int i = 1; i < sheet.getRows(); i++) {
      for (int j = 0; j < sheet.getColumns(); j++) {
        /*secuencia,cod_cia,cod_emp,cod_dp,valor*/
          Cell cell = sheet.getCell(j, i);
          if(j==0){
              System.out.println("Secuencia: "+ cell.getContents());
          }
          if(j==1){
              System.out.println("cod_cia: "+ cell.getContents());
          }          
          if(j==2){
              System.out.println("cod_emp: "+ cell.getContents());
          }          
          if(j==3){
              System.out.println("cod_dp: "+ cell.getContents());
          }          
          if(j==4){
              System.out.println("valor: "+ cell.getContents());
          }                    
        }
      }
    
    } catch (BiffException e) {
      e.printStackTrace();
    }
  }
}

