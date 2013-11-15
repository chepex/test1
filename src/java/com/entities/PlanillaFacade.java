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
public class PlanillaFacade extends AbstractFacade<Planilla> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public PlanillaFacade() {
	super(Planilla.class);
    }
    
 @Override
    public List<Planilla> findAll(){
	 TypedQuery<Planilla> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("Planilla.findByCodCia", Planilla.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }       
 
 
    public  List<Planilla>  findByPk(ProgramacionPla programacionPla){
	 TypedQuery<Planilla> q;	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("Planilla.findByPk", Planilla.class )		    
                    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() )
		    .setParameter("codCia",  codCia );
    return q.getResultList();
    
    } 
    
    public  List<Planilla>  findByStatus(){
	 TypedQuery<Planilla> q;	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("Planilla.findByStatus", Planilla.class )		    
                    .setParameter("status",  "P" )
		    .setParameter("codCia",  codCia );
    return q.getResultList();
    
    } 
    
    public  List<Planilla>  findByAnioMes(short anio, short mes){
	 TypedQuery<Planilla> q;	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("Planilla.findByAnioMes", Planilla.class )		    
                    .setParameter("anio", anio )
                    .setParameter("mes",  mes )                    
		    .setParameter("codCia",  codCia );
    return q.getResultList();
    
    }     
 
}