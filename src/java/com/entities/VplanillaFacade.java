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
public class VplanillaFacade extends AbstractFacade<Vplanilla> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VplanillaFacade() {
        super(Vplanilla.class);
    }
    
    public  List<Vplanilla>  findByPk(ProgramacionPla programacionPla){
	 TypedQuery<Vplanilla> q;	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("Vplanilla.findByPk", Vplanilla.class )		    
                    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() )
		    .setParameter("codCia",  codCia );
    return q.getResultList();
    
    } 
    
    @Override
    public List<Vplanilla> findAll(){
	 TypedQuery<Vplanilla> q;
	 
	    //LoginBean lb= new LoginBean();	
	    short codCia =0;
		 q = em.createNamedQuery("Vplanilla.findAll", Vplanilla.class )		                        
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }       
 
    
    
}
