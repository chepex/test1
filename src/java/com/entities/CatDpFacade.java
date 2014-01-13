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
public class CatDpFacade extends AbstractFacade<CatDp> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatDpFacade() {
        super(CatDp.class);
    }
    
    @Override
    public List<CatDp> findAll(){
	 TypedQuery<CatDp> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("CatDp.findByCodCia", CatDp.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }  
    
     
}
