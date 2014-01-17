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
import com.entities.Mensaje;
import com.entities.MovDp;
import com.entities.MovDpFacade;
import com.entities.MovDpPK;
import com.entities.PlanillaHoras;
import com.entities.PlanillaHorasFacade;
import com.entities.PlanillaHorasPK;
import com.entities.ProgramacionPla;
import com.entities.ResumenAsistenciaFacade;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


@Stateless
public class SB_readXLS {
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
      
      
      try{
       
       
       this.inputWorkbook= new File(mas);
      
       
       int xx=0; 
     
       BigDecimal valor=null;
        LoginBean lb= new LoginBean();
        Workbook w=null;
       
        w = Workbook.getWorkbook(inputWorkbook);     
        Sheet sheet = w.getSheet(0);      
      
        for (int i = 0; i < sheet.getRows(); i++) { 
           xx++;           
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
          movdp.setMovDpPK(movpk);
          movdp.setUsuario(lb.ssuser() );
          movdp.setFechaReg( lb.sdate());   
          movdp.setValor(valor);
          Empleados emp  = empleadosFacade.findbyCodemp(movpk.getCodEmp());
          DeducPresta dp = deducPrestaFacade.findCodDeduc(movdp);
          if (dp!=null){              
              if (resumenAsistenciaFacade.ByEmp(movdp)!=null ){
                ProgramacionPla Vpla  = new ProgramacionPla(lb.sscia(),movdp.getMovDpPK().getSecuencia());                
                movdp.setEmpleados(emp); 
                movdp.setDeducPresta(dp);
                movdp.setProgramacionPla(Vpla);             
                movDpFacade.edit(movdp);  
                }
          }
          
         
          
      }   
      }catch(Exception ex){
          return "surgio un error";
          
      }
    
        return "ok";
  
      }
  

}
