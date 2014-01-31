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
public class RentaFacade extends AbstractFacade<Renta> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RentaFacade() {
        super(Renta.class);
    }
    
@Override
    public List<Renta> findAll(){
	 TypedQuery<Renta> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("Renta.findByCodCia", Renta.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    } 

    

    public Renta findByValor(double devengado,short id ){
        try{
	 TypedQuery<Renta> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("Renta.findByDevengado", Renta.class )		    
                    .setParameter("devengado",  devengado )
                    .setParameter("id",  id )
		    .setParameter("codCia",  codCia );
                    
         return q.getSingleResult();
         }
        catch(Exception ex){
            return null;
        }
    
    } 
    
}
