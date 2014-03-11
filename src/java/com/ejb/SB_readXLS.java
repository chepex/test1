
package com.ejb;

import com.entities.DeducPresta;
import com.entities.DeducPrestaFacade;
import com.entities.Empleados;
import com.entities.EmpleadosFacade;
import com.entities.LoginBean;
import com.entities.MovDp;
import com.entities.MovDpFacade;
import com.entities.MovDpPK;
import com.entities.Prestamos;
import com.entities.PrestamosFacade;
import com.entities.PrestamosPK;
import com.entities.ProgramacionPla;
import com.entities.ProgramacionPlaFacade;
import com.entities.Pruebas;
import com.entities.PruebasFacade;
import com.entities.ResumenAsistencia;
import com.entities.ResumenAsistenciaFacade;
import com.entities.util.JsfUtil;
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
    private PruebasFacade pruebasFacade;
    
    @EJB
    private PrestamosFacade prestamosFacade;
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
 
    
 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)  
 public String prueba(String mas, ProgramacionPla programacionPla) throws IOException, BiffException  {        
      String mensaje;
      try{
           this.inputWorkbook= new File(mas);
           Workbook w=null;       
           w = Workbook.getWorkbook(inputWorkbook);     
           Sheet sheet = w.getSheet(0);      
          mensaje=VPrueba(sheet);
            
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
        Empleados emp= null;
        LoginBean lb= new LoginBean();
         String codEmp ="";
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
                  
                     codEmp =  String.valueOf(cell.getContents());
                    
                }          
                if(j==1){
                   
                    movpk.setCodDp(Short.parseShort(cell.getContents()));
                }          
                if(j==2){
               
                    
                    valor = new BigDecimal(cell.getContents());
                }                                
            }   
            
            mensaje = sB_ProgramacionPla.validarEstado(programacionPla);
     try{
            if(mensaje.equals("ok")){
                 if(codEmp.length()>4){
                    emp  = empleadosFacade.findbyCodempref(codEmp); 
                 }else{
                      int codemp = Integer.valueOf(codEmp);
                      emp  = empleadosFacade.findbyCodemp(codemp); 
                      
                 }
                
                 movpk.setCodEmp(emp.getEmpleadosPK().getCodEmp());
                 movdp.setMovDpPK(movpk);
                 movdp.setUsuario(lb.ssuser() );
                 movdp.setFechaReg( lb.sdate());   
                 movdp.setValor(valor);
                 
                 DeducPresta dp = deducPrestaFacade.findCodDeduc(movdp);
                 ResumenAsistencia ra= resumenAsistenciaFacade.ByEmp(movdp);
                 movdp.setGenerado("N");
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
                        movDpFacade.flush();
                       }
                 }
            }
     }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso upload Linea"+i,SB_readXLS.class,"ERROR");            
     }
        }

    return "Cantida de registros insertados "+xx+" de "+total;
    }

  public String VPrueba(Sheet sheet){   
      int xx=0; 
      int total=0; 
        String valor="";
        String codEmp  =""; 
        String codDp ="";      
        
        LoginBean lb= new LoginBean();
        for (int i = 0; i < sheet.getRows(); i++) { 
         total++;
 
            for (int j = 0; j < sheet.getColumns(); j++) {              
                xx=xx+1;
                Cell cell = sheet.getCell(j, i);
                if(j==0){
                  codEmp =  String.valueOf(cell.getContents());
                }          
                if(j==1){
                  codDp =String.valueOf(cell.getContents());
                }          
                if(j==2){
                  valor = String.valueOf(cell.getContents());
                }                                
            }
             try{ 
                Pruebas pruebas  = new Pruebas();
                pruebas.setV1(codEmp);
                pruebas.setV2(String.valueOf(codDp));
                pruebas.setV3(String.valueOf(valor));
                pruebasFacade.edit(pruebas);
                
            }catch(Exception ex){
                JsfUtil.logs(ex , "Surgio un error", "Proceso upload Linea"+i,SB_readXLS.class,"ERROR");            
            }
        }

    return "Cantida de registros insertados "+xx+" de "+total;
    }    
    
    public String SinSecuencia(Sheet sheet){   
      int xx=0; 
      int total=0; 
        BigDecimal valor=null;
        String codEmp  =""; 
        short codDp =0;      
        Empleados emp = null;
        LoginBean lb= new LoginBean();
        for (int i = 0; i < sheet.getRows(); i++) { 
         total++;
 
            for (int j = 0; j < sheet.getColumns(); j++) {              
                
                Cell cell = sheet.getCell(j, i);
                if(j==0){
                  
                    
                    codEmp =  String.valueOf(cell.getContents());
                }          
                if(j==1){
                   
                   codDp =Short.parseShort(cell.getContents());
                }          
                if(j==2){
               
                    
                    valor = new BigDecimal(cell.getContents());
                }                                
            }
             try{ 
                 
                 if(codEmp.length()>4){
                     emp  = empleadosFacade.findbyCodempref(codEmp); 
                 }else{
                     int codemp = Integer.valueOf(codEmp);
                      emp  = empleadosFacade.findbyCodemp(codemp); 
                      
                 }
          
    
          ProgramacionPla pp= programacionPlaFacade.findByCodEmp(emp);
          if(pp!=null){
              
           
          String mensaje = sB_ProgramacionPla.validarEstado(pp);
        
                if(mensaje.equals("ok")){
                    MovDpPK movpk = new MovDpPK( lb.sscia(), pp.getProgramacionPlaPK().getSecuencia() , emp.getEmpleadosPK().getCodEmp(), codDp, 0 ); 
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
                          movdp.setGenerado("N");

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
                          movDpFacade.flush();
                          }else{
                JsfUtil.logs(null , "Surgio un error", "proceso upload Linea"+i+" Empleado sin planilla ",SB_readXLS.class,"ERROR");                        
                        }
                    }
                }
          }
            }catch(Exception ex){
                JsfUtil.logs(ex , "Surgio un error", "Proceso upload Linea"+i,SB_readXLS.class,"ERROR");            
            }
        }

    return "Cantida de registros insertados "+xx+" de "+total;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)  
    public String readPrestamos(String mas ) throws IOException, BiffException  {        
         String mensaje;
         try{
              this.inputWorkbook= new File(mas);
              Workbook w=null;       
              w = Workbook.getWorkbook(inputWorkbook);     
              Sheet sheet = w.getSheet(0);                 
              mensaje= createPrestamos(sheet);

         }   
         catch(Exception ex){
           return "error";          
      }   
           return "ok " +mensaje;  
    }    

 
    public String createPrestamos(Sheet sheet){   
            int xx=0; 
            int total=0; 
            
            
            String cod_emp="";
            Short cod_dp=0;
            int cod_presta=0;
            Short cuotas=0;
            BigDecimal valor_cuota=new BigDecimal( 0);
            Short frecuencia=0;
            String numRef="";            
            
            Empleados emp = null;
            LoginBean lb= new LoginBean();
            for (int i = 0; i < sheet.getRows(); i++) { 
             total++;

                for (int j = 0; j < sheet.getColumns(); j++) {              

                    Cell cell = sheet.getCell(j, i);
                    if(j==0){


                        cod_emp =  String.valueOf(cell.getContents());
                    }          
                    if(j==1){

                       cod_dp =Short.parseShort(cell.getContents());
                    }          
                    if(j==2){


                        numRef =  String.valueOf(cell.getContents());
                    }  
                    if(j==3){


                        frecuencia =  Short.parseShort(cell.getContents());
                    }    
                    if(j==4){


                        cuotas =  Short.parseShort(cell.getContents());
                    }      
                    if(j==5){


                        valor_cuota = new BigDecimal(cell.getContents());
                    }                          
                }
                 try{ 

                     if(cod_emp.length()>4){
                         emp  = empleadosFacade.findbyCodempref(cod_emp); 
                     }else{
                         int codemp = Integer.valueOf(cod_emp);
                          emp  = empleadosFacade.findbyCodemp(codemp); 
                     }


                        cod_presta= prestamosFacade.SeqNext();

                        PrestamosPK prestamoPk = new PrestamosPK(lb.sscia(),emp.getEmpleadosPK().getCodEmp(),cod_dp, cod_presta  ); 
                        Prestamos prestamo = new Prestamos();                         
                        
                        if(frecuencia==3){
                           valor_cuota = new BigDecimal(valor_cuota.floatValue()/2);
                           int vcuotas= cuotas*2;
                           cuotas = (short)vcuotas;
                        }    
                        prestamo.setMonto(valor_cuota.multiply(BigDecimal.valueOf(cuotas)));
                        prestamo.setSaldo(valor_cuota.multiply(BigDecimal.valueOf(cuotas)));                        
                        prestamo.setPrestamosPK(prestamoPk);
                        prestamo.setUsuario(lb.ssuser() );
                        prestamo.setFechaReg( lb.sdate());
                        prestamo.setFrecuencia(frecuencia);
                        prestamo.setVcuota(valor_cuota);
                        prestamo.setCuotas(cuotas);
                        
                        prestamo.setCuotasP(Short.parseShort("0"));
                        prestamo.setNumRef(numRef);
                        DeducPresta dp = deducPrestaFacade.findCodDeduc(cod_dp);

                        if (dp!=null){ 
                            xx++;
                              prestamosFacade.create(prestamo);  
                              movDpFacade.flush();                          
                        }
                    
              
                }catch(Exception ex){
                    JsfUtil.logs(ex , "Surgio un error", "Proceso upload Linea"+i,SB_readXLS.class,"ERROR");            
                }
            }

        return "Cantida de registros insertados "+xx+" de "+total;
        } 
}
