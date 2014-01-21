/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.DeducPresta;
import com.entities.DeducPrestaFacade;
import com.entities.Empleados;
import com.entities.EmpleadosFacade;
import com.entities.LoginBean;
import com.entities.MovDp;
import com.entities.MovDpFacade;
import com.entities.MovDpPK;
import com.entities.ProgramacionPla;
import com.entities.ProgramacionPlaFacade;
import com.entities.ResumenAsistencia;
import com.entities.ResumenAsistenciaFacade;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


@Stateless
public class SB_readXLS {
    @EJB
    private SB_Calculos sB_Calculos;
    @EJB
    private SB_ProgramacionPla sB_ProgramacionPla;
    @EJB
    private ProgramacionPlaFacade programacionPlaFacade;
    @EJB
    private DeducPrestaFacade deducPrestaFacade;
    @EJB
    private ResumenAsistenciaFacade resumenAsistenciaFacade;
    
   
private File inputWorkbook;        

@EJB
private EmpleadosFacade empleadosFacade;
@EJB
private MovDpFacade movDpFacade;    

    
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)  
  public String read(String mas, ProgramacionPla programacionPla) throws IOException, BiffException  {  
      
      String mensaje;
      try{
           this.inputWorkbook= new File(mas);
           Workbook w=null;       
           w = Workbook.getWorkbook(inputWorkbook);     
           Sheet sheet = w.getSheet(0);      
           if(programacionPla==null ){
              mensaje= SinSecuencia(sheet);
           }else{
              mensaje= ConSecuencia(sheet,programacionPla); 
           }
      }   
      catch(Exception ex){
        return "error";          
      }   
        return "ok " +mensaje;  
      }
  
    public String ConSecuencia(Sheet sheet,ProgramacionPla programacionPla){
        int xx=0; 
         int total=0; 
        String mensaje="";
        BigDecimal valor=null;
        LoginBean lb= new LoginBean();
        for (int i = 0; i < sheet.getRows(); i++) { 
           total++;
           MovDpPK movpk = new MovDpPK(); 
           MovDp movdp = new MovDp(); 
           movpk.setSecuencia(programacionPla.getProgramacionPlaPK().getSecuencia());           
           movpk.setCodCia(lb.sscia());
           movpk.setCodPresta(0);
            for (int j = 0; j < sheet.getColumns(); j++) {              
                
                Cell cell = sheet.getCell(j, i);
                if(j==0){
                  
                    
                    movpk.setCodEmp( Integer.parseInt(cell.getContents()));
                }          
                if(j==1){
                   
                    movpk.setCodDp(Short.parseShort(cell.getContents()));
                }          
                if(j==2){
               
                    
                    valor = new BigDecimal(cell.getContents());
                }                                
            }   
            
            mensaje = sB_ProgramacionPla.validarEstado(programacionPla);
     
            if(mensaje.equals("ok")){
                 movdp.setMovDpPK(movpk);
                 movdp.setUsuario(lb.ssuser() );
                 movdp.setFechaReg( lb.sdate());   
                 movdp.setValor(valor);
                 Empleados emp  = empleadosFacade.findbyCodemp(movpk.getCodEmp());
                 DeducPresta dp = deducPrestaFacade.findCodDeduc(movdp);
                 ResumenAsistencia ra= resumenAsistenciaFacade.ByEmp(movdp);
                 if (dp!=null){              
                     if (ra!=null ){
                       ProgramacionPla Vpla  = new ProgramacionPla(lb.sscia(),movdp.getMovDpPK().getSecuencia());                
                       movdp.setEmpleados(emp); 
                       movdp.setDeducPresta(dp);
                       movdp.setProgramacionPla(Vpla);    
                        if(dp.getCatDp().getDescripcion().equals("HorasExtras")){    
                            movdp.setDeducPresta(dp);
                            sB_Calculos.inicializar( ra );
                            sB_Calculos.movdp= movdp;
                            movdp.setCantidad(movdp.getValor());
                            valor = BigDecimal.valueOf( sB_Calculos.HoraExtra(movdp));
                            movdp.setValor(valor );       
                        }  
                        xx++;
                        movDpFacade.edit(movdp); 
                       }
                 }
            }
        }

    return "Cantida de registros insertados "+xx+" de "+total;
    }

    
    public String SinSecuencia(Sheet sheet){   
    int xx=0; 
      int total=0; 
        BigDecimal valor=null;
        int codEmp  =0; 
        short codDp =0;        
        LoginBean lb= new LoginBean();
        for (int i = 0; i < sheet.getRows(); i++) { 
         total++;
 
            for (int j = 0; j < sheet.getColumns(); j++) {              
                
                Cell cell = sheet.getCell(j, i);
                if(j==0){
                  
                    
                    codEmp =  Integer.parseInt(cell.getContents());
                }          
                if(j==1){
                   
                   codDp =Short.parseShort(cell.getContents());
                }          
                if(j==2){
               
                    
                    valor = new BigDecimal(cell.getContents());
                }                                
            }     
          Empleados emp  = empleadosFacade.findbyCodemp(codEmp); 
          ProgramacionPla pp= programacionPlaFacade.findByCodEmp(emp);
          String mensaje = sB_ProgramacionPla.validarEstado(pp);
          
            if(mensaje.equals("ok")){
                MovDpPK movpk = new MovDpPK( lb.sscia(), pp.getProgramacionPlaPK().getSecuencia() , codEmp, codDp, 0 ); 
                MovDp movdp = new MovDp(); 
                movdp.setMovDpPK(movpk);
                movdp.setUsuario(lb.ssuser() );
                movdp.setFechaReg( lb.sdate());   
                movdp.setValor(valor);

                DeducPresta dp = deducPrestaFacade.findCodDeduc(movdp);
                if (dp!=null){      
                ResumenAsistencia ra= resumenAsistenciaFacade.ByEmp(movdp);                              
                    if (ra!=null ){
                        
                        
                      ProgramacionPla Vpla  = new ProgramacionPla(lb.sscia(),movdp.getMovDpPK().getSecuencia());                
                      movdp.setEmpleados(emp); 
                      movdp.setDeducPresta(dp);
                      movdp.setProgramacionPla(Vpla); 
                      
                        if(dp.getCatDp().getDescripcion().equals("HorasExtras")){    
                            movdp.setDeducPresta(dp);
                            sB_Calculos.inicializar( ra );
                            sB_Calculos.movdp= movdp;
                            movdp.setCantidad(movdp.getValor());
                            valor = BigDecimal.valueOf( sB_Calculos.HoraExtra(movdp));
                            movdp.setValor(valor );       
                        }     
                        xx++;
                      movDpFacade.edit(movdp);  
                      }
                }
            }
        }

    return "Cantida de registros insertados "+xx+" de "+total;
    }
    
}