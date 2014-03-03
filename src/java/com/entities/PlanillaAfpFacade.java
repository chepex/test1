/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mmixco
 */
@Stateless
public class PlanillaAfpFacade extends AbstractFacade<PlanillaAfp> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanillaAfpFacade() {
        super(PlanillaAfp.class);
    }
    

    
    public  List<PlanillaAfp> findByEmp(ResumenAsistencia ra){
        TypedQuery<PlanillaAfp> q;	 
        try{
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("PlanillaAfp.findByCodEmp", PlanillaAfp.class )		    
                    .setParameter("codCia",  codCia )
                    .setParameter("anio",  ra.getProgramacionPla().getAnio() )                                             
                    .setParameter("mes",  ra.getProgramacionPla().getMes() )                                                                      
                    .setParameter("codEmp",  ra.getResumenAsistenciaPK().getCodEmp() );
                 
                   return  q.getResultList();
        }
        catch(Exception ex){
            // JsfUtil.logs(ex , "Surgio un error", "Proceso findByEmp Empleado "+ra.getEmpleados().getNombreIsss(),PlanillaIsssFacade.class,"ERROR");             
            return null;
        }
  
    
    }  
    
    public List <PlanillaAfp> findByAnioMes(short vanio, short vmes){
        TypedQuery<PlanillaAfp> q;	 
        try{
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("PlanillaAfp.findByAnioMes", PlanillaAfp.class )		    
                    .setParameter("codCia",  codCia )
                    .setParameter("anio", vanio )                                             
                    .setParameter("mes",  vmes ) ;                                                                                        
                 
                   return  q.getResultList();
        }
        catch(Exception ex){
            // JsfUtil.logs(ex , "Surgio un error", "Proceso findByEmp Empleado "+ra.getEmpleados().getNombreIsss(),PlanillaIsssFacade.class,"ERROR");             
            return null;
        }
  
    
    }      
        
    
}
