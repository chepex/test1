/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;


import com.entities.AbstractFacade;
import com.entities.Observaciones;
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
public class ObservacionesFacade extends AbstractFacade<Observaciones> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObservacionesFacade() {
        super(Observaciones.class);
    }
    
    @Override
    public List<Observaciones> findAll(){
	 TypedQuery<Observaciones> q;
	 
	   
            LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
		 q = em.createNamedQuery("Observaciones.findByCodCia", Observaciones.class )		    
		    .setParameter("codCia",  codCia );		    
         return q.getResultList();
    
    }      
    
    
}
