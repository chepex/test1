/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import com.entities.util.JsfUtil;
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
public class PlanillaIsssFacade extends AbstractFacade<PlanillaIsss> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanillaIsssFacade() {
        super(PlanillaIsss.class);
    }
    
  
    
    public  PlanillaIsss findByEmp(ResumenAsistencia ra){
        TypedQuery<PlanillaIsss> q;	 
        try{
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("PlanillaIsss.findByCodEmp", PlanillaIsss.class )		    
                    .setParameter("codCia",  codCia )
                    .setParameter("anio",  ra.getProgramacionPla().getAnio() )                                             
                    .setParameter("mes",  ra.getProgramacionPla().getMes() )                                                                      
                    .setParameter("codEmp",  ra.getResumenAsistenciaPK().getCodEmp() );
                 
                   return  q.getSingleResult();
        }
        catch(Exception ex){
            // JsfUtil.logs(ex , "Surgio un error", "Proceso findByEmp Empleado "+ra.getEmpleados().getNombreIsss(),PlanillaIsssFacade.class,"ERROR");             
            return null;
        }
  
    
    }  
    
    public List <PlanillaIsss> findByAnioMes(short vanio, short vmes,short vcorelativo){
        TypedQuery<PlanillaIsss> q;	 
        try{
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("PlanillaIsss.findByAnioMes", PlanillaIsss.class )		    
                    .setParameter("codCia",  codCia )
                    .setParameter("anio", vanio )                                             
                    .setParameter("mes",  vmes ) 
                    .setParameter("correlativo", vcorelativo ) ;     
                 
                   return  q.getResultList();
        }
        catch(Exception ex){
            // JsfUtil.logs(ex , "Surgio un error", "Proceso findByEmp Empleado "+ra.getEmpleados().getNombreIsss(),PlanillaIsssFacade.class,"ERROR");             
            return null;
        }
  
    
    }      
    
}
