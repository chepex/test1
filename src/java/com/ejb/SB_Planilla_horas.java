
package com.ejb;

import com.entities.DeducPresta;
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
import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;



@Stateless


public class SB_Planilla_horas {
    @EJB
    private EmpleadosFacade empleadosFacade;

    @EJB
    private PlanillaHorasFacade planillaHorasFacade;
    @EJB
    private MovDpFacade movDpFacade;        
    
      
    private File inputWorkbook;        
    private Mensaje msg = new Mensaje();
    
    
    ProgramacionPla programacionPla;


    public ProgramacionPla getProgramacionPla() {
        return programacionPla;
    }

    public void setProgramacionPla(ProgramacionPla programacionPla) {
        this.programacionPla = programacionPla;
    }



    
    public Mensaje validar_planilla_horas(ProgramacionPla programacionPla) {
	Calendar currentDate = Calendar.getInstance();
	if(programacionPla == null){
            msg.setTitulo("error");
            msg.setMensajes("debe selecionar una planilla");
            return msg;
        }
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
            LoginBean lb= new LoginBean();		
	    MovDpPK  movdppk = new MovDpPK(e.getPlanillaHorasPK().getCodCia(), 
		    e.getProgramacionPla().getProgramacionPlaPK().getSecuencia(),
		    e.getEmpleados().getEmpleadosPK().getCodEmp(),
		    e.getDeducPresta().getDeducPrestaPK().getCodDp() );	
	    MovDp movdp = new MovDp(movdppk);	    
	    movdp.setValor(e.getValor());
            movdp.setUsuario(lb.ssuser() );
            movdp.setFechaReg( lb.sdate());    
            movdp.setGenerado("N");  
	    movDpFacade.edit(movdp );			
	}	
	msg.setTitulo("ok");
	msg.setMensajes("Informacion traslada correctamente");
	msg.setDescripcion(planillahoras.size()+" Registros");	
	return msg;
    }

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)  
  public void read(String mas, ProgramacionPla programacionPla)  {  
      
      
      try{
       this.setProgramacionPla(programacionPla);
       borarXuser();
       this.inputWorkbook= new File(mas);
      

       int xx=0; 
     
       BigDecimal valor=null;
        LoginBean lb= new LoginBean();
        Workbook w=null;
       
        w = Workbook.getWorkbook(inputWorkbook);     
        Sheet sheet = w.getSheet(0);      
      
        for (int i = 0; i < sheet.getRows(); i++) { 
           xx++;           
           PlanillaHorasPK phpk = new PlanillaHorasPK(); 
           PlanillaHoras ph = new PlanillaHoras(); 
           phpk.setSecuencia(programacionPla.getProgramacionPlaPK().getSecuencia());           
           phpk.setCodCia(lb.sscia());
            for (int j = 0; j < sheet.getColumns(); j++) {              
                /*secuencia,cod_cia,cod_emp,cod_dp,valor*/
                Cell cell = sheet.getCell(j, i);
                if(j==0){
                  
                    
                    phpk.setCodEmp( Integer.parseInt(cell.getContents()));
                }          
                if(j==1){
                   
                    phpk.setCodDp(Short.parseShort(cell.getContents()));
                }          
                if(j==2){
               
                    
                    valor = new BigDecimal(cell.getContents());
                }                                
            }    
          ph.setPlanillaHorasPK(phpk);
          ph.setUsuario(lb.ssuser() );
          ph.setFechaReg( lb.sdate());   
          ph.setValor(valor);
          Empleados emp  = empleadosFacade.findbyCodemp(phpk.getCodEmp());
          ProgramacionPla Vpla  = new ProgramacionPla(lb.sscia(),ph.getPlanillaHorasPK().getSecuencia());
          DeducPresta dp = new DeducPresta(lb.sscia(),ph.getPlanillaHorasPK().getCodDp());
          ph.setEmpleados(emp); 
          ph.setDeducPresta(dp);
          ph.setProgramacionPla(Vpla);   
          
          planillaHorasFacade.create(ph); 
      }
    
   
      
    
        msg.setTitulo("ok");
        msg.setMensajes("Archivo cargado correctamente ");
       
      }catch(Exception ex){
          
          
      }
      
            
  }
  
  
  
  

  

     public void borarXuser(){      
     List<PlanillaHoras> planillaHoras = this.planillaHorasFacade.findByFiltro(programacionPla);
      if(planillaHoras!= null){
        for( PlanillaHoras PX : planillaHoras ){                                                                               
            this.planillaHorasFacade.remove(PX);
         }
       }     
  }



}

