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
		    .setParameter("codCia",  codCia );
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
  
        
    public  List<MovDp>  findSecuencia(ProgramacionPla programacionPla){
	 
	 TypedQuery<MovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("MovDp.findBySecuencia", MovDp.class )		    
		    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() )
                    .setParameter("generado",  "G" )
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
  
    public BigDecimal PromComision(Empleados empleado, ResumenAsistencia resumenAsistencia){
	 BigDecimal val;
	 try{
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	 
		Query q =  em.createNativeQuery("select nvl(sum(valor),0) from mov_dp m, deduc_presta d, cat_dp c, programacion_pla p " +
                                                    " where M.COD_CIA = d.cod_cia" +
                                                    " and m.cod_dp = d.cod_dp" +
                                                    " and d.cod_cia= c.cod_cia" +
                                                    " and D.COD_CAT = c.cod_cat" +
                                                    " and C.DESCRIPCION = 'comision'" +                                                    
                                                    " and P.COD_CIA = m.cod_cia" +
                                                    " and P.SECUENCIA = M.SECUENCIA" +
                                                    " and P.FECHA_PAGO between  add_months( P.FECHA_PAGO,-5) and sysdate" +
                                                    " and m.GENERADO <> 'G'" +    
                                                    " and P.STATUS = 'C'" +
                                                    " and m.cod_cia = ?" +
                                                    " and m.cod_emp = ?" +
                                                    " and m.secuencia= ? " 
                                                      );		                        
                q.setParameter(1, codCia);  
                q.setParameter(2, empleado.getEmpleadosPK().getCodEmp());                  
                q.setParameter(3, resumenAsistencia.getResumenAsistenciaPK().getSecuencia());  
                val= (BigDecimal)q.getSingleResult();
        
         }
         catch(Exception ex){
              val =  BigDecimal.ZERO ;
            
         }
       return val;
    
    }     
  
}

