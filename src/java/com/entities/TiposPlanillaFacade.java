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
public class TiposPlanillaFacade extends AbstractFacade<TiposPlanilla> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public TiposPlanillaFacade() {
	super(TiposPlanilla.class);
    }
    
@Override
    public List<TiposPlanilla> findAll(){
	 TypedQuery<TiposPlanilla> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("TiposPlanilla.findByCodCia", TiposPlanilla.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }      
    

    public TiposPlanilla findByMovDp(){
	 TypedQuery<TiposPlanilla> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("TiposPlanilla.findByCodCia", TiposPlanilla.class )		    
		    .setParameter("codCia",  codCia );
         return q.getSingleResult();
    
    }   

}
