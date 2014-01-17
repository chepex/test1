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
public class ProgramacionPlaFacade extends AbstractFacade<ProgramacionPla> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public ProgramacionPlaFacade() {
	super(ProgramacionPla.class);
    }
    
@Override
    public List<ProgramacionPla> findAll(){
	 TypedQuery<ProgramacionPla> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("ProgramacionPla.findByCodCia", ProgramacionPla.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }    


    public List<ProgramacionPla> findByEstado(String status){
        try{
	 TypedQuery<ProgramacionPla> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("ProgramacionPla.findByStatus", ProgramacionPla.class )		    
		    .setParameter("codCia",  codCia )
		    .setParameter("status",  status );
         return q.getResultList();
         
        }catch(Exception ex){
            
            return null;
        }
    
    }   
    
    public  ProgramacionPla findByPk(ProgramacionPla programacionPla){
	 TypedQuery<ProgramacionPla> q;
	 try{
		 LoginBean lb= new LoginBean();	
		 short codCia = lb.sscia();	
		 	 
		 q = em.createNamedQuery("ProgramacionPla.findByPK", ProgramacionPla.class )		    
		    .setParameter("codCia",  codCia )
		    .setParameter("secuencia", programacionPla.getProgramacionPlaPK().getSecuencia() );
         return q.getSingleResult();
         }catch(Exception ex){
          
           ProgramacionPla programacionPlax = new   ProgramacionPla();
           
           return programacionPlax;
         }
         
    }      
}
