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
import com.entities.MovDp;
import com.entities.MovDpFacade;
import com.entities.Planilla;
import com.entities.PlanillaFacade;
import com.entities.ProgramacionPla;
import com.entities.ProgramacionPlaFacade;
import com.entities.ResumenAsistencia;
import com.entities.ResumenAsistenciaFacade;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_Planilla {
    @EJB
    private MovDpFacade movDpFacade;
    
    @EJB
    private DeducPrestaFacade deducPrestaFacade;
    
    @EJB
    private PlanillaFacade planillaFacade;
    @EJB
    private ProgramacionPlaFacade programacionPlaFacade;
    
    @EJB
    private SB_ProgramacionPla sB_ProgramacionPla;
    @EJB
    private SB_Calculos calculos;    
    @EJB
    private ResumenAsistenciaFacade resumenAsistenciaFacade;
    
    private EmpleadosFacade empleadosFacade;   
    Mensaje msg1;
 


    public void Deducciones(Empleados empleados) {
	/*traer el total de deduciones*/
	
    }

    public void DeduccionesLey(Empleados empleados) {
	/*generar las deducciones de ley */
	  
    }

    public Mensaje Generar()  {   
        String totales="";
        List<ProgramacionPla> iterador=  programacionPlaFacade.findByEstado("P");        
        if(iterador== null){
            msg1.setTitulo("ok");
            msg1.setMensajes("No existen planillas programadas");
           return  msg1;
        }
          for( ProgramacionPla e : iterador ){ 
              Borar(e); 
                if(e.getTiposPlanilla().getCodDp()>0 ){
                    DeducPresta deduc = deducPrestaFacade.findByTipoPla(e.getTiposPlanilla());
                   if(deduc.getProceso().equals("vacc")) {
                        PlanillaVacColectiva(e);   
                    }
                    if(deduc.getProceso().equals("vaca")) {
                        PlanillaVacAnual(e);   
                    }
                    if(deduc.getProceso().equals("agui")) {
                        PlanillaAguilado(e);                     
                    } 
                }else{
                    PlanillaNormal(e);                       
                }
                
               
                    msg1.setMensajes("Planillas generadas correctamente");
                    totales += e.getTiposPlanilla().getNomTipopla()+msg1.getDescripcion();
               
              }
            msg1.setDescripcion(totales);
         return msg1;
     }	
    
    
    public void PlanillaNormal(ProgramacionPla programacionPlax){
            

            List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(programacionPlax );

              for( ResumenAsistencia ra : iterator ){ 
                calculos.CalcularLey(ra);                
                calculos.CalcularPrestamos(ra);  
                calculos.CalcularLiqRecibir(ra); 
              }
              
             
              
              
    }
    
    public void PlanillaVacAnual(ProgramacionPla programacionPlax){
            Borar(programacionPlax);
            List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(programacionPlax );
            for( ResumenAsistencia ra : iterator ){         
                calculos.CalcularEspecial(ra);
               
                  calculos.CalcularLey(ra);
                  calculos.CalcularPrestamos(ra);  
                  calculos.CalcularLiqRecibir(ra);  
               
              }
            
    }
   
    public void PlanillaVacColectiva(ProgramacionPla programacionPlax){
            Borar(programacionPlax);
            List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(programacionPlax );
            for( ResumenAsistencia ra : iterator ){         
             calculos.CalcularEspecial(ra);
             
                  calculos.CalcularLey(ra);
                  calculos.CalcularPrestamos(ra);  
                  calculos.CalcularLiqRecibir(ra); 
              }
               
    }    
  
    public void PlanillaAguilado(ProgramacionPla programacionPlax){
            Borar(programacionPlax);
            List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(programacionPlax );
            for( ResumenAsistencia ra : iterator ){         
              
                
                  calculos.CalcularLey(ra);
                  calculos.CalcularPrestamos(ra);                 
                  calculos.CalcularLiqRecibir(ra);  
                
              }
                
    }      
    
    public void Borar(ProgramacionPla programacionPla){
                msg1 =  sB_ProgramacionPla.validarEstado(programacionPla);
                if(msg1.getTitulo().equals("ok")){
                    
                    List<Planilla> iterator =  planillaFacade.findByPk(programacionPla);
                    if(iterator != null){
                        for( Planilla p : iterator ){                             
                            planillaFacade.remove(p);                            
                            
                        }
                    } 
                    
                    List<MovDp> iterator2 =  movDpFacade.findSecuencia(programacionPla);
                    if(iterator2 != null){
                        for( MovDp p : iterator2 ){                             
                            movDpFacade.remove(p); 
                        }
                    }               
                    
                    
                    
                }       
    }    
    


}
