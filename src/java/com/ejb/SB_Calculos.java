package com.ejb;
import com.entities.DeducPresta;
import com.entities.DeducPrestaFacade;
import com.entities.DetEmpleado;
import com.entities.DetEmpleadoFacade;
import com.entities.Empleados;
import com.entities.EmpleadosFacade;
import com.entities.LoginBean;
import com.entities.Mensaje;
import com.entities.MovDp;
import com.entities.MovDpFacade;
import com.entities.MovDpPK;
import com.entities.Planilla;
import com.entities.PlanillaFacade;
import com.entities.PlanillaPK;
import com.entities.Prestamos;
import com.entities.PrestamosFacade;
import com.entities.ResumenAsistencia;
import com.entities.ResumenAsistenciaFacade;
import com.entities.util.JsfUtil;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import com.entities.ProgramacionPla;
import com.entities.ProgramacionPlaFacade;
import com.entities.ProgramacionPlaPK;
import com.entities.Renta;
import com.entities.RentaFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
* @author       Mario J. Mixco
* @version	1.0.012014
* @return	Indica los parámetros de salida
* @exception	Indica la excepción que puede generar
* @param	Código para documentar cada uno de los parámetros
* @see          Una referencia a otra clase o utilidad
* @deprecated	El método ha sido reemplazado por otro
 */
@Stateless
public class SB_Calculos {
    @EJB
    private PlanillaFacade planillaFacade;
    @EJB
    private PrestamosFacade prestamosFacade;
    @EJB
    private DetEmpleadoFacade detEmpleadoFacade;
    @EJB
    private RentaFacade rentaFacade;
    @EJB
    private ProgramacionPlaFacade programacionPlaFacade;
    @EJB
    private MovDpFacade movDpFacade;
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private ResumenAsistenciaFacade resumenAsistenciaFacade;
    @EJB
    private DeducPrestaFacade deducPrestaFacade;    
    public MovDp movdp;
    public DeducPresta deducPresta;
    public ResumenAsistencia resumenAsistencia;
    public Empleados empleado;
    public ProgramacionPla programacionPla;  
    Mensaje msg = new Mensaje();
    /**
     *
     * @param movdp2
     * @return
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public MovDp CalcularModDp(MovDp movdp2) throws IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {       
       this.movdp = movdp2;
       this.empleado =   empleadosFacade.findbyCodemp(this.movdp.getMovDpPK().getCodEmp());
       this.deducPresta = deducPrestaFacade.findCodDeduc(this.movdp);
       this.resumenAsistencia = resumenAsistenciaFacade.ByEmp(this.movdp);
       /*SI no tiene proceso entonces solo retornar movdp*/
        if(this.deducPresta.getProceso().isEmpty()){
            crear_movdp( movdp2.getValor().floatValue() );
        }else{               
            this.getClass().getMethod(deducPresta.getProceso()).invoke(this);
        }        
       return this.movdp;         
    }
  
    
  /**
    * ejecuta los procesos de los movimientos de ley relacionados a el empleado
    * ejemplo: <br>
    *   emp:2526 
    *       movimientos a ejecutar y crear en la tabla mov_dp
    *       isss:1
    *       renta:2
    *       afp:3
    * @author       Mario J. Mixco
* @version	1.0.012014
    */
    public void CalcularLey(ResumenAsistencia resumenAsistenciax)    {    
      inicializar(resumenAsistenciax);
      try {  
          //this.msg.setTitulo("ok");
          List<DetEmpleado>   iterador =   detEmpleadoFacade.findByEmp(this.empleado);
          if(!iterador.isEmpty()) {          
            for( DetEmpleado e : iterador ){ 
              this.deducPresta = e.getDeducPresta();  
              if(this.deducPresta.getProceso()!= null){
                this.getClass().getMethod(this.deducPresta.getProceso()).invoke(this);                 
              }
              
            } 
          }         
       } catch (  NullPointerException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {                               
                 JsfUtil.logs(ex , "Surgio un error", "Proceso CalcularLey Empleado"+resumenAsistenciax.getEmpleados().getNombreIsss(),SB_Calculos.class,"ERROR");  
       }
    }
    
    /**
    * ejecuta los procesos de los movimientos relacionados por tipo de planilla
    *   ejemplo:
    *       planilla vacacion anual     = movimiento vaca 
    *       planilla vacacion colectiva = movimiento vacc 
    *       planilla aguinaldo          = movimiento agui
    * @author       Mario J. Mixco
* @version	1.0.012014
    */
    public void CalcularEspecial(ResumenAsistencia resumenAsistenciax)    {       
      try {  
          inicializar(resumenAsistenciax);          
          this.deducPresta = programacionPla.getTiposPlanilla().getDeducPresta();          
          this.getClass().getMethod(this.deducPresta.getProceso()).invoke(this); 
       } catch (  NullPointerException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {                       
                 JsfUtil.logs(ex , "Surgio un error", "Proceso CalcularEspecial Empleado"+resumenAsistenciax.getEmpleados().getNombreIsss(),SB_Calculos.class,"ERROR"); 
       }       
    }   
            
    /**
    *  crea los prestamos aun no cancelados por el empleado para luego guardarlos en la tabla mov_dp
    * ejemplo: 
    *   emp:2526 
    *       prestamos where cuotas_p!=cuotas    
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar            
     */
    public void CalcularPrestamos(ResumenAsistencia resumenAsistenciax)   { 
        try{
            inicializar(resumenAsistenciax);
            List<Prestamos>   iterador =   prestamosFacade.findByEmpleado(this.empleado);
            for( Prestamos e : iterador ){ 
              this.deducPresta = e.getDeducPresta();            
              crear_movdp(e.getVcuota().floatValue(),e );
            }            
        }catch(Exception ex){         
                 JsfUtil.logs(ex , "Surgio un error", "Proceso CalcularPrestamos Empleado"+resumenAsistenciax.getEmpleados().getNombreIsss(),SB_Calculos.class,"ERROR"); 
        }
    }    

    
    /**
    * obtinen el lilquido a recibir y posteriormente guarda este en la tabla planilla
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar            
     */
    public void CalcularLiqRecibir(ResumenAsistencia ra)   { 
        try{
          float liquido = LiqRecibir(ra);
          crear_planilla(liquido);                  
        }catch(Exception ex){           
           JsfUtil.logs(ex , "Surgio un error", "Proceso CalcularLiqRecibir Empleado"+ra.getEmpleados().getNombreIsss(),SB_Calculos.class,"ERROR");         
        }
    }    
  
    /**
    *  obtinen el lilquido a recibir y posteriormente guarda este en la tabla planilla
    * @author       Mario J. Mixco
* @version	1.0.012014   
    * @exception    Indica la excepción que puede generar            
     */
    public float LiqRecibir(ResumenAsistencia resumenAsistenciax)   { 
        float liquido =0;
        try{
            inicializar(resumenAsistenciax);
             liquido = devengado() -deducciones();
            if(liquido<0){
                liquido = negativos(liquido );
                liquido=(float)0.1;
            }          
                  return liquido;        
        }catch(Exception ex){           
           JsfUtil.logs(ex , "Surgio un error", "Proceso LiqRecibir Empleado"+resumenAsistenciax.getEmpleados().getNombreIsss(),SB_Calculos.class,"ERROR");         
           return liquido;  
        }
    } 
    
    /**
    * Actualiza los mivimientos de deduciones hasta dejar a cero el liquido a recibir 
    * @author       Mario J. Mixco
* @version	1.0.012014  
    * @exception    Indica la excepción que puede generar   
    * @return      
    */
    public float negativos(float negativo ) {      
        try{            
            List<MovDp> mov= this.movDpFacade.findByPkPrioridad(this.empleado, "R", this.resumenAsistencia);
             for( MovDp m : mov ){ 
                 BigDecimal real = m.getValor();
                float pendiente ;
                 if(negativo > m.getValor().floatValue()){
                    negativo= negativo + m.getValor().floatValue();
                    pendiente = negativo -real.floatValue();
                    m.setPendiente( BigDecimal.valueOf(pendiente));                                              
                    this.movDpFacade.edit(m);                                      
                 }else{
                     negativo = m.getValor().floatValue() + negativo;
                     pendiente = negativo -real.floatValue();
                     if(negativo<0.1){                         
                         m.setValor(BigDecimal.ZERO );                                
                         m.setPendiente(real);
                     }else{                         
                         m.setValor(BigDecimal.valueOf(negativo) );
                         m.setPendiente( BigDecimal.valueOf(pendiente));
                         return negativo;
                     }                     
                     this.movDpFacade.edit(m);                      
                 }                 
             }
            return 0;
        }catch(Exception ex){  
           JsfUtil.logs(ex , "Surgio un error", "Proceso negativos Empleado"+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");         
            return (float)0;       
        }
        
    }    
    
    
    /**
    *  Obtiene de las variables del EJB los dias laborados y el salario base del empleado para calcular asi el devengado
    *  Formula:
    *  devengado = (salario_base/dias_mes)* dias_laborados;
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar   
    * @return        float devengado
    */
    public float devengado( ) {      
        try{
            float dias_mes = 30;  
            float devengado ; 
            float dias_laborados = Float.valueOf( this.resumenAsistencia.getDias());		        
            devengado = (this.empleado.getSalarioBase().floatValue()/dias_mes)* (dias_laborados);
            float bonificaciones= prestaciones();
            devengado = devengado + bonificaciones;
            return devengado;	
        }catch(Exception ex){ 
           JsfUtil.logs(ex , "Surgio un error", "Proceso devengado Empleado "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");              
            return (float)0;       
        }
        
    }
    
 /**
    *  Obtiene de las variables del EJB los dias laborados y el salario base del empleado para calcular asi el devengado
    *  Formula:
    *  devengado = (salario_base/dias_mes)* dias_laborados;
    * @author       Mario J. Mixco
* @version	1.0.012014
    * @exception    Indica la excepción que puede generar   
    * @return        float devengado
    */
    public float bruto( ) {      
        try{
            float dias_mes = 30;  
            float devengado ; 
            float dias_laborados = Float.valueOf( this.resumenAsistencia.getDias());		        
            devengado = (this.empleado.getSalarioBase().floatValue()/dias_mes)* (dias_laborados);
            return devengado;	
        }catch(Exception ex){  
           JsfUtil.logs(ex , "Surgio un error", "Proceso bruto Empleado"+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");             
            return (float)0;       
        }        
    }    
    
    /**
    *  Obtiene el total de las deduciones por empleado que existen en la tabla mov_dp en una planilla especifica
    *  Formula:
    *  deduciones = sum(valor) from mov_dp where suma_resta = 'R'
    * @author       Mario J. Mixco
* @version	1.0.012014   
    * @exception    Indica la excepción que puede generar   
    * @return        float deducciones
    */
    public float deducciones( ) {   
       float valor=0;
       try{        
	List<MovDp> deduc =  movDpFacade.findByTotal(this.empleado, "R", this.programacionPla);
        if(!deduc.isEmpty()){
            for( MovDp m : deduc ){ 
         valor =valor+ m.getValor().floatValue();         
         }
        }      	        
       }catch(Exception ex){                    
       JsfUtil.logs(ex , "Surgio un error", "Proceso deducciones Empleado"+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR"); 
        return 0;
       }
      return valor;	
    }    
     
    /**
    *  Obtiene el total de las prestaciones por empleado que existen en la tabla mov_dp en una planilla especifica
    *  Formula:
    *  deduciones = sum(valor) from mov_dp where suma_resta = 'S'
    * @author       Mario J. Mixco
* @version	1.0.012014   
    * @exception    Indica la excepción que puede generar   
    * @return        float deducciones
    */
    public float prestaciones( ) {   
       float valor=0;
       try{        
        List<MovDp> deduc =  movDpFacade.findByTotal(this.empleado, "S", this.programacionPla);
        if(!deduc.isEmpty()){
            for( MovDp m : deduc ){ 
         valor =+ m.getValor().floatValue();

         }
        }       
       }catch(Exception ex){        
       JsfUtil.logs(ex , "Surgio un error", "Proceso prestaciones Empleado"+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR"); 
        return 0;
       }
      return valor;	
    }    
         

    /**
    *  Obtiene el valor en dinero de las horas extras laboradas por un empleado
    *  Formula:
    *  vHoraExtra = valor x hora $ * fractor tipo hora extras * cantidad horas
    *  Ejemplo
    *   Emp: 2526
    *       vHoraExtra = $0.97 * 2.25 * 10 = $21.82
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar   
    */
    public void HoraExtra(){  
        try{
            float  valor = valorHora() * this.deducPresta.getFactor().floatValue()*calcular_hora();        
            this.movdp.setValor( BigDecimal.valueOf( valor ) );  
        }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso HoraExtra Empleado"+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR"); 
          this.movdp.setValor( BigDecimal.ZERO ); 
        }
    }    
    
    /**
    *  Obtiene el valor en dinero de las horas extras laboradas por un empleado
    *  Formula:
    *  vHoraExtra = valor x hora $ * fractor tipo hora extras * cantidad horas
    *  Ejemplo
    *   Emp: 2526
    *       vHoraExtra = $0.97 * 2.25 * 10 = $21.82
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar   
    */
    public float HoraExtra(MovDp movdp){  
        try{
            this.movdp = movdp;
            float horas= calcular_hora();
            float  valor = valorHora() * movdp.getDeducPresta().getFactor().floatValue() *horas; 
            
            return valor;
        }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso HoraExtra Empleado"+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR"); 
            return 0;
        }
    }  
    
    
    /**
    *  Obtiene el valor de isss a descontar segun lo devengado por el empleado
    *  Formula:
    *  vIsss= 233 * 0.03;
    *  Ejemplo
    *   Emp: 2526
    *       vIsss = $233 * 0.03 = $6.99
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar   
    */
    public void isss(){        
        try{
            float devengado= devengado();
            float valor = devengado * this.deducPresta.getFactor().floatValue();
            valor= tope(valor);
            crear_movdp(valor);  
        }catch(Exception ex){      
           JsfUtil.logs(ex , "Surgio un error", "Proceso isss Empleado"+ this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR"); 
        }
        
    } 
    
    /**
    *  Evalua si un monto es mayor que el monto si es asi retorna el tope
    *  Ejemplo
    *   si $monto  > tope
    *       $monto = tope
    * @author       Mario J. Mixco
* @version	1.0.012014  
    * @exception    Indica la excepción que puede generar 
    * @return       float monto
    */
    public float tope(float valor ){    
        try{
            if(this.programacionPla.getTiposPlanilla().getFrecuencia().equals("Q")){
                float vtope ;
                if (this.programacionPla.getNumPlanilla() == 1){
                    vtope = this.deducPresta.getTope().floatValue()/2;
                }else{
                    vtope = this.deducPresta.getTope().floatValue();
                }
                if(valor>vtope){
                   valor=vtope; 
                }   
            }
            return valor;
        }catch(Exception ex){ 
           JsfUtil.logs(ex , "Surgio un error", "Proceso tope Empleado"+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");             
            return 0;
        }
    }
    
    
    /**
    * Obtiene el monto a descotar de afp segun lo devengado de un empleado
     *  Formula:
    *  vAfp= 233 * 0.0625=14.56;
    * @author       Mario J. Mixco
* @version	1.0.012014   
    * @exception    Indica la excepción que puede generar 
    */
    public void afp(){   
        try{
            
            float devengado= devengado();
            float valor = devengado * this.deducPresta.getFactor().floatValue();
            valor= tope(valor);
            crear_movdp(valor);               
        }
        catch(Exception ex){        
           JsfUtil.logs(ex , "Surgio un error", "Proceso afp Empleado "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");             
        }
        
    } 
    
    /**
    * Obtiene el devengado descontando el afp correspondiente a esa quincena
    *  Formula:
    *  devengado= ((salario base/dias mes) * dias laborados) - afp;    * 
    * @author       Mario J. Mixco
* @version	1.0.012014
    * @exception    Indica la excepción que puede generar 
     */
    public float devengado_renta(){
        try{            
            float devengado = devengado();            
            DetEmpleado   afp = detEmpleadoFacade.findByAfp(this.empleado)  ; 
            float factorAfp =1;
            if(afp != null){
                factorAfp= afp.getDeducPresta().getFactor().floatValue();
            }          
            float devengadoafp = devengado * factorAfp;
            devengado = devengado - devengadoafp;                 
            return devengado; 
        }catch(Exception ex){             
           JsfUtil.logs(ex , "Surgio un error", "Proceso devengado_renta Empleado "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");            
            return 0;             
        }
    }
    
    /**
    * Obtiene la renta quincenal por empleado (devengado_renta = devengado - afp)<br>
    *  Formula:<br>
    *       monto = ((devengado_renta- exceso) * porcentaje ) + valor fijo<br>
    *  ejemplo:<br>
    *   codemp: 2526<br>
    *       monto = ((1000- 457.9) * 20%) + 30<br>
    *       monto = $32.17  <br>
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar 
     */
    public void renta(){
        try{
            float devengado= devengado_renta();
            Renta renta= new Renta();
            if(devengado>0){
                devengado= (float) JsfUtil.Redondear((double)devengado,2);
                if(this.programacionPla.getTiposPlanilla().getFrecuencia().equals("Q")){
                   renta= rentaFacade.findByValor(devengado, (short)2);
                }
                if(this.programacionPla.getTiposPlanilla().getFrecuencia().equals("M")){
                   renta= rentaFacade.findByValor(devengado, (short)1);
                }                                
                float valor = (((( devengado -renta.getExceso().floatValue()) * renta.getPorcentaje().floatValue())/100) )+renta.getValorFijo().floatValue();
                crear_movdp(valor);   
            } 
        }catch(Exception ex){      
           JsfUtil.logs(ex , "Surgio un error", "Proceso renta Empleado "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");            
        }
    }     

 
    
    /**
    * Calcula el valor de la vacacion anual por empleado
    *  Formula:
    *       monto = ((devengado+(promedio comision 6 meses/2))*30%
    *  ejemplo:
    *   codemp: 2526
    *       monto = (1000+((5000 /6)/2))*30%
    *       monto = $1125
    * @author       Mario J. Mixco
* @version	1.0.012014  
    * @exception    Indica la excepción que puede generar 
     */
    public void vaca(){
        try{
            float devengado= devengado();
            BigDecimal promedio = movDpFacade.PromComision(empleado, resumenAsistencia);                
            float vpromedio = (promedio.floatValue()/6);
            float valor = ((devengado + vpromedio)/2) *  this.deducPresta.getFactor().floatValue();        
            crear_movdp(valor);            
        }catch(Exception ex){          
           JsfUtil.logs(ex , "Surgio un error", "Proceso vaca Empleado "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");            
        }
    }     
        
    
    /**
    * Calcula el valor de la vacacion colectiva por empleado
    *  Formula:
    *       monto = (devengado+((promedio comision 6 meses/30)*dias planilla))*30%
    *  ejemplo:
    *   codemp: 2526
    *       monto = (1000+(((5000/6) /30)*7))*30%
    *       monto = $1058
    * @author       Mario J. Mixco
* @version	1.0.012014   
    * @exception    Indica la excepción que puede generar 
     */
    public void vacc(){
        try{
            float devengado= devengado();
            float dias_mes = 30; 
            float dias_laborados = Float.valueOf( this.resumenAsistencia.getDias());		        
            BigDecimal promedio = movDpFacade.PromComision(empleado, resumenAsistencia);                
            float vpromedio = (promedio.floatValue()/6);
            float valor = (((devengado + vpromedio )/dias_mes)*dias_laborados ) *  this.deducPresta.getFactor().floatValue();        
            crear_movdp(valor);           
        }catch(Exception ex){           
           JsfUtil.logs(ex , "Surgio un error", "Proceso vacc Empleado"+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");            
        }
    }  
    
    
    
    /**
    * Calcula el valor del promedio comision<br>
    *  Formula:<br>
    *       monto = suma ultimos 6 meses<br>
    *  ejemplo:<br>
    *   codemp: 2526<br>
    *       monto = ((1000+(150))/30)*15<br>
    *       monto = $575<br>
    * @author       Mario J. Mixco
* @version	1.0.012014   
    * @exception    Indica la excepción que puede generar 
     */
    public void promedioagui(ResumenAsistencia resumenAsistenciax){
        try{      
            inicializar(resumenAsistenciax);        
            BigDecimal promedio = movDpFacade.PromComision(empleado, resumenAsistencia);
            float vpromedio = (promedio.floatValue());
            float valor =  + vpromedio;  	
            crear_movdp(valor);  
        }catch(Exception ex){          
           JsfUtil.logs(ex , "Surgio un error", "Proceso promedioagui Empleado"+resumenAsistenciax.getEmpleados().getNombreIsss(),SB_Calculos.class,"ERROR");            
        }
    }     
    
    
    /**
    * Calcula el valor del promedio comision<br>
    *  Formula:<br>
    *       monto = suma ultimos 6 meses<br>
    *  ejemplo:<br>
    *   codemp: 2526<br>
    *       monto = ((1000+(150))/30)*15<br>
    *       monto = $575<br>
    * @author       Mario J. Mixco
* @version	1.0.012014   
    * @exception    Indica la excepción que puede generar 
     */
    public void promedioQuincenal(ResumenAsistencia resumenAsistenciax){
        try{  
            inicializar(resumenAsistenciax);        
            BigDecimal promedio = movDpFacade.PromComision(empleado, resumenAsistencia);
            float vpromedio = (promedio.floatValue())/2;        
            crear_movdp(vpromedio);      
        }catch(Exception ex){          
           JsfUtil.logs(ex , "Surgio un error", "Proceso promedioQuincenal Empleado"+resumenAsistenciax.getEmpleados().getNombreIsss() ,SB_Calculos.class,"ERROR");            
        }
    }      
    
  /**
    * Calcula el valor del promedio comision<br>
    *  Formula:<br>
    *       monto = suma ultimos 6 meses<br>
    *  ejemplo:<br>
    *   codemp: 2526<br>
    *       monto = ((1000+(150))/30)*15<br>
    *       monto = $575<br>
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar 
     */
    public void promedioMensual(ResumenAsistencia resumenAsistenciax){
        try{  
            inicializar(resumenAsistenciax);
            BigDecimal promedio = movDpFacade.PromComision(empleado, resumenAsistencia);
            float valor = (promedio.floatValue());        
            crear_movdp(valor);      
        }catch(Exception ex){           
           JsfUtil.logs(ex , "Surgio un error", "Proceso promedioMensual Empleado"+resumenAsistenciax.getEmpleados().getNombreIsss(),SB_Calculos.class,"ERROR");            
        }
    }      
        
    
    /**
    * crea la deduccion o prestacion en la tabla de movimientos de planilla    
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar 
     */
    public void crear_movdp(float valor){     
        try{
            BigDecimal vv= new BigDecimal(valor);
            LoginBean lb= new LoginBean();		
            MovDp movdpx = new MovDp();
            MovDpPK movdppk = new MovDpPK();
            movdppk.setCodCia(lb.sscia());
            movdppk.setCodDp(this.deducPresta.getDeducPrestaPK().getCodDp());
            movdppk.setCodEmp(this.empleado.getEmpleadosPK().getCodEmp());
            movdppk.setSecuencia(this.resumenAsistencia.getResumenAsistenciaPK().getSecuencia());        
            movdppk.setCodPresta(0);
            movdpx.setMovDpPK(movdppk);
            movdpx.setValor(vv );        
            movdpx.setUsuario(lb.ssuser() );
            movdpx.setFechaReg( lb.sdate());    
            movdpx.setGenerado("G");        
            savemovDp(movdpx); 
        }catch(Exception ex){                       
           JsfUtil.logs(ex , "Surgio un error", "Proceso crear_movdp Empleado"+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");            
        }
    }
    
    public void crear_movdp(float valor,Prestamos prestamo){     
        try{
            BigDecimal vv= new BigDecimal(valor);
            LoginBean lb= new LoginBean();		
            MovDp movdpx = new MovDp();
            MovDpPK movdppk = new MovDpPK();
            movdppk.setCodCia(lb.sscia());
            movdppk.setCodDp(this.deducPresta.getDeducPrestaPK().getCodDp());
            movdppk.setCodEmp(this.empleado.getEmpleadosPK().getCodEmp());
            movdppk.setSecuencia(this.resumenAsistencia.getResumenAsistenciaPK().getSecuencia());        
            movdppk.setCodPresta(prestamo.getPrestamosPK().getCodPresta());
            movdpx.setMovDpPK(movdppk);        
            movdpx.setValor(vv );        
            movdpx.setUsuario(lb.ssuser() );
            movdpx.setFechaReg( lb.sdate());    
            movdpx.setGenerado("G");  
            savemovDp(movdpx);
            
        }catch(Exception ex){                      
           JsfUtil.logs(ex , "Surgio un error", "Proceso crear_movdp Empleado "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");            
        }
    }   
    
    
    
    /**
    * inserta los datos de liquido  a recibir, total devengado entre otros en la tabla de planilla   
    * @author       Mario J. Mixco
* @version	1.0.012014  
    * @exception    Indica la excepción que puede generar 
     */
    public void crear_planilla(float valor){  
        try{
            float   deduc = deducciones();               
            float bruto = bruto();        
            float TotalBonos = TotalBonos(this.resumenAsistencia);
            float TotalHorasExtras = TotalHorasExtras(this.resumenAsistencia);
            float neto= bruto+ TotalBonos+TotalHorasExtras; LoginBean lb= new LoginBean();		
            Planilla planillak = new Planilla();
            PlanillaPK planillapk = new PlanillaPK();
            planillapk.setCodCia(lb.sscia());        
            planillapk.setCodEmp(this.empleado.getEmpleadosPK().getCodEmp());
            planillapk.setSecuencia(this.resumenAsistencia.getResumenAsistenciaPK().getSecuencia());        
            planillak.setPlanillaPK(planillapk);
            planillak.setBruto(BigDecimal.valueOf( bruto) );
            planillak.setDias(this.resumenAsistencia.getDias());
            planillak.setBonos(BigDecimal.valueOf( TotalBonos ));
            planillak.setHorasExtras(BigDecimal.valueOf( TotalHorasExtras ));
            planillak.setNeto(BigDecimal.valueOf( neto ) );        
            planillak.setDeducciones(BigDecimal.valueOf(deduc) );        
            planillak.setLiquido(BigDecimal.valueOf(valor) );  
            planillak.setCodDepto(this.empleado.getDepartamentos().getDepartamentosPK().getCodDepto());
            planillak.setUsuario(lb.ssuser() );
            planillak.setFechaReg( lb.sdate()); 
            savePlanilla(planillak);
        }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso crear_planilla Empleado "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");            
          
        }
          
    }    
  
    /**
    * Retorna el total de movimientos para de la categoria bonos para una planilla y empleado especifico       
    * @author       Mario J. Mixco
* @version	1.0.012014
    */    
    public float TotalBonos(ResumenAsistencia ra){
        try{
        List<MovDp>  lmovdp =movDpFacade.findByCat("Bonos",ra);
        float total = 0;
        for( MovDp mdp : lmovdp ){    
             total = total + mdp.getValor().floatValue();
        }   
        return total;
        }catch(Exception ex){
            JsfUtil.logs(ex , "Surgio un error", "Proceso TotalBonos Empleado "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");            
            return 0;
        }
        
    }
    
    /**
    * Retorna el total de movimientos para de la categoria especifica para una planilla y empleado especifico       
    * @author       Mario J. Mixco
* @version	1.0.012014
    */    
  
    public float SumarCategoria(ResumenAsistencia ra,String Cat){
        float total = 0;        
        try{
         List<MovDp>  lmovdp =movDpFacade.findByCat(Cat,ra);

         for( MovDp mdp : lmovdp ){    
              total = total + mdp.getValor().floatValue();
         }  
        }catch(Exception ex){
            JsfUtil.logs(ex , "Surgio un error", "Proceso SumarCategoria "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                        
            return 0;
        }
         return total;
    }


    /**
    * Retorna el total de movimientos para de la categoria horasExtras para una planilla y empleado especifico       
    * @author       Mario J. Mixco
* @version	1.0.012014
    */            
    public float TotalHorasExtras(ResumenAsistencia ra){
        float total = 0;        
        try{
        List<MovDp>  lmovdp =movDpFacade.findByCat("HorasExtras",ra);

        for( MovDp mdp : lmovdp ){    
            total = total + mdp.getValor().floatValue();
        }   
        }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso SumarCategoria "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                        
            return 0;            
        }
        return total;
    }

    /**
    * inserta los datos de liquido  a recibir, total devengado entre otros en la tabla de planilla   
    * @author       Mario J. Mixco
* @version	1.0.012014  
    * @exception    Indica la excepción que puede generar 
    */  
    public void savePlanilla(Planilla planillak){   
        try{
        this.planillaFacade.create(planillak);
        this.planillaFacade.flush();
        this.planillaFacade.refresh(planillak);    
        }catch(Exception ex){
            JsfUtil.logs(ex , "Surgio un error", "Proceso savePlanilla "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                                    
            
        }
    }
    
    /**
    * inserta los datos de liquido  a recibir, total devengado entre otros en la tabla de planilla   
    * @author       Mario J. Mixco
* @version	1.0.012014  
    * @exception    Indica la excepción que puede generar 
    */  
    public void savemovDp(MovDp movDp){   
        try{
        this.movDpFacade.create(movDp);
        this.movDpFacade.flush();
        this.movDpFacade.refresh(movDp);    
        }catch(Exception ex){
            JsfUtil.logs(ex , "Surgio un error", "Proceso savePlanilla "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                                    
            
        }
    }    
    /**
    * retorna el valor por horas devengado por un empleado
    *   formula:
    *           $horas = (salariobase/dias mes )/ horas
    *   ejemplo:
    *           $horas = (233/30)/8
    *           $horas = 0.97
    *           
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar 
     */
    public float valorHora(){
        try{
            float dias  =  (float) 30.0;
            float horas  =  (float) 8.0;
            float sdiario= (this.empleado.getSalarioBase().floatValue() /dias)/horas;                
            return sdiario;
        }catch(Exception ex){     
           JsfUtil.logs(ex , "Surgio un error", "Proceso valorHora "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                                                
           return 0;
        }        
    }
    
    /**
    * convierte la cantidad de horas.minutos en horas.horas
    *   ejemplo:
    *       horas = 2.30 (horas.minutos)
    *       horas = 2.5  (horas.horas)
    *           
    * @author       Mario J. Mixco
* @version	1.0.012014 
    * @exception    Indica la excepción que puede generar 
     */
    public float calcular_hora(){ 
        try{
            /*float p_Ent  = this.movdp.getValor().setScale(0).floatValue();
            float Hora  = this.movdp.getValor().setScale(2).floatValue();
            float P_Dec = Hora - p_Ent; 
            float Var1 = (P_Dec*100)/60;
            float Valor = p_Ent + Var1;*/
            
            BigDecimal  valor2 =this.movdp.getValor();
            double minutos=0;
            double horasminutos=0;
            int entero= valor2.intValue();
                    //valor2 =  valor2.multiply(BigDecimal.valueOf(24));
                    minutos = this.movdp.getValor().remainder(BigDecimal.ONE).doubleValue();
                    minutos = (minutos * 60)/100;
                    horasminutos = entero + minutos;        
            
           return (float)(horasminutos) ;                                
            
        }catch(Exception ex){  
           JsfUtil.logs(ex , "Surgio un error", "Proceso calcular_hora "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                                                            
           return 0;              
        }
    }  
  
      /**
    * Inicializa las variables necesarias para los procesos    
    * @author       Mario J. Mixco
* @version	1.0.012014
    */
   public void inicializar(ResumenAsistencia resumenAsistenciax){         
    try{
      ProgramacionPla  progPla = new ProgramacionPla();
      ProgramacionPlaPK  progPlapk = new ProgramacionPlaPK();
      progPlapk.setCodCia(resumenAsistenciax.getResumenAsistenciaPK().getCodCia());
      progPlapk.setSecuencia(resumenAsistenciax.getResumenAsistenciaPK().getSecuencia());
      progPla.setProgramacionPlaPK(progPlapk);              
      this.empleado =   empleadosFacade.findbyCodemp(resumenAsistenciax.getEmpleados().getEmpleadosPK().getCodEmp());
      this.programacionPla = programacionPlaFacade.findByPk(progPla);      
      this.resumenAsistencia = resumenAsistenciax;            
     }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso inicializar "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                                                                      
    }
   } 

   
   public String recalculo(ResumenAsistencia ra){
    /**
    * Inicializa las variables necesarias para los procesos    
    * @author   Mario J. Mixco
    * @version	1.0.012014
    */
      try{
      inicializar(ra);
      float tdevengo = tdevengado();
      float pagado = tmovdpRenta();
      float tafp = tmovdpAfp();
      tdevengo = tdevengo - tafp;  
      Renta renta= rentaFacade.findByValor(tdevengo, (short)5);
      float calculado = (((( tdevengo -renta.getExceso().floatValue()) * renta.getPorcentaje().floatValue())/100) )+renta.getValorFijo().floatValue();      
         MovDpPK modDpPK = new MovDpPK();
         MovDp modDp = new MovDp();
         modDpPK.setCodCia(ra.getResumenAsistenciaPK().getCodCia());
         modDpPK.setCodDp((short)1);
         modDpPK.setCodEmp(this.empleado.getEmpleadosPK().getCodEmp());
         modDpPK.setSecuencia(ra.getResumenAsistenciaPK().getSecuencia());
         modDpPK.setCodPresta(0);
         modDp.setMovDpPK(modDpPK);
         MovDp modDpb =   movDpFacade.findPk2(modDp);
         /*Si ya existe el movimiento se elimina*/
         if(modDpb!=null) {
             movDpFacade.remove(modDpb);
             movDpFacade.flush();                
         } 
       float recalculoRenta=0;         
      if(calculado>pagado){
        recalculoRenta =  calculado-pagado; 
        this.deducPresta = deducPrestaFacade.findCodDeduc((short)1);  
        crear_movdp(recalculoRenta);
      } 
        Planilla pl= planillaFacade.findByEmp(ra);
        pl.setLiquido(BigDecimal.valueOf(LiqRecibir(ra)));
        pl.setDeducciones(BigDecimal.valueOf(deducciones()));
        planillaFacade.flush();
        planillaFacade.edit(pl);
        planillaFacade.flush();
        planillaFacade.refresh(pl);
        return "ok";
    }catch(Exception ex){
        
           JsfUtil.logs(ex , "Surgio un error", "Proceso recalculo"+ra.getEmpleados().getNombreIsss(),SB_Calculos.class,"ERROR");                                                                                 
            return "error";
    }  
   }

   public float tdevengado(){
    /**
    * Obtiene el total devengado de un empleado para un año especifico, se utiliza para el recalculo
    * @author       Mario J. Mixco
    * @version	1.0.012014
    * 
    */
       float total =0;
       try{
       
       List<Planilla> lpla=planillaFacade.findByEmpT(this.resumenAsistencia);
       for(Planilla pla : lpla){
           if(pla.getProgramacionPla().getTiposPlanilla().getLey().equals("S") ){
              total = total + pla.getNeto().floatValue();           
           } 
            
       }
        }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso tdevengado "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                                                                                 
            return 0;
        }
       
       return total;
   }

   public float tmovdpRenta(){
        /**
        * Obtiene el total de movimientos de renta para un año en especifico, se utiliza para el recalculo
        * @author       Mario J. Mixco
        * @version	1.0.012014
        */       
       float total =0;       
       try{

       List<MovDp> lmov=movDpFacade.findByRentaT(this.resumenAsistencia);
       for(MovDp mov : lmov){
         total = total + mov.getValor().floatValue();
       }      
       
       List<MovDp> lotmov=movDpFacade.findByRentaT(this.resumenAsistencia);
       for(MovDp mov : lotmov){
         total += total + mov.getValor().floatValue();
       }        
       }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso tmovdpRenta "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                                                                                 
            return 0;           
       }
       return total;
   }   

   public float tmovdpAfp(){
        /**
        * Obtiene el total de afp para un año especifico, esto se utiliza para efectos del recalculo
        * @author       Mario J. Mixco
        * @version	1.0.012014
        */       
       float total =0;       
       try{
            List<MovDp> lmov=movDpFacade.findByAfpT(this.resumenAsistencia);
            for(MovDp mov : lmov){
              total = total + mov.getValor().floatValue();
            }  
       }catch(Exception ex){       
           JsfUtil.logs(ex , "Surgio un error", "Proceso tmovdpAfp "+this.empleado.getNombreIsss(),SB_Calculos.class,"ERROR");                                                                                 
            return 0;                  
       }
       return total;
   }   

}
