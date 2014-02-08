package com.ejb;
import com.entities.DeptosMov;
import com.entities.DeptosMovFacade;
import com.entities.Dmgdetalle;
import com.entities.DmgdetalleFacade;
import com.entities.DmgdetallePK;
import com.entities.Dmgpoliza;
import com.entities.DmgpolizaFacade;
import com.entities.DmgpolizaPK;
import com.entities.LoginBean;
import com.entities.Mensaje;
import com.entities.MovDp;
import com.entities.MovDpFacade;
import com.entities.Parametros;
import com.entities.ParametrosFacade;
import com.entities.Planilla;
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
import java.text.DecimalFormat;
import java.util.Date;
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

              for( ResumenAsistencia ra : iterator ){ 
                 
                  if(e.getTiposPlanilla().getPromedio().equals("M")){                      
                     calculos.promedioMensual(ra);    
                  } 
                  if(e.getTiposPlanilla().getPromedio().equals("Q")){
                     calculos.promedioQuincenal(ra);  
                  }                  
                  if(e.getTiposPlanilla().getPrestamos().equals("S")){
                     calculos.CalcularPrestamos(ra);  
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
             //     System.out.print(ra.getResumenAsistenciaPK().getCodEmp());
                  mensaje ="ok+";
              }
                    mensaje ="ok";
                    
                    totales += e.getTiposPlanilla().getNomTipopla();
               
              }
            
         return mensaje;
     }	
    
    public String Cerrar()  {   
        
        List<ProgramacionPla> iterador=  programacionPlaFacade.findByEstado("P");        
        if(iterador.isEmpty()){
            
           return  "error";
        }
          for( ProgramacionPla e : iterador ){
              actualizarPrestamos(e);
              crearPartida(e);
              GenerarIsss(e);
              //PlanillaHistorial(e);              
              actualizarStatus(e);
               
          }            
         return "ok";
     }	    
    
    public String GenerarIsss(ProgramacionPla programacionPla){
            
            List<ResumenAsistencia> iterator =  resumenAsistenciaFacade.findBysecuencia(programacionPla );
            for( ResumenAsistencia ra : iterator ){          
                  CalculoIsss(ra);
              }
        return "ok";
    }      
    
    public String CalculoIsss(ResumenAsistencia ra){
        
        try{
        Parametros p= parametrosFacade.findByNombre("HORAS_LABORALES");
        Parametros nPATRONAL= parametrosFacade.findByNombre("NPATRONAL_ISSS");
        LoginBean lb= new LoginBean();	
        List<Planilla> planillas = planillaFacade.findByMes(ra ) ;
        BigDecimal devengado = BigDecimal.valueOf( devengoIsss(planillas));
        String dias =  devengoDias(planillas) ;
        PlanillaIsss plaIsss = planillaIsssFacade.findByEmp(ra);
        System.out.print("ISSS EMP=>"+ra.getResumenAsistenciaPK().getCodEmp());
        if(plaIsss==null){
            
            PlanillaIsss planillaIsss =new PlanillaIsss();
            PlanillaIsssPK planillaIsssPK =new PlanillaIsssPK();
            planillaIsssPK.setCodCia(ra.getResumenAsistenciaPK().getCodCia());
            planillaIsssPK.setAnio(ra.getProgramacionPla().getAnio());
            planillaIsssPK.setMes(ra.getProgramacionPla().getMes());
            planillaIsssPK.setCodEmp(ra.getResumenAsistenciaPK().getCodEmp());
            planillaIsss.setPlanillaIsssPK(planillaIsssPK);
            planillaIsss.setNombre(ra.getEmpleados().getNombreIsss());
            planillaIsss.setNoAfilacion(ra.getEmpleados().getNumIgss());
            planillaIsss.setCorrelativo(ra.getProgramacionPla().getTiposPlanilla().getTiposPlanillaPK().getCodTipopla());
            planillaIsss.setDiasRemunerados(dias);
            planillaIsss.setHorasJornada(p.getValorTxt());
            planillaIsss.setSalDebengado( devengado);            
            planillaIsss.setUsuario(lb.ssuser());
            planillaIsss.setFechaReg(lb.sdate());        
            planillaIsss.setNoPatronal(nPATRONAL.getValorTxt());
            if(ra.getObservaciones()!= null){
                planillaIsss.setCodObserva(ra.getObservaciones().getIsss());
            }
            if(ra.getResumenAsistenciaPK().getCodEmp()==210){
                System.out.print("aa");
            }
            
            planillaIsssFacade.create(planillaIsss);
            planillaIsssFacade.flush();
            planillaIsssFacade.refresh(planillaIsss);
        }else{
            Parametros TOPE_ISSS= parametrosFacade.findByNombre("TOPE_ISSS");            
            
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
        } 

        
        
        
        
        }catch(Exception ex){
             JsfUtil.logs(ex , "Surgio un error", "Proceso validaCuenta",SB_Planilla.class,"ERROR"); 
        }
        return "ok";
    }
    
    public Float devengoIsss(List<Planilla> lplanilla){
        float total =0;  
            for(Planilla pla : lplanilla){
              total = total + pla.getNeto().floatValue();
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
         
        JsfUtil.logs(ex , "Surgio un error", "Proceso savePoliza",SB_Asistencia.class,"ERROR"); 
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
           
        JsfUtil.logs(ex , "Surgio un error", "Proceso PART_ liquido",SB_Asistencia.class,"ERROR"); 
        }
        
    } 
   
    public void mov(ResumenAsistencia ra){
        try{
        List<MovDp> deduc =  movDpFacade.findByCodEmp(ra);
         for( MovDp movDp : deduc ){  
            String concepto="";
               BigDecimal cargo = BigDecimal.ZERO;
               BigDecimal abono = BigDecimal.ZERO;
               BigDecimal valor = movDp.getValor();               
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
                        cargo = valor;
                        abono = BigDecimal.ZERO;         
                        concepto="PRESTACION ";
                   }

                String py = ra.getEmpleados().getDepartamentos().getProyecto();
                DmgdetallePK pk = new DmgdetallePK(pl.getDmgpolizaPK().getCodCia(),pl.getDmgpolizaPK().getTipoDocto(), pl.getDmgpolizaPK().getNumPoliza(),pl.getDmgpolizaPK().getFecha(), 0);                
                dt = new Dmgdetalle(pk);
                dt.setCta1(cta1);
                dt.setCta2(cta2);
                dt.setCta3(cta3);
                dt.setCta4(cta4);
                dt.setCta5(cta5);
                dt.setProyecto( String.valueOf(cta1)); 
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
                    Prestamos p = prestamosFacade.findByPk(m);
                    short cuotas = (short) (p.getCuotasP()+1) ;
                    float saldo = p.getSaldo().floatValue() - m.getValor().floatValue();
                    p.setCuotasP(cuotas);
                    p.setSaldo( BigDecimal.valueOf(saldo));
                    prestamosFacade.edit(p);
                }
                
                
                
            }                                                        
    }  
    
    public String actualizarStatus(ProgramacionPla prog){
        prog.setStatus("C");
        programacionPlaFacade.edit(prog);
        programacionPlaFacade.flush();
        programacionPlaFacade.refresh(prog);
        return "ok";
    }    
    
    
    public String generarTxt(){
        
          String encabezado="";
          String Campo="B";
          String vPlan="4177";
          String Plan =   vPlan +"0000".substring(vPlan.length());
          String vCorrelativo= "4";
          String Correlativo =   "00000".substring(vCorrelativo.length())+vCorrelativo;
          String vNit= "";
          String Nit=  "                    ".substring(vNit.length())+vNit;
          
          String anio= "2013";
          String vmes= "4";
          String mes = "00".substring(vmes.length())+vmes;          
          String vdias= "2";
          String dias = "00".substring(vdias.length())+vdias;                              
          
                            
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
        
          
          List<Planilla> lpla= planillaFacade.findByStatus();
          int cort= 1;
          String detalle = "";
           for( Planilla p : lpla ){               
               try{
                   
               if (!p.getEmpleados().getCodBanco().equals("0")){
                   if (p.getEmpleados().getCodBanco().equals("14")){
                       if(p.getLiquido().floatValue()>0){
                           detalle += txtDetalle(p,cort++);
                           total = total.add(p.getLiquido().multiply(bd2)) ;
                           id++;
                       }
                       
                       
                    }
               }
               
               
               
               }catch(Exception ex){
                   return "error empleado  ="+p.getEmpleados().getEmpleadosPK().getCodEmp();
               }
                   
                   
                   
               
             
           }
           
           
           String vmonto = "";  
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
          encabezado+=descripcion;
          encabezado+=Esp;
          encabezado+=nombre;
          encabezado+=Cuenta;
          encabezado+="\n";           
          
        encabezado+=detalle;
        return encabezado;
    }
    
    public String txtDetalle(Planilla pl,int ct)
    {
        
          float valor= pl.getLiquido().floatValue()*100;
          DecimalFormat df = new DecimalFormat("###########");
          
          

          String detalle="";
          String Campo="T";
          String vPlan="4177";
          String Plan = vPlan +"0000".substring(vPlan.length());
          String vCorrelativo=  "1" ;
          String Correlativo =   "00000".substring(vCorrelativo.length())+vCorrelativo;
          String vNit= pl.getEmpleados().getNumNit();
          String Nit=  vNit+"                    ".substring(vNit.length());
          String vtrans = String.valueOf(ct);
          String trans  = "     ".substring(vtrans.length())+vtrans; 
          String Id=  "     ";
          String anio= "2013";
          String vmes= "4";
          String mes = "00".substring(vmes.length())+vmes;          
          String vdias= "2";
          String dias = "00".substring(vdias.length())+vdias;                              
          String vmonto= String.valueOf(df.format(valor)) ;
          String monto = "             ".substring(vmonto.length())+vmonto;                    

          String vdescripcion ="1 quincena junio 2013";
          vdescripcion = JsfUtil.truncate(vdescripcion,30);
         
          String descripcion =  vdescripcion+"                              ".substring(vdescripcion.length()); 
          String Esp = " ";
          String vnombre = pl.getEmpleados().getNombres()+pl.getEmpleados().getApellidos();
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
          detalle+="\n";
          
        return detalle;
    }

}
