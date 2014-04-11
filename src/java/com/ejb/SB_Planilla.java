package com.ejb;
import com.entities.DeptosMov;
import com.entities.DeptosMovFacade;
import com.entities.DetEmpleado;
import com.entities.DetEmpleadoFacade;
import com.entities.Dmgdetalle;
import com.entities.DmgdetalleFacade;
import com.entities.DmgdetallePK;
import com.entities.Dmgpoliza;
import com.entities.DmgpolizaFacade;
import com.entities.DmgpolizaPK;
import com.entities.Empleados;
import com.entities.EmpleadosFacade;
import com.entities.LoginBean;
import com.entities.Mensaje;
import com.entities.MovDp;
import com.entities.MovDpFacade;
import com.entities.Parametros;
import com.entities.ParametrosFacade;
import com.entities.Planilla;
import com.entities.PlanillaAfp;
import com.entities.PlanillaAfpFacade;
import com.entities.PlanillaAfpPK;
import com.entities.PlanillaFacade;
import com.entities.PlanillaIsss;
import com.entities.PlanillaIsssFacade;
import com.entities.PlanillaIsssPK;
import com.entities.Prestamos;
import com.entities.PrestamosFacade;
import com.entities.ProgramacionPla;
import com.entities.ProgramacionPlaFacade;
import com.entities.ResumenAsistencia;
import com.entities.ResumenAsistenciaFacade;
import com.entities.util.JsfUtil;
import com.entities.util.ManejadorFechas;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_Planilla {
    @EJB
    private DetEmpleadoFacade detEmpleadoFacade;
    @EJB
    private PlanillaAfpFacade planillaAfpFacade;
    
    @EJB
    private EmpleadosFacade empleadosFacade;
    @EJB
    private PlanillaIsssFacade planillaIsssFacade;
    @EJB
    private ParametrosFacade parametrosFacade;
    @EJB
    private PrestamosFacade prestamosFacade;
    @EJB
    private DeptosMovFacade deptosMovFacade;
    @EJB
    private DmgdetalleFacade dmgdetalleFacade;    
    @EJB
    private DmgpolizaFacade dmgpolizaFacade;
    @EJB
    private MovDpFacade movDpFacade; 
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
    
    
    Mensaje msg1 = new Mensaje();
    String mensaje ;
    Dmgpoliza pl;
    Dmgdetalle dt;    
    Short cta1;
    Short cta2;
    Short cta3;
    Short cta4;
    Short cta5;
    int correlativo = 0;
    private int cantidad;
    public int ix=0;
    
    BigDecimal vliquido;

 
    
    public String Generar()  {   
        String totales="";
        List<ProgramacionPla> iterador=  programacionPlaFacade.findByEstado("P");        
        if(iterador== null){
            mensaje = "ok";
            
           return  mensaje;
        }
          for( ProgramacionPla e : iterador ){ 
               borrar(e); 
               List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(e );
                ProcesoAcero();
              for( ResumenAsistencia ra : iterator ){ 
                  actualizarNegativos(ra);
                  if(ra.getEmpleados().getEmpleadosPK().getCodEmp()==2526){
                  System.out.print("aa");
                  }
                  if(e.getTiposPlanilla().getPromedio().equals("M")){                      
                     calculos.promedioMensual(ra);    
                  } 
                  if(e.getTiposPlanilla().getPromedio().equals("Q")){
                     calculos.promedioQuincenal(ra);  
                  } 
                  if(e.getTiposPlanilla().getPromedio().equals("V")){
                     calculos.promedioVacacion(ra);  
                  } 
                  
                  
                  if(e.getTiposPlanilla().getPrestamos().equals("S")){
                      if(e.getSegmentar()==0){
                          calculos.CalcularPrestamos(ra);  
                      }else{
                      calculos.CalcularPrestamosSegmento(ra,e);
                      }
                      
                     
                  }  
                  if(Integer.parseInt(ra.getDias())==0){
                    calculos.VComVaca(ra);                  
                  }
                  
                  if(e.getTiposPlanilla().getAdicional().equals("S")){
                     calculos.CalcularEspecial(ra);
                  } 
                  if(e.getTiposPlanilla().getLey().equals("S")){
                     calculos.CalcularLey(ra);                
                  }                   
                  if(e.getTiposPlanilla().getLiquido().equals("S")){
                     calculos.CalcularLiqRecibir(ra); 
                  }      
                  if(e.getRecalculo().equals("S")){
                     calculos.recalculo(ra); 
                  } 
                  
                  
                   
                 this.cantidad=this.cantidad+1;
                  Proceso(cantidad,iterator.size());
                  NomPla(e.getTiposPlanilla().getNomTipopla());
                  mensaje ="ok+";
              }
                  mensaje ="ok";
                    
                    totales += e.getTiposPlanilla().getNomTipopla();
               
              }
            
         return mensaje;
     }	

 public void Proceso(int cant, int total){
     
     int actual = cant*100/total;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
    session.setAttribute("SSPROCESO", actual);    
 }

  public void NomPla(String nom){
     
     
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
    session.setAttribute("SSNOMPLA", nom);    
 }
  public void ProcesoAcero(){
     cantidad =0;
      int actual = 0;
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
    session.setAttribute("SSPROCESO", 0);    
 }
    

 
    
    
    public String Cerrar()  {   
        
        List<ProgramacionPla> iterador=  programacionPlaFacade.findByEstado("P");        
        if(iterador.isEmpty()){
            
           return  "error";
        }
          for( ProgramacionPla e : iterador ){
              actualizarPrestamos(e);
              crearPartida(e);
              //GenerarIsss(e);
              GenerarAfp(e);
              //PlanillaHistorial(e);              
              actualizarStatus(e);
               
          }            
         return "ok";
     }	    
    
    public String Negativos()  {   
        
        List<ProgramacionPla> iterador=  programacionPlaFacade.findByEstado("P");        
        if(iterador.isEmpty()){
            
           return  "error";
        }
          for( ProgramacionPla e : iterador ){
            
          }            
         return "ok";
     }	
    

    
    public String GenerarAfp(ProgramacionPla programacionPla){
            
            List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(programacionPla );
            for( ResumenAsistencia ra : iterator ){          
                  CalculoAfp(ra);
              }
        return "ok";
    }     
    
    
  public String CalculoIsss(Planilla pla){
        
        try{
              if(pla.getResumenAsistencia().getEmpleados().getEmpleadosPK().getCodEmp()==3044){
             System.out.println("aa");
           }
        Parametros p= parametrosFacade.findByNombre("HORAS_LABORALES");
        Parametros nPATRONAL= parametrosFacade.findByNombre("NPATRONAL_ISSS");
        Parametros  p2=parametrosFacade.findByNombre("PATRONALISSS");  
         
        String NUMPATRONAL = p2.getValorTxt();
        LoginBean lb= new LoginBean();	
      
        List<Planilla> lplanilla =planillaFacade.findByAnioMes2(pla.getResumenAsistencia());
        BigDecimal devengado = BigDecimal.valueOf(devengoIsss(lplanilla) );
        String dias =  devengoDias(lplanilla) ;
        Empleados emp= empleadosFacade.findbyCodemp(pla.getPlanillaPK().getCodEmp());
        PlanillaIsssPK planillaIsssPK =new PlanillaIsssPK();
            planillaIsssPK.setCodCia(emp.getEmpleadosPK().getCodCia());
            planillaIsssPK.setAnio(pla.getProgramacionPla().getAnio());
            planillaIsssPK.setMes(pla.getProgramacionPla().getMes());
            planillaIsssPK.setCodEmp(pla.getEmpleados().getEmpleadosPK().getCodEmp());        
            //PlanillaIsss pp = planillaIsssFacade.find(planillaIsssPK);
            //if(pp==null){
                PlanillaIsss planillaIsss =new PlanillaIsss(planillaIsssPK);
                planillaIsss.setNombre(pla.getEmpleados().getNombreIsss());         
                planillaIsss.setNoAfilacion(pla.getEmpleados().getNumIgss());
                planillaIsss.setCorrelativo(emp.getDepartamentos().getCorrelIsss());
               // planillaIsss.setCorrelativo((short)1);
                planillaIsss.setDiasRemunerados(dias);
                planillaIsss.setNoPatronal(NUMPATRONAL);
                planillaIsss.setHorasJornada(p.getValorTxt());
                planillaIsss.setSalDebengado( devengado);            
                planillaIsss.setUsuario(lb.ssuser());
                planillaIsss.setFechaReg(lb.sdate());        
                planillaIsss.setNoPatronal(nPATRONAL.getValorTxt());
                planillaIsss.setEmpleados(emp);
                if(pla.getResumenAsistencia().getObservaciones()!= null){
                    planillaIsss.setCodObserva(pla.getResumenAsistencia().getObservaciones().getIsss());
                }
                try{
                planillaIsssFacade.edit(planillaIsss);
               // planillaIsssFacade.flush();
             //   planillaIsssFacade.refresh(planillaIsss);
                }catch(ConstraintViolationException  ex){                                                         
                        JsfUtil.logs(ex , "Surgio un error", "Proceso CalculoIsss "+pla.getResumenAsistencia().getEmpleados().getEmpleadosPK().getCodEmp(),SB_Planilla.class,"ERROR");     
                }        
          //  }     
           /* 
            plaIsss.setDiasRemunerados( dias.toString());
            
            
            if(ra.getObservaciones()!= null){ 
                plaIsss.setCodObserva(ra.getObservaciones().getIsss());
            }      
            if( devengado.floatValue() >  TOPE_ISSS.getValorInt().floatValue() ){
                plaIsss.setSalDebengado(TOPE_ISSS.getValorInt());                
            }else{
                plaIsss.setSalDebengado(devengado);                
            }            
            planillaIsssFacade.edit(plaIsss); 
            planillaIsssFacade.flush();
           planillaIsssFacade.refresh(plaIsss);
        */

        
        
        
        }
         catch(ConstraintViolationException  ex){                                                         
                    JsfUtil.logs(ex , "Surgio un error", "Proceso CalculoIsss "+pla.getEmpleados().getEmpleadosPK().getCodEmp(),SB_Planilla.class,"ERROR");     
                }   
               catch(Exception ex){                                                         
                   JsfUtil.logs(ex , "Surgio un error", "Proceso CalculoIsss "+pla.getEmpleados().getEmpleadosPK().getCodEmp(),SB_Planilla.class,"ERROR");    
                }    
           
        return "ok";
    }
  
  
    public String CalculoAfp(ResumenAsistencia ra){
        
        try{
        Parametros p= parametrosFacade.findByNombre("HORAS_LABORALES");
        
        
   
        
        LoginBean lb= new LoginBean();	
        List<Planilla> planillas = planillaFacade.findByMes(ra ) ;
        BigDecimal devengado = BigDecimal.valueOf( devengoIsss(planillas));
        String dias =  devengoDias(planillas) ;
        List<PlanillaAfp> plaAfp = planillaAfpFacade.findByEmp(ra);
        Empleados emp= empleadosFacade.findbyCodemp(ra.getResumenAsistenciaPK().getCodEmp());
        DetEmpleado afp = detEmpleadoFacade.findByAfp(emp);
        
        String estadoCivil= "";
        if (emp.getEstadoCivil()=="A"){
            estadoCivil= "U";
        }else{
            estadoCivil= emp.getEstadoCivil();
        }
        String cod_afp ="";
        
        if(afp.getDetEmpleadoPK().getCodDp() == 64){
            cod_afp="ISS";
        } 
        if(afp.getDetEmpleadoPK().getCodDp() == 65){
            cod_afp="COF";
        } 
        if(afp.getDetEmpleadoPK().getCodDp() == 66){
            cod_afp="MAX";
        }         
        if(plaAfp.isEmpty()){
            
            PlanillaAfp planillaAfp =new PlanillaAfp();
            PlanillaAfpPK planillaAfpPK =new PlanillaAfpPK();
            planillaAfpPK.setCodCia(ra.getResumenAsistenciaPK().getCodCia());
            planillaAfpPK.setAnio(ra.getProgramacionPla().getAnio());
            planillaAfpPK.setMes(ra.getProgramacionPla().getMes());
            planillaAfpPK.setCodEmp(ra.getResumenAsistenciaPK().getCodEmp());
            planillaAfp.setPlanillaAfpPK(planillaAfpPK);
            planillaAfp.setNumAfp(emp.getNupAfp());
            planillaAfp.setApellidos(emp.getApellidos());
            planillaAfp.setApCasada(emp.getApCasada());
            planillaAfp.setNombres(emp.getNombres());
            planillaAfp.setDocumento("DUI");
            planillaAfp.setDocumento(emp.getCedula());
            planillaAfp.setEstadoCivil(estadoCivil);
            if(afp.getDetEmpleadoPK().getCodDp()==115){
            planillaAfp.setPensionado("x");
            }
            planillaAfp.setAfp(cod_afp);
            planillaAfp.setFechaIngreso(emp.getFecIngreso());
            planillaAfp.setDevengado(devengado);
            planillaAfp.setHoras(p.getValorTxt());
            planillaAfp.setDias(dias);
            if(ra.getObservaciones().getAfp()==3){
                planillaAfp.setIncapacidad("X");
            }
            
            

            planillaAfpFacade.create(planillaAfp);
            planillaAfpFacade.flush();
            planillaAfpFacade.refresh(planillaAfp);
        }else{
            Parametros TOPE_AFP= parametrosFacade.findByNombre("TOPE_AFP");                        
            plaAfp.get(0).setDias( dias.toString());            
            if( devengado.floatValue() >  TOPE_AFP.getValorInt().floatValue() ){
                plaAfp.get(0).setDevengado(TOPE_AFP.getValorInt());                
            }else{
                plaAfp.get(0).setDevengado(devengado);                
            }            
            planillaAfpFacade.edit(plaAfp.get(0)); 
            planillaAfpFacade.flush();
            //planillaAfpFacade.refresh(plaAfp);
        } 

        
        
        
        
        }catch(Exception ex){
             JsfUtil.logs(ex , "Surgio un error", "Proceso CalculoAfp"+ra.getEmpleados().getEmpleadosPK().getCodEmp(),SB_Planilla.class,"ERROR"); 
        }
        return "ok";
    }
    
    public Float devengoIsss(List<Planilla> lplanilla){
        float total =0;  
        Parametros param= parametrosFacade.findByNombre("TOPE_ISSS");    
            for(Planilla pla : lplanilla){
              total = total + pla.getNeto().floatValue();
            } 
            
            if(total>  param.getValorInt().floatValue()){
                total=  param.getValorInt().floatValue();
            }
        return total;

    }
    
    public String devengoDias(List<Planilla> lplanilla){
        int total =0;

       
            for(Planilla pla : lplanilla){
              total = total + Integer.parseInt(pla.getDias());
            } 
            
        return Integer.toString(total); 

    }    
    public String borrar(ProgramacionPla programacionPla){
        try{
                mensaje =  sB_ProgramacionPla.validarEstado(programacionPla);
                if(mensaje.equals("ok")){
                    
                    List<Planilla> iterator =  planillaFacade.findByPk(programacionPla);
                    if(iterator != null){
                        for( Planilla p : iterator ){                             
                            planillaFacade.remove(p);  
                            planillaFacade.flush();
                        }
                    } 
                    
                    List<MovDp> iterator2 =  movDpFacade.findSecuencia(programacionPla);
                    if(iterator2 != null){
                        for( MovDp p : iterator2 ){                             
                            movDpFacade.remove(p); 
                            movDpFacade.flush();
                        }
                    }                                   
                }   
            return "ok";
        }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso borrar",SB_Asistencia.class,"ERROR");             
            return "error";
            
        }
    }    
    
    
    public String crearPartida(ProgramacionPla programacionPlax){     
        try{
            generarPoliza(programacionPlax);                             
            List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(programacionPlax );
            this.vliquido=new BigDecimal(0);
            for( ResumenAsistencia ra : iterator ){          
               
                    salarios(ra);                    
                    liquido(ra);                                
                    mov(ra); 
              }
                               
             part_liquido();
            redondearPartida();
            return "ok";
        }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso crearPartida",SB_Asistencia.class,"ERROR");             
            return "error";            
            
        }
    }        

    public String PlanillaHistorial(ProgramacionPla programacionPlax){                 
        try{        
            List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(programacionPlax );
            for( ResumenAsistencia ra : iterator ){          
                   /*1 obtener la planilla*/
                   /*2 obetner los movimientos de ley*/
                   /*3 */
              }
            return "ok";
        }catch(Exception ex){
               JsfUtil.logs(ex , "Surgio un error", "Proceso crearPartida",SB_Asistencia.class,"ERROR");             
                return "error";      
        }        
    }        

    
    public void generarPoliza(ProgramacionPla programacionPlax) {
        try{
        LoginBean lb= new LoginBean();		 
        Date now = ManejadorFechas.NowDate();
        short codCia = lb.sscia();
        short id= dmgpolizaFacade.Sequence("DGMPOLOZA_PLA");            
        DmgpolizaPK pk= new DmgpolizaPK(codCia, "PL",id ,now);
        pl = new Dmgpoliza();
        pl.setDmgpolizaPK(pk);
        String ref = String.valueOf(programacionPlax.getProgramacionPlaPK().getSecuencia());        
        pl.setNumReferencia(ref);        
        pl.setUsuario(lb.ssuser());
        pl.setFechaCreacion(now);
        pl.setConcepto("Planilla "+programacionPlax.getTiposPlanilla().getNomTipopla()+" Quincena "+programacionPlax.getNumPlanilla() +" "+now.getMonth()+"/"+now.getYear());
        savePoliza();    
        }catch(Exception ex)        
        {
         
        JsfUtil.logs(ex , "Surgio un error", "Proceso generarPoliza",SB_Asistencia.class,"ERROR"); 
        }
    }
    

    
    public void savePoliza(){
        dmgpolizaFacade.create(pl);  
    }
    
    
    

    
    public void salarios(ResumenAsistencia ra){
        try{
        //calculos.inicializar(ra);
        Planilla pla= planillaFacade.findByEmp(ra);
        BigDecimal salario = BigDecimal.valueOf(pla.getBruto().floatValue());       
        if(salario.intValue()>0){
          
             cta1= ra.getEmpleados().getDepartamentos().getScta1();
             cta2= ra.getEmpleados().getDepartamentos().getScta2();
             cta3= ra.getEmpleados().getDepartamentos().getScta3();
             cta4= ra.getEmpleados().getDepartamentos().getScta4();
             cta5= ra.getEmpleados().getDepartamentos().getScta5();
            String py = ra.getEmpleados().getDepartamentos().getProyecto();                
            DmgdetallePK pk = new DmgdetallePK(pl.getDmgpolizaPK().getCodCia(),pl.getDmgpolizaPK().getTipoDocto(), pl.getDmgpolizaPK().getNumPoliza(),pl.getDmgpolizaPK().getFecha(), 0);
            dt = new Dmgdetalle(pk);
            dt.setCta1(cta1);
            dt.setCta2(cta2);
            dt.setCta3(cta3);
            dt.setCta4(cta4);
            dt.setCta5(cta5);
            dt.setProyecto(py);
            dt.setConcepto(": Salarios Planilla ="+ra.getResumenAsistenciaPK().getSecuencia()  );                
            dt.setAbono(BigDecimal.ZERO); 
            dt.setCargo(salario );       
            validaCuenta(dt);
        }
        }catch(Exception ex){
         
        JsfUtil.logs(ex , "Surgio un error", "Proceso salarios",SB_Asistencia.class,"ERROR"); 
        }
    }

  
   
    public void liquido(ResumenAsistencia ra){
        try{
        Planilla pla= planillaFacade.findByEmp(ra);
        BigDecimal liquido = BigDecimal.valueOf(pla.getLiquido().floatValue());      
        if(liquido.intValue()>0){
            this.vliquido = BigDecimal.valueOf(vliquido.floatValue() + liquido.floatValue());            
         }
        }catch( Exception ex){
           
        JsfUtil.logs(ex , "Surgio un error", "Proceso liquido",SB_Asistencia.class,"ERROR"); 
        }
        
    } 
    
   public void part_liquido(){
        try{        
            
            Parametros  p=parametrosFacade.findByNombre("CUENTA_LIQUIDO");            
            String colores = p.getValorTxt();
            String[] arrayCuentas = colores.split(",");           
            
             cta1= Short.valueOf( arrayCuentas[0]) ;
             cta2= Short.valueOf( arrayCuentas[1]) ;
             cta3= Short.valueOf( arrayCuentas[2]) ;
             cta4= Short.valueOf( arrayCuentas[3]) ;
             cta5= Short.valueOf( arrayCuentas[4]) ;
            
            DmgdetallePK pk = new DmgdetallePK(pl.getDmgpolizaPK().getCodCia(),pl.getDmgpolizaPK().getTipoDocto(), pl.getDmgpolizaPK().getNumPoliza(),pl.getDmgpolizaPK().getFecha(),0);
            dt = new Dmgdetalle(pk);
            dt.setCta1(cta1);
            dt.setCta2(cta2);
            dt.setCta3(cta3);
            dt.setCta4(cta4);
            dt.setCta5(cta5);
            dt.setProyecto("2");
            dt.setConcepto("Liquido  planilla " );        
            dt.setAbono(vliquido); 
            dt.setCargo(BigDecimal.ZERO);
            validaCuenta(dt);
         
        }catch( Exception ex){
           
        JsfUtil.logs(ex , "Surgio un error", "Proceso PART_liquido",SB_Asistencia.class,"ERROR"); 
        }
        
    } 
   
    public void mov(ResumenAsistencia ra){
        try{
        List<MovDp> deduc =  movDpFacade.findByCodEmp(ra);
          Parametros  p=parametrosFacade.findByNombre("DETALLE_PARTIDA_CONTA");            
            String detalle = p.getValorTxt();
         for( MovDp movDp : deduc ){  
            String concepto="";
               BigDecimal cargo = BigDecimal.ZERO;
               BigDecimal abono = BigDecimal.ZERO;
               BigDecimal negativo = BigDecimal.valueOf(-1);
               
               BigDecimal valor = movDp.getValor(); 
               System.out.print("mov->"+movDp.getMovDpPK()+"emp->"+ra.getEmpleados()+"dp"+movDp.getDeducPresta().getCatDp()+"depto"+ra.getEmpleados().getDepartamentos());
                if(movDp.getDeducPresta().getFactor().floatValue()<0){
                          valor = valor.multiply(negativo);
                }
               if(valor.intValue()>0){                    
                   if(movDp.getDeducPresta().getCatDp().getSumaResta().equals("R")){
                        cta1= movDp.getDeducPresta().getCta1();
                        cta2= movDp.getDeducPresta().getCta2();
                        cta3= movDp.getDeducPresta().getCta3();
                        cta4= movDp.getDeducPresta().getCta4();
                        cta5= movDp.getDeducPresta().getCta5();   
                        cargo = BigDecimal.ZERO;
                        abono = valor;
                        concepto="DEDUCCION ";
                        
                   }                   
                   if(movDp.getDeducPresta().getCatDp().getSumaResta().equals("S")){
                        DeptosMov Cuentas= deptosMovFacade.findCatdp( movDp.getDeducPresta().getCatDp(), ra.getEmpleados().getDepartamentos());
                        cta1= Cuentas.getBcta1();
                        cta2= Cuentas.getBcta2();
                        cta3= Cuentas.getBcta3();
                        cta4= Cuentas.getBcta4();
                        cta5= Cuentas.getBcta5();
                        if(movDp.getDeducPresta().getFactor().floatValue()<0){
                          
                            concepto="DEDUCCION ";
                            cargo = BigDecimal.ZERO;  
                            abono = valor;                                   
                        }else{
                            cargo = valor;
                            abono = BigDecimal.ZERO;         
                            concepto="PRESTACION ";
                        }
                        
                        
                   }

                String py = ra.getEmpleados().getDepartamentos().getProyecto();
                DmgdetallePK pk = new DmgdetallePK(pl.getDmgpolizaPK().getCodCia(),pl.getDmgpolizaPK().getTipoDocto(), pl.getDmgpolizaPK().getNumPoliza(),pl.getDmgpolizaPK().getFecha(), 0);                
                dt = new Dmgdetalle(pk);
                dt.setCta1(cta1);
                dt.setCta2(cta2);
                dt.setCta3(cta3);
                dt.setCta4(cta4);
                dt.setCta5(cta5);
                if(detalle.equals("S")){
                    dt.setProyecto( ra.getEmpleados().getDepartamentos().getProyecto()); 
                }else{
                     if(dt.getCta1()==1 || dt.getCta1() == 2  ){
                        dt.setProyecto( String.valueOf(cta1));     
                    }else{
                        dt.setProyecto( ra.getEmpleados().getDepartamentos().getProyecto());         
                    }
                     
                }
                
                
                dt.setConcepto("planilla "+ra.getResumenAsistenciaPK().getSecuencia()+concepto + movDp.getDeducPresta().getDescripcion() );        
                dt.setAbono(abono);                
                dt.setCargo(cargo);                        
                validaCuenta(dt);   
               }
         }    
        }catch(Exception ex){
         String  emp =  String.valueOf(ra.getEmpleados().getEmpleadosPK().getCodEmp()) ;
        JsfUtil.logs(ex , "Surgio un error", "Proceso mov",SB_Asistencia.class,"ERROR"); 
        }
    }   
     
    
    public void validaCuenta( Dmgdetalle dt ){   
        try{
       Dmgdetalle dtt= dmgdetalleFacade.findByCuenta(dt);
       BigDecimal a  = new BigDecimal(0);
       BigDecimal c  = new BigDecimal(0);
       if (dtt != null){
           if( dt.getAbono().floatValue()>0 ) {
               a = new BigDecimal (dt.getAbono().floatValue() + dtt.getAbono().floatValue());              
               dtt.setAbono(a );  
           }
           if(dt.getCargo().floatValue()>0){
               c = new BigDecimal ( dt.getCargo().floatValue() + dtt.getCargo().floatValue());              
               dtt.setCargo(c );
           }  
          dmgdetalleFacade.edit(dtt);       
       }else{         
           correlativo++;
           dt.getDmgdetallePK().setCorrelat(correlativo);
           dmgdetalleFacade.create(dt);
       }
        }catch(Exception ex){
          
        JsfUtil.logs(ex , "Surgio un error", "Proceso validaCuenta",SB_Asistencia.class,"ERROR"); 
        }
    }
    
    public void redondearPartida(){
        try{
            Date now = ManejadorFechas.NowDate();
            List <Dmgdetalle>  Ldmgdetalle =  dmgdetalleFacade.findByFecha(now);
            for( Dmgdetalle detalle : Ldmgdetalle ){           
                BigDecimal  c = new BigDecimal (0);
                BigDecimal  a = new BigDecimal (0);
                if(detalle.getAbono().floatValue()>0){
                    a= detalle.getAbono();
                    a= a.setScale(2, RoundingMode.CEILING);
                    detalle.setAbono(a);
                }
                if(detalle.getCargo().floatValue()>0){
                   c= detalle.getCargo();
                   c= c.setScale(2, RoundingMode.CEILING);
                   detalle.setCargo(c);
                }         
                if(detalle.getCargo().floatValue()>0 || detalle.getAbono().floatValue()>0 ){
                    dmgdetalleFacade.edit(detalle);
                }

            }    
        }catch(Exception ex){     
        JsfUtil.logs(ex , "Surgio un error", "Proceso redondearPartida",SB_Asistencia.class,"ERROR"); 
        }
    }
    
    public void actualizarPrestamos(ProgramacionPla prog){
       List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(prog );
            for( ResumenAsistencia ra : iterator ){  
                List<MovDp>  lmdp =  movDpFacade.findByCat("Prestamos", ra);
                for( MovDp m : lmdp ){
                     float saldo  =0;
                     float vcuota =0;
                     Prestamos p = prestamosFacade.findByPk(m);
                     /*Si es una planilla normal se suman 1 a cuotas pagadas y se resta el valor de la cuota al saldo*/
                     if(prog.getTiposPlanilla().getPromedio().equals("N")){  
                        int cuotas =  (p.getCuotasP()+1) ;
                        saldo = p.getSaldo().floatValue() - m.getValor().floatValue();
                        p.setCuotasP(cuotas);
                       
                     }else{
                       /*Si es una planilla vaca se resta el valor de la cuota al saldo*/
                       vcuota=(m.getValor().floatValue() * prog.getSegmentar())/100;
                       saldo = p.getSaldo().floatValue() - vcuota;
                     }
                      p.setSaldo( BigDecimal.valueOf(saldo));
                    prestamosFacade.edit(p);
                }
                
                
                
            }                                                        
    }  
    
    
    
    public String actualizarStatus(ProgramacionPla prog){
        prog.setStatus("C");
        programacionPlaFacade.edit(prog);
        programacionPlaFacade.flush();
       // programacionPlaFacade.refresh(prog);
        return "ok";
    }    
    
    public void actualizarNegativos(ResumenAsistencia ra){
    
       List<MovDp> lmovdp = movDpFacade.findByCodEmp(ra);
       for( MovDp mov : lmovdp ){ 
           if(mov.getPendiente() != null){
               //BigDecimal neg = BigDecimal.valueOf(-1);
               mov.setValor(mov.getValor().add(mov.getPendiente()));
               mov.setPendiente(null);
               movDpFacade.edit(mov);
               movDpFacade.flush();
           }
        }
                                                                    
    }
    
    public String generarTxt(int correlativo, ProgramacionPla ppla){
        
          String encabezado="";
          String Campo="B";
          String vPlan="4177";
          String Plan =   vPlan +"0000".substring(vPlan.length());
          String vCorrelativo= String.valueOf(correlativo);
          String Correlativo =   "00000".substring(vCorrelativo.length())+vCorrelativo;
          String vNit= "";
          String Nit=  "                    ".substring(vNit.length())+vNit;
          String anio= "";
          String vmes= "";                 
          String vdias= "";
          String vtrans = "";
          String trans  = "     ".substring(vtrans.length())+vtrans; 
          String vdescripcion = "";
          String descripcion =  "                              ".substring(vdescripcion.length())+vdescripcion; 
          String Esp = " ";
          String vnombre = "";
          String nombre =  "                              ".substring(vnombre.length())+vnombre; 
          String vCuenta = "";
          String Cuenta =  "     ".substring(vCuenta.length())+vCuenta; 
          
          BigDecimal total = new BigDecimal(0);
          BigDecimal bd2 = new BigDecimal("100");
          int id= 0;           
          Calendar calendar = Calendar.getInstance();
          
          
          List<Planilla> lpla= planillaFacade.findByStatus();
          int cort= 1;
          String detalle = "";
           for( Planilla p : lpla ){               
               try{
                   
               if (!p.getEmpleados().getCodBanco().equals("0")){
                   if (p.getEmpleados().getCodBanco().equals("14")){
                       if(p.getLiquido().floatValue()>0){
                           
                           if(id==1){
                               anio = String.valueOf(ppla.getAnio());
                               vmes = String.valueOf(ppla.getMes());
                               calendar.setTime(ppla.getFechaPago());
                               vdias = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                               
                           }
                           detalle += txtDetalle(p,cort++,vCorrelativo,ppla);
                           total = total.add(p.getLiquido().multiply(bd2)) ;
                           id++;
                 
                           
                       }
                       
                       
                    }
               }
               
               
               
               }catch(Exception ex){
                   System.out.println("---->"+ex);
                   return "error empleado  ="+p.getEmpleados().getEmpleadosPK().getCodEmp();
               }
                   
                   
                   
               
             
           }
           
           try{
           String mes = "00".substring(vmes.length())+vmes; 
           String dias = "00".substring(vdias.length())+vdias;            
           String monto ="";
           String Id= String.valueOf(id);
           Id =   "     ".substring(Id.length() )+id;
              
           monto =String.valueOf(total.intValue() ) ;
           monto = "             ".substring(monto.length())+monto;  
           encabezado+=Campo;
           encabezado+=Plan;
           encabezado+=Correlativo;
           encabezado+=Nit;
           encabezado+=trans;
           encabezado+=anio;
           encabezado+=mes;
           encabezado+=dias;
           encabezado+=monto;
           encabezado+=Id;          
           encabezado+="\r\n";
           /*encabezado+=Esp;
           encabezado+=nombre;
           encabezado+=Cuenta;
           encabezado+="\r\n";
           */
           //encabezado+="\n";  
           //encabezado+=System.getProperty("line.separator");
          System.out.println(detalle);
        encabezado+=detalle;
          }catch(Exception ex){
             JsfUtil.logs(ex , "Surgio un error", "Proceso Totales TXT ",SB_Asistencia.class,"ERROR"); 
             System.out.println("---->"+ex);
                      return "";
          }
        return encabezado;
    }
    
    public String txtDetalle(Planilla pl,int ct,String corelativo,ProgramacionPla ppla)
    {
        
        
        try{
          float valor= pl.getLiquido().floatValue()*100;
          DecimalFormat df = new DecimalFormat("###########");
          
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(ppla.getFechaPago());
          String dia_ = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
          String detalle="";
          String Campo="T";
          String vPlan="4177";
          String Plan = vPlan +"0000".substring(vPlan.length());
          String vCorrelativo= corelativo ;
          String Correlativo =   "00000".substring(vCorrelativo.length())+vCorrelativo;
          String vNit= pl.getEmpleados().getNumNit();
          String Nit=  vNit+"                    ".substring(vNit.length());
          String vtrans = String.valueOf(ct);
          String trans  = "     ".substring(vtrans.length())+vtrans; 
          String Id=  "     ";
          String anio= String.valueOf(ppla.getAnio());
          String vmes= String.valueOf(ppla.getMes());
          String mes = "00".substring(vmes.length())+vmes;          
           
          String dias = "00".substring(dia_.length())+dia_;                              
          String vmonto= String.valueOf(df.format(valor)) ;
          String monto = "             ".substring(vmonto.length())+vmonto;     
          String DescNum="";
          if(pl.getProgramacionPla().getNumPlanilla().intValue()==1) {
                DescNum="1ra Q ";     
             } else{
                 DescNum="2da Q ";
            }
          if(pl.getProgramacionPla().getTiposPlanilla().getPromedio().equals("V")){
                 DescNum="VACACION ";
          }
                 
          String vdescripcion ="SALARIOS "+DescNum+  JsfUtil.Meses(pl.getProgramacionPla().getMes()) +""+ pl.getProgramacionPla().getAnio();
          vdescripcion = JsfUtil.truncate(vdescripcion,30);         
          String descripcion =  vdescripcion+"                              ".substring(vdescripcion.length()); 
          String Esp = " ";
          String vnombre = pl.getEmpleados().getNombres()+" "+pl.getEmpleados().getApellidos();
          vnombre = JsfUtil.truncate(vnombre,30);
          String nombre = vnombre+"                              ".substring(vnombre.length()); 
          String vCuenta = pl.getEmpleados().getCtaBancaria().replace("-","");
          String Cuenta =  "         ".substring(vCuenta.length())+vCuenta; 
          detalle+=Campo;
          detalle+=Plan;
          detalle+=Correlativo;
          detalle+=Nit;
          detalle+=trans;
          detalle+=anio;
          detalle+=mes;
          detalle+=dias;
          detalle+=monto;
          detalle+=Id;
          detalle+=descripcion;
          detalle+=Esp;
          detalle+=nombre;
          detalle+=Cuenta;
          //detalle+="\n";
          detalle+="\r\n";
           System.out.println(detalle);
          //detalle+=System.getProperty("line.separator");
           return detalle;
          }catch(Exception ex){
                System.out.println("---->"+ex);
                    JsfUtil.logs(ex , "Surgio un error", "Proceso DETALLE TXT "+pl.getEmpleados(),SB_Asistencia.class,"ERROR");  
                      return "";
               }
       
    }

    
  public String generarTxtISSS(short Anio, short Mes ,short corelativo){
         
        DecimalFormat df = new DecimalFormat("###########");
         List<PlanillaIsss> lpisss = planillaIsssFacade.findByAnioMes(Anio, Mes,corelativo);
         String detalle = "";
         String ob="";
         String dias="";
         
         String mes= String.valueOf(Mes);
         if(mes.length()==1){
             mes= "0"+mes;
         }
         for( PlanillaIsss pisss : lpisss ){  
            String nombre = JsfUtil.truncate(pisss.getNombre(),40);
            nombre =  nombre+"                                        ".substring(nombre.length()); 
            String dev =  String.valueOf(df.format(pisss.getSalDebengado().floatValue()*100));

             dev =  "000000000".substring(dev.length())+dev;
            if(pisss.getCodObserva()==null){
                ob = "00";
            }else{
                ob = "0"+String.valueOf(pisss.getCodObserva());
            }
            dias = pisss.getDiasRemunerados();
            if( Integer.parseInt(pisss.getDiasRemunerados())<10){
              dias=   "0"+dias;
            }
            detalle+=pisss.getNoPatronal();
            detalle+=String.valueOf(Anio-2000);
            detalle+=mes;
            detalle+="01";
            detalle+=pisss.getNoAfilacion();
            detalle+="000000000000000";
            detalle+=nombre;         
            detalle+=dev;
            detalle+=dias;
            detalle+="08";
            detalle+=ob;
            detalle+="\r\n";

         }
        
                 
        return detalle;
    } 
  
  
    
  public String generarTxtAFP(short Anio, short Mes ){
         
         
         List<PlanillaAfp> lpisss = planillaAfpFacade.findByAnioMes(Anio, Mes);
         String detalle = "";
         
         String mes= String.valueOf(Mes);
         if(mes.length()==1){
             mes= "0"+mes;
         }
         detalle+=String.valueOf(Anio) +String.valueOf(mes);
         detalle+="\r\n";
         
         for( PlanillaAfp pafp : lpisss ){  
            String fechaingreso= new SimpleDateFormat("yyyyMMdd").format(pafp.getFechaIngreso());    
            String[] nombres = pafp.getNombres().split(" ");
            String[] apellidos = pafp.getApellidos().split(" ");
            detalle+=formatoAfp(pafp.getNumAfp());
            detalle+=formatoAfp(apellidos[0]);
            detalle+=formatoAfp(apellidos[1]);         
            detalle+=formatoAfp(pafp.getApCasada());
            detalle+=formatoAfp(nombres[0]);
            detalle+=formatoAfp(nombres[1]);         
            detalle+=formatoAfp(pafp.getDocumento());
            detalle+=formatoAfp(pafp.getNumDocumento());
            detalle+=formatoAfp(pafp.getEstadoCivil());
            detalle+="\""+"P"+"\",";
            detalle+=formatoAfp(pafp.getPensionado());
            detalle+="\"\",";
            detalle+=formatoAfp(pafp.getAfp());
            detalle+=formatoAfp(fechaingreso);
            detalle+="\"\",";
            detalle+="\""+"M"+"\",";         
            detalle+=formatoAfp(String.valueOf(pafp.getDevengado()));
            detalle+=formatoAfp(pafp.getHoras());
            detalle+=formatoAfp(pafp.getDias());
            detalle+=formatoAfp(pafp.getIngreso());
            detalle+=formatoAfp(pafp.getRetiro());
            detalle+=formatoAfp(pafp.getLicencia());
            detalle+=formatoAfp(pafp.getIncapacidad());
            detalle+=formatoAfp(pafp.getAprendiz());
            detalle+=formatoAfp(pafp.getPAnticipada());
            detalle+=formatoAfp(pafp.getPSinObligacion());
            detalle+=formatoAfp(pafp.getPRiesgo());
            detalle+=formatoAfp(pafp.getDocente());
            detalle+=formatoAfp(pafp.getCVoluntaria());
            detalle+=formatoAfp(pafp.getCtv());
            detalle+=formatoAfp(pafp.getCodCentro());
            detalle+="\r\n";
         
         }
        
                 
        return detalle;
    }   
  
  public String formatoAfp(String valor){
      
      if(valor==null){
        valor=   "\"\",";
      }else{
          valor=   "\""+valor+"\",";
      }
      return valor;
  }
  
}
