/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;
import com.entities.DeducPresta;
import com.entities.DeducPrestaFacade;
import com.entities.Empleados;
import com.entities.EmpleadosFacade;
import com.entities.Mensaje;
import com.entities.Parametros;
import com.entities.ParametrosFacade;
import com.entities.ProgramacionPla;
import com.entities.ProgramacionPlaFacade;
import com.entities.ResumenAsistencia;
import com.entities.ResumenAsistenciaFacade;
import com.entities.ResumenAsistenciaPK;
import com.entities.LoginBean;
import com.entities.util.JsfUtil;
import com.entities.util.ManejadorFechas;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
/**
 *
 * @author mmixco
 */
@Stateless
public class SB_Asistencia {
    @EJB
    private DeducPrestaFacade deducPrestaFacade;
    
    @EJB
    private SB_ProgramacionPla sB_ProgramacionPla;
    @EJB
    private ParametrosFacade parametrosFacade;
    @EJB
    private ProgramacionPlaFacade programacionPlaFacade;
    @EJB
    private ResumenAsistenciaFacade resumenAsistenciaFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;   
    Mensaje msg = new Mensaje();
    
    public Mensaje Generar(ProgramacionPla programacionpla ) {
        if(programacionpla.getTiposPlanilla().getCodDp()>0 ){
                DeducPresta deduc = deducPrestaFacade.findByTipoPla(programacionpla.getTiposPlanilla());
	     if(deduc.getProceso().equals("vacc")) {
                vacc(programacionpla);
              }
              if(deduc.getProceso().equals("vaca")) {
                vaca(programacionpla);
              }
              if(deduc.getProceso().equals("agui")) {
                agui(programacionpla);
              }
        }else{
                    normal(programacionpla);
        } 
	
              
              
                if(msg.getTitulo().equals("error")){
                    return msg;
                }else{
                    msg.setMensajes("Planilla generada correctamente");                    
                }

	msg.setTitulo( "ok");
	return msg;
    }
    
    
    public Mensaje Validar_existe_asistencia(ProgramacionPla programacionpla){	
	
	List<ResumenAsistencia>   iterador =  resumenAsistenciaFacade.findByFiltro(programacionpla);		
	msg.setMensajes("Esta hoja ya fue generada");
	msg.setTitulo( "ok");
	if ( iterador.isEmpty() ) /*Estado pendiente se ha generado la asistencia es necesario eliminarla*/
	{
	    msg = sB_ProgramacionPla.validarEstado(programacionpla);
	    if (msg.getTitulo().equals("ok") ){
		msg = Generar( programacionpla ); 			
	    }	    
	}

	return msg;
    }
    
    public Mensaje eliminar_Asistencia(ProgramacionPla programacionpla ){	    
	 msg =sB_ProgramacionPla.validarEstado(programacionpla);   
        if (msg.getTitulo().equals("ok")  ){	 
	List<ResumenAsistencia> hj =  resumenAsistenciaFacade.findByFiltro(programacionpla);		    
	for( ResumenAsistencia e : hj ){ 
	    resumenAsistenciaFacade.remove(e);			
	}
	msg.setMensajes("Asistenia eliminada correactamente");
	msg.setDescripcion("Total de Registros "+ hj.size());
	msg.setTitulo( "ok");
	 }else{
	     msg.setMensajes("Una Asistenia cerrada no puede ser eliminada:" );
	     msg.setTitulo( "error");
	 }
	return msg;
    }
    
    public void normal(ProgramacionPla programacionpla){
           try{
            LoginBean lb= new LoginBean();		 
	    short codCia = lb.sscia();	    
	    List<Empleados>   iterador =    empleadosFacade.findbytipoPla(   programacionpla );		    
	    for( Empleados e : iterador ){        
		ResumenAsistencia hj = new ResumenAsistencia();
		ResumenAsistenciaPK resumenAsistenciaPK = new ResumenAsistenciaPK(codCia,programacionpla.getProgramacionPlaPK().getSecuencia(),e.getEmpleadosPK().getCodEmp() );
		String dias= diasNormal( programacionpla, e);
		hj.setDias(dias);
		hj.setEmpleados(e);
		hj.setProgramacionPla(programacionpla);
		hj.setResumenAsistenciaPK(resumenAsistenciaPK);	    	    
		resumenAsistenciaFacade.edit(hj);			
	    }
	msg.setMensajes("Se genero la asistencia correctamente ");
	msg.setDescripcion("Total de Registros "+ iterador.size());
	msg.setTitulo( "ok");        
            }catch(Exception ex){
            msg.setDescripcion("surgio un error en "+this.getClass().getName()+".normal" );
            msg.setTitulo( "error");                
            }
           
    }
    

    public void vacc(ProgramacionPla programacionpla){
           try{
            LoginBean lb= new LoginBean();		 
	    short codCia = lb.sscia();	    
	    List<Empleados>   iterador =    empleadosFacade.findbyVacc(   programacionpla );		    
	    for( Empleados e : iterador ){        
		ResumenAsistencia hj = new ResumenAsistencia();
		ResumenAsistenciaPK resumenAsistenciaPK = new ResumenAsistenciaPK(codCia,programacionpla.getProgramacionPlaPK().getSecuencia(),e.getEmpleadosPK().getCodEmp() );
		String dias= diasVacc( programacionpla, e);
		hj.setDias(dias);
		hj.setEmpleados(e);
		hj.setProgramacionPla(programacionpla);
		hj.setResumenAsistenciaPK(resumenAsistenciaPK);	    	    
		resumenAsistenciaFacade.edit(hj);			
	    }
	msg.setMensajes("Se genero la asistencia correctamente ");
	msg.setDescripcion("Total de Registros "+ iterador.size());
	msg.setTitulo( "ok");        
            }catch(Exception ex){
            msg.setDescripcion("surgio un error en "+this.getClass().getName()+".normal" );
            msg.setTitulo( "error");                
            }
    }
   
    
    public void vaca(ProgramacionPla programacionpla){
           try{
            LoginBean lb= new LoginBean();		 
	    short codCia = lb.sscia();	    
	    List<Empleados>   iterador =   null;		    
                for( Empleados e : iterador ){        
                    ResumenAsistencia hj = new ResumenAsistencia();
                    ResumenAsistenciaPK resumenAsistenciaPK = new ResumenAsistenciaPK(codCia,programacionpla.getProgramacionPlaPK().getSecuencia(),e.getEmpleadosPK().getCodEmp() );
                    String dias= diasVaca( programacionpla, e);
                    hj.setDias(dias);
                    hj.setEmpleados(e);
                    hj.setProgramacionPla(programacionpla);
                    hj.setResumenAsistenciaPK(resumenAsistenciaPK);	    	    
                    resumenAsistenciaFacade.edit(hj);			
                }
            msg.setMensajes("Se genero la asistencia correctamente ");
            msg.setDescripcion("Total de Registros 0");
            msg.setTitulo( "ok");        
            }catch(Exception ex){
            msg.setDescripcion("surgio un error en "+this.getClass().getName()+".normal" );
            msg.setTitulo( "error");                
            }
    }    
   
    
    public void agui(ProgramacionPla programacionpla){
           try{
            LoginBean lb= new LoginBean();		 
	    short codCia = lb.sscia();	    
	    List<Empleados>   iterador =    empleadosFacade.activos();			    
                for( Empleados e : iterador ){        
                    ResumenAsistencia hj = new ResumenAsistencia();
                    ResumenAsistenciaPK resumenAsistenciaPK = new ResumenAsistenciaPK(codCia,programacionpla.getProgramacionPlaPK().getSecuencia(),e.getEmpleadosPK().getCodEmp() );
                    String dias= diasAgui( programacionpla, e);
                    hj.setDias(dias);
                    hj.setEmpleados(e);
                    hj.setProgramacionPla(programacionpla);
                    hj.setResumenAsistenciaPK(resumenAsistenciaPK);	    	    
                    resumenAsistenciaFacade.edit(hj);			
                }
            msg.setMensajes("Se genero la asistencia correctamente ");
            msg.setDescripcion("Total de Registros 0");
            msg.setTitulo( "ok");        
            }catch(Exception ex){
            msg.setDescripcion("surgio un error en "+this.getClass().getName()+".normal" );
            msg.setTitulo( "error");                
            }
    }
    
   public String diasNormal(ProgramacionPla programacionpla,Empleados e){
     
                String dias = "15";
                Parametros min1 = parametrosFacade.findByNombre("SAL_MIN1");
                Parametros min2 = parametrosFacade.findByNombre("SAL_MIN2");
		int minimo1 = e.getSalarioBase().compareTo(min1.getValorInt());
		int minimo2 = e.getSalarioBase().compareTo(min2.getValorInt());
		
		if(programacionpla.getNumPlanilla()==2){
		    if (JsfUtil.diasDelMes(programacionpla.getMes(), programacionpla.getAnio())==31){
			
			if (minimo1 !=1  && minimo2 !=1)  {
			    dias= "16"; 
			}
		    }
		    if (JsfUtil.diasDelMes(programacionpla.getMes(), programacionpla.getAnio())==28){
			if (minimo1 !=1  && minimo2 !=1)  {
			   dias= "13";  
			}
		    }
		    if (JsfUtil.diasDelMes(programacionpla.getMes(), programacionpla.getAnio())==29){
			if (minimo1 !=1  && minimo2 !=1)  {
			    dias= "14"; 
			}
		    }		
		}  
     return dias;
    }  
   
   public String diasVacc(ProgramacionPla programacionpla, Empleados e){
     
                String dias = "7";
                
     return dias;
    }   
   
   public String diasVaca(ProgramacionPla programacionpla,Empleados e){
     
                String dias = "15";
                
     return dias;
    }      
   
   public String diasAgui(ProgramacionPla programacionpla,Empleados e){
       
      Date FechaIngreso = e.getFecIngreso();
      int año = 2013; int mes = 12; int dia = 12;
      Calendar calendar = new GregorianCalendar(año, mes-1, dia); 
      Date fechaAguinaldo=calendar.getTime();        
      int  c_d_agu  = ManejadorFechas.diferenciasDeFechas(FechaIngreso, fechaAguinaldo);
      int dias_pago ;
      
        if (c_d_agu >= 365 && c_d_agu < 1095){
           c_d_agu   = 365;
           dias_pago = 10;
        }else if( c_d_agu >= 1095 && c_d_agu < 3650){ 
           dias_pago = 15;
        }
        else if( c_d_agu >= 3650 ){ 
           dias_pago = 18;
        }
        else{
           dias_pago = (10*c_d_agu)/365;
        }
        
        String Dias = Integer.toString(dias_pago);
    return Dias;
    
    }  
   

}
