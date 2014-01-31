
package com.ejb;
import com.entities.DeducPresta;
import com.entities.DeducPrestaFacade;
import com.entities.Empleados;
import com.entities.EmpleadosFacade;
import com.entities.Mensaje;
import com.entities.Parametros;
import com.entities.ParametrosFacade;
import com.entities.ProgramacionPla;
import com.entities.ResumenAsistencia;
import com.entities.ResumenAsistenciaFacade;
import com.entities.ResumenAsistenciaPK;
import com.entities.LoginBean;
import com.entities.Renta;
import com.entities.RentaFacade;
import com.entities.util.JsfUtil;
import com.entities.util.ManejadorFechas;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
* Este ejb se encarga de realizar todos los procesos de la asistencia de planilla
* @author       Mario J. Mixco
* @version	1.0.012014
*/
@Stateless
public class SB_Asistencia {
    @EJB
    private ParametrosFacade parametrosFacade1;
    @EJB
    private RentaFacade rentaFacade;
    @EJB
    private DeducPrestaFacade deducPrestaFacade;    
    @EJB
    private SB_ProgramacionPla sB_ProgramacionPla;
    @EJB
    private ParametrosFacade parametrosFacade;    
    @EJB
    private ResumenAsistenciaFacade resumenAsistenciaFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;   
    
    Mensaje msg = new Mensaje();
    String mensaje;
    
/**
*  Este proceso recorre los empleados de la la planilla selecionada y coloco la cantidad de dias a pagar
*  segun el tipo de planilla
* @author       Mario J. Mixco
* @version	1.0.012014
* @param	ProgramacionPla programacionpla
*/
    public String Generar(ProgramacionPla programacionpla ) {
        try{
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
        return "ok";	
        }catch(Exception ex){
           JsfUtil.logs(ex, "Surgio un error", "error en planilla proceso generar",SB_Asistencia.class,"ERROR");                                        
            return "error";	
        }
        
    }
    
/**
* Este proceso verifica si la asistencia ya fue genera si es este el caso solamente se consulta de
* lo contrario se genera
* @author       Mario J. Mixco
* @version	1.0.012014
* @param	ProgramacionPla programacionpla
*/    
    public String Validar_existe_asistencia(ProgramacionPla programacionpla){	
	
	List<ResumenAsistencia>   iterador =  resumenAsistenciaFacade.findByFiltro(programacionpla);		
        
	if ( iterador.isEmpty() ) 
	{
	    mensaje = sB_ProgramacionPla.validarEstado(programacionpla);
	    if (mensaje.equals("ok") ){
		mensaje = Generar( programacionpla ); 			
	    }else{
           JsfUtil.logs(new Exception() , "Surgio un error", "Planilla cerrada"+programacionpla.getProgramacionPlaPK().getSecuencia(),SB_Asistencia.class,"ALERT");                                                                                 
            }	    
	}
        return "ok";
	
    }
    
/**
* Este proceso recorre los empleados de la la planilla selecionada y coloco la cantidad de dias a pagar
* segun el tipo de planilla
* @author       Mario J. Mixco
* @version	1.0.012014
* @param	Código para documentar cada uno de los parámetros
*/    
    public String  eliminar_Asistencia(ProgramacionPla programacionpla ){	    
	 mensaje =sB_ProgramacionPla.validarEstado(programacionpla);   
        if (mensaje.equals("ok")  ){	 
            List<ResumenAsistencia> hj =  resumenAsistenciaFacade.findByFiltro(programacionpla);		    
            for( ResumenAsistencia e : hj ){ 
                resumenAsistenciaFacade.remove(e);			
            }            
	 }else{
           JsfUtil.logs(new Exception() , "Surgio un error", "Asistencia cerrada"+programacionpla.getProgramacionPlaPK().getSecuencia(),SB_Asistencia.class,"ALERT");                                                                                 
	 }
	return "ok";
    }
    

/**
* Este proceso recorre los empleados de la la planilla selecionada y coloco la cantidad de dias a pagar
* segun el tipo de planilla
* @author       Mario J. Mixco
* @version	1.0.012014
* @param	Código para documentar cada uno de los parámetros
*/      
    public String normal(ProgramacionPla programacionpla){
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
               return "ok";
            }catch(Exception ex){
              JsfUtil.logs(ex , "Surgio un error", "Proceso normal",SB_Asistencia.class,"ERROR");                                                                                 
               return "error";
            }
           
    }
    

/**
* Este proceso crea la hoja de asistencia para los empleados en la planilla de vacaciones colectivas
* @author       Mario J. Mixco
* @version	1.0.012014
* @param	Código para documentar cada uno de los parámetros
*/       
    public String vacc(ProgramacionPla programacionpla){
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
                return "ok";      
            }catch(Exception ex){
               JsfUtil.logs(ex , "Surgio un error", "Proceso vacc",SB_Asistencia.class,"ERROR");                                                                                 
                return "error";
            }
    }
   
    
/**
* Este proceso crea la hoja de asistencia para los empleados en la planilla de vacaciones anuales
* @author       Mario J. Mixco
* @version	1.0.012014
* @param	Código para documentar cada uno de los parámetros
*/       
    public String vaca(ProgramacionPla programacionpla){
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
                return "ok";
            }catch(Exception ex){
               JsfUtil.logs(ex , "Surgio un error", "Proceso vaca",SB_Asistencia.class,"ERROR");                                                                                 
                return "error";
            }
    }    
   
/**
* Este proceso crea la hoja de asistencia para los empleados en la planilla de aguinaldo
* @author       Mario J. Mixco
* @version	1.0.012014
* @param	Código para documentar cada uno de los parámetros
*/       
    public String agui(ProgramacionPla programacionpla){
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
                return "ok";
            }catch(Exception ex){
               JsfUtil.logs(ex , "Surgio un error", "Proceso agui",SB_Asistencia.class,"ERROR");                                                                                 
                return "error";              
            }
    }
    
    
/**
* Este proceso calcula los dias a pagar para un empleado,<br> 
* primera quincena  para todos 15 dias<br> 
* segunda quincena<br> 
* empleados con salario minimo 13,14,15 o 16 dias dependien de los dias faltantes del mes<br> 
* empleados > salario minimo 15 dias
* @author       Mario J. Mixco
* @version	1.0
* @param	ProgramacionPla programacionpla,Empleados e
*/      
   public String diasNormal(ProgramacionPla programacionpla,Empleados e){
     try{
                String dias = "15";
                Parametros min1 = parametrosFacade.findByNombre("SAL_MIN1");
                Parametros min2 = parametrosFacade.findByNombre("SAL_MIN2");
		int minimo1 = e.getSalarioBase().compareTo(min1.getValorInt());
		int minimo2 = e.getSalarioBase().compareTo(min2.getValorInt());
	
		if(programacionpla.getNumPlanilla()==2){
		    if (JsfUtil.diasDelMes(programacionpla.getMes(), programacionpla.getAnio())==31){
			
			if (minimo1 ==0  || minimo2 ==0)  {
			    dias= "16"; 
			}
		    }
		    if (JsfUtil.diasDelMes(programacionpla.getMes(), programacionpla.getAnio())==28){
			if (minimo1 ==0  || minimo2 ==0)  {
			   dias= "13";  
			}
		    }
		    if (JsfUtil.diasDelMes(programacionpla.getMes(), programacionpla.getAnio())==29){
			if (minimo1 ==0  || minimo2 ==0)  {
			    dias= "14"; 
			}
		    }		
		} 
            return dias;       
     }
     catch(Exception ex){                
       JsfUtil.logs(ex , "Surgio un error", "Proceso diasNormal",SB_Asistencia.class,"ERROR");                                                                                 
        return "0";
     }
     
  
    }  
   
   
   public String diasVacc(ProgramacionPla programacionpla, Empleados e){     
       Parametros  P= parametrosFacade1.findByNombre("DIAS_VACC");       
       String dias =P.getValorTxt();                
     return dias;
    }   
   
   public String diasVaca(ProgramacionPla programacionpla,Empleados e){     
        Parametros  P= parametrosFacade1.findByNombre("DIAS_VACA");       
        String dias =P.getValorTxt();                
                      
     return dias;
    }      
   
/**
* Este proceso calcula los dias laborales segun la tabla de aguinaldo llamada renta id 3<br>
* 
* @author       Mario J. Mixco
* @version	1.0.012014
* @param	ProgramacionPla programacionpla,Empleados e
*/     
   public String diasAgui(ProgramacionPla programacionpla,Empleados e){
       int dias_pago = 0 ;

      try{
            Date FechaIngreso = e.getFecIngreso();
            int año = 2013; int mes = 12; int dia = 12;
            Calendar calendar = new GregorianCalendar(año, mes-1, dia); 
            Date fechaAguinaldo=calendar.getTime();        
            int  c_d_agu  = ManejadorFechas.diferenciasDeFechas(FechaIngreso, fechaAguinaldo);
   
           Renta r = rentaFacade.findByValor(c_d_agu, (short)3);
            if(r == null){
                dias_pago = (10*c_d_agu)/365;
            }else{
                dias_pago=  r.getValorFijo().intValue();
            }          
      }
      catch(Exception ex){
        JsfUtil.logs(ex , "Surgio un error", "Proceso diasAgui",SB_Asistencia.class,"ERROR");                                                                                 
      }
      String Dias = Integer.toString(dias_pago);
    return Dias;
    }  
   

}
