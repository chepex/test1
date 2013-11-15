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
public class OtrosMovDpFacade extends AbstractFacade<OtrosMovDp> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public OtrosMovDpFacade() {
	super(OtrosMovDp.class);
    }
    
  @Override
    public List<OtrosMovDp> findAll(){
	 TypedQuery<OtrosMovDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("OtrosMovDp.findByCodCia", OtrosMovDp.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }     
}
