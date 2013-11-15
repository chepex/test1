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
public class ParametrosFacade extends AbstractFacade<Parametros> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public ParametrosFacade() {
	super(Parametros.class);
    }
    
 @Override
    public List<Parametros> findAll(){
	 TypedQuery<Parametros> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("Parametros.findByCodCia", Parametros.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }    

    
    public Parametros findByNombre(String nombre){
	 TypedQuery<Parametros> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("Parametros.findByNombre", Parametros.class )		    
		 .setParameter("codCia",  codCia )
		 .setParameter("nombre",  nombre );
         return q.getSingleResult();
    
    }   
}
