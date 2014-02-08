/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author mmixco
 */
@Stateless
public class MovDpFacade extends AbstractFacade<MovDp> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public MovDpFacade() {
	super(MovDp.class);
    }
    
  @Override
    public List<MovDp> findAll(){
	 TypedQuery<MovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("MovDp.findByCodCia", MovDp.class )		    
		    .setParameter("codCia",  0 );
         return q.getResultList();
    
    }      
  
    
    public BigDecimal findPk(MovDp movDp){
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		Query q =  em.createNativeQuery("Select nvl( Count(*),0) from Mov_Dp where cod_Cia = ? "
                        + "and cod_dp = ? and cod_emp = ? and secuencia =?" );		                        
                q.setParameter(1, codCia);  
                q.setParameter(2, movDp.getMovDpPK().getCodDp());  
                q.setParameter(3, movDp.getMovDpPK().getCodEmp());  
                q.setParameter(4, movDp.getMovDpPK().getSecuencia());  
                BigDecimal val = (BigDecimal)q.getSingleResult();
        return val;
       
    }      
  
    public MovDp  findPk2(MovDp movDp){
	try{ 
 
	 TypedQuery<MovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("MovDp.findByPK2", MovDp.class )		    
		    .setParameter("codCia",  codCia)
                    .setParameter("codEmp",  movDp.getMovDpPK().getCodEmp())                    
                    .setParameter("secuencia",  movDp.getMovDpPK().getSecuencia() );
                 
         return q.getSingleResult();
        }catch(Exception ex){
            return null;
        }
    }      
  
    
    public  List<MovDp>  findSecuencia(ProgramacionPla programacionPla){
	 
	 TypedQuery<MovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("MovDp.findBySecuencia", MovDp.class )		    
		    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() )
                    .setParameter("generado",  "S" )
                    .setParameter("codCia",  codCia );
                 
                 
         return q.getResultList();
       
    }  
    
    
    
    public  List<MovDp>  findSecuenciaGenerado(ProgramacionPla programacionPla, String generado){
	 
	 TypedQuery<MovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("MovDp.findBySecuencia", MovDp.class )		    
		    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() )
                    .setParameter("generado",  generado )
                    .setParameter("codCia",  codCia );
                 
                 
         return q.getResultList();
       
    }     
      public List<MovDp> findByCodEmp(ResumenAsistencia resumenAsistencia){
	 	
              TypedQuery<MovDp> q;
	
           
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
                
		 q = em.createNamedQuery("MovDp.findByCodEmp2", MovDp.class )		    
		    .setParameter("secuencia",  resumenAsistencia.getResumenAsistenciaPK().getSecuencia() )
                    .setParameter("codEmp",  resumenAsistencia.getResumenAsistenciaPK().getCodEmp() )                    
                    .setParameter("codCia",  codCia );
                 return q.getResultList();
                
                
   }  
      
      public List<MovDp> findByRentaT(ResumenAsistencia resumenAsistencia){
	 	
              TypedQuery<MovDp> q;
	
           
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
                
		 q = em.createNamedQuery("MovDp.findByRentaT", MovDp.class )		    		    
                    .setParameter("anio",  resumenAsistencia.getProgramacionPla().getAnio() )                    
                    .setParameter("codEmp",  resumenAsistencia.getResumenAsistenciaPK().getCodEmp() )                    
                    .setParameter("codCia",  codCia );
                 return q.getResultList();
                
                
   }  
      
      public List<MovDp> findByAfpT(ResumenAsistencia resumenAsistencia){	 	
            TypedQuery<MovDp> q;
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
            q = em.createNamedQuery("MovDp.findByAfpT", MovDp.class )		    		    
               .setParameter("anio",  resumenAsistencia.getProgramacionPla().getAnio() )                    
               .setParameter("codEmp",  resumenAsistencia.getResumenAsistenciaPK().getCodEmp() )                    
               .setParameter("codCia",  codCia );
            return q.getResultList();
   }  
      
    public List<MovDp> findByTotal(Empleados empleado, String sumaResta,ProgramacionPla programacionPla){
	 	
              TypedQuery<MovDp> q;
	
           
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
                if(empleado!=null ){
		 q = em.createNamedQuery("MovDp.findBySR", MovDp.class )		    
		    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() )
                    .setParameter("codEmp",  empleado.getEmpleadosPK().getCodEmp() )
                    .setParameter("sumaResta",  sumaResta )
                    .setParameter("codCia",  codCia );
                 return q.getResultList();
                }
                return null;
   }   
    
    public List<MovDp> findByTNogravado(Empleados empleado, String sumaResta,ProgramacionPla programacionPla){
	 	
              TypedQuery<MovDp> q;
	
           
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
                if(empleado!=null ){
		 q = em.createNamedQuery("MovDp.findBySRNogravado", MovDp.class )		    
		    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() )
                    .setParameter("codEmp",  empleado.getEmpleadosPK().getCodEmp() )
                    .setParameter("sumaResta",  sumaResta )
                    .setParameter("codCia",  codCia );
                 return q.getResultList();
                }
                return null;
   }    
    public List<MovDp> findByPkPrioridad(Empleados empleado, String sumaResta,ResumenAsistencia resumenAsistencia){
	 	
              TypedQuery<MovDp> q;
	
           
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
                
		 q = em.createNamedQuery("MovDp.findBySRPrioridad", MovDp.class )		    
		    .setParameter("secuencia",  resumenAsistencia.getResumenAsistenciaPK().getSecuencia() )
                    .setParameter("codEmp",  empleado.getEmpleadosPK().getCodEmp() )
                    .setParameter("sumaResta",  sumaResta )
                    .setParameter("codCia",  codCia );

                 return q.getResultList();
   }       
  
    
    
    public List<MovDp> findByCat(String categoria,ResumenAsistencia resumenAsistencia){
	 	
              TypedQuery<MovDp> q;
	
           
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
                
		 q = em.createNamedQuery("MovDp.findByCat", MovDp.class )		    
		    .setParameter("secuencia",  resumenAsistencia.getResumenAsistenciaPK().getSecuencia() )
                    .setParameter("codEmp",  resumenAsistencia.getEmpleados().getEmpleadosPK().getCodEmp() )
                    .setParameter("categoria", categoria  )
                    .setParameter("codCia",  codCia );

                 return q.getResultList();
   } 
    
    public MovDp findByPresta(Prestamos presta,ResumenAsistencia resumenAsistencia){
	 try{	
              TypedQuery<MovDp> q;
	
           
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
                
		 q = em.createNamedQuery("MovDp.Presta", MovDp.class )		    
		    .setParameter("secuencia",  resumenAsistencia.getResumenAsistenciaPK().getSecuencia() )
                    .setParameter("codEmp",  resumenAsistencia.getEmpleados().getEmpleadosPK().getCodEmp() )
                    .setParameter("codPresta",  presta.getPrestamosPK().getCodPresta() )                         
                    .setParameter("codCia",  codCia );
                 return q.getSingleResult();
         }catch(Exception ex){
             return null;
         }
                 
   }     
    
    public BigDecimal PromComision(Empleados empleado, ResumenAsistencia resumenAsistencia){
	 BigDecimal val;
	 try{
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	 /*TENEMOS Q MANDARLE LA FECHA DE PAGO DE LA PLANILLA PARAQ NOS CUADRE*/
            Query q =  em.createNativeQuery("select nvl(sum(valor),0) from mov_dp m, deduc_presta d, cat_dp c, programacion_pla p " +
                                                " where M.COD_CIA = d.cod_cia" +
                                                " and m.cod_dp = d.cod_dp" +
                                                " and d.cod_cia= c.cod_cia" +
                                                " and D.COD_CAT = c.cod_cat" +
                                                " and C.DESCRIPCION = 'Comision'" +                                                    
                                                " and P.COD_CIA = m.cod_cia" +
                                                " and P.SECUENCIA = M.SECUENCIA" +                                                
                                                " and m.GENERADO <> 'S'" +    
                                                " and P.STATUS = 'C'" +
                                                " and m.cod_cia = ?" +
                                                " and m.cod_emp = ?" +
                                                " and P.FECHA_PAGO between  "+
                                                "TO_DATE('01/'||to_char(add_months(?,-6),'mm')||'/'||to_char(add_months(?,-6),'yyyy'),'DD/MM/YYYY') "+        
                                                " and last_day(add_months (?,-1))");		                        
            q.setParameter(1, codCia);  
            q.setParameter(2, empleado.getEmpleadosPK().getCodEmp());                  
            q.setParameter(3, resumenAsistencia.getProgramacionPla().getFechaPago());                  
            q.setParameter(4, resumenAsistencia.getProgramacionPla().getFechaPago());       
            q.setParameter(5, resumenAsistencia.getProgramacionPla().getFechaPago());  
            
            val= (BigDecimal)q.getSingleResult();
        
         }
         catch(Exception ex){
              val =  BigDecimal.ZERO ;
            
         }
       return val;
    
    }     

    public BigDecimal PromComision(Empleados empleado, ProgramacionPla ppla){
	 BigDecimal val;
	 try{
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	 /*TENEMOS Q MANDARLE LA FECHA DE PAGO DE LA PLANILLA PARAQ NOS CUADRE*/
            Query q =  em.createNativeQuery("select nvl(sum(valor),0) from mov_dp m, deduc_presta d, cat_dp c, programacion_pla p " +
                                                " where M.COD_CIA = d.cod_cia" +
                                                " and m.cod_dp = d.cod_dp" +
                                                " and d.cod_cia= c.cod_cia" +
                                                " and D.COD_CAT = c.cod_cat" +
                                                " and C.DESCRIPCION = 'Comision'" +                                                    
                                                " and P.COD_CIA = m.cod_cia" +
                                                " and P.SECUENCIA = M.SECUENCIA" +                                                
                                                " and m.GENERADO <> 'S'" +    
                                                " and P.STATUS = 'C'" +
                                                " and m.cod_cia = ?" +
                                                " and m.cod_emp = ?" +
                                                " and P.FECHA_PAGO between  "+
                                                "TO_DATE('01/'||to_char(add_months(?,-6),'mm')||'/'||to_char(add_months(?,-6),'yyyy'),'DD/MM/YYYY') "+        
                                                " and last_day(add_months (?,-1))");		                        
            q.setParameter(1, codCia);  
            q.setParameter(2, empleado.getEmpleadosPK().getCodEmp());                  
            q.setParameter(3, ppla.getFechaPago());                  
            q.setParameter(4, ppla.getFechaPago());       
            q.setParameter(5, ppla.getFechaPago());  
            
            val= (BigDecimal)q.getSingleResult();
        
         }
         catch(Exception ex){
              val =  BigDecimal.ZERO ;
            
         }
       return val;
    
    } 
    
    public List<MovDp> findByFiltro(ProgramacionPla programacionPla){
	 TypedQuery<MovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
	    String user = lb.ssuser();
		 q = em.createNamedQuery("MovDp.findByFiltro", MovDp.class )		    
		    .setParameter("codCia",  codCia )		    
		    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() );
         return q.getResultList();
    
    } 


    public List<MovDp> findByStatus(String status){
	 TypedQuery<MovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
	    String user = lb.ssuser();
		 q = em.createNamedQuery("MovDp.findByStatus", MovDp.class )		    
		    .setParameter("codCia",  codCia )		    
		    .setParameter("status", status );
         return q.getResultList();
    
    }       
  
    public MovDp findByPKDp(PlanillaHoras PlanillaHoras){
	 TypedQuery<MovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
	    String user = lb.ssuser();
		 q = em.createNamedQuery("MovDp.findByPkDp", MovDp.class )		    
		    .setParameter("codCia",  codCia )		    
		    .setParameter("secuencia",  PlanillaHoras.getPlanillaHorasPK().getSecuencia())
                    .setParameter("codDp",  PlanillaHoras.getPlanillaHorasPK().getCodDp()  )
                 .setParameter("codEmp",  PlanillaHoras.getPlanillaHorasPK().getCodEmp() );
         return q.getSingleResult();
    
    }     
    
    public List<MovDp> findBymovdp(Empleados empleado, ResumenAsistencia resumenAsistencia, int codDp ){
	 TypedQuery<MovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();		    
		 q = em.createNamedQuery("MovDp.findByPkDpMes", MovDp.class )		    
		    .setParameter("codCia",  codCia )		    
		    .setParameter("anio",  resumenAsistencia.getProgramacionPla().getAnio())
                    .setParameter("mes",  resumenAsistencia.getProgramacionPla().getMes())
                    .setParameter("codDp",   codDp )
                 .setParameter("codEmp",  empleado.getEmpleadosPK().getCodEmp() );
         return q.getResultList();
    
    }      
}

