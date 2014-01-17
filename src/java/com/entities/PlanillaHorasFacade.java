/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author mmixco
 */
@Stateless
public class PlanillaHorasFacade extends AbstractFacade<PlanillaHoras> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public PlanillaHorasFacade() {
	super(PlanillaHoras.class);
    }
    
@Override
    public List<PlanillaHoras> findAll(){
	 TypedQuery<PlanillaHoras> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("PlanillaHoras.findByCodCia", PlanillaHoras.class )		    
		    .setParameter("codCia",  0 );
         return q.getResultList();
    
    }    

    public List<PlanillaHoras> findByFiltro(ProgramacionPla programacionPla){
	 TypedQuery<PlanillaHoras> q;
	 try{
            LoginBean lb= new LoginBean();         	
	    short codCia = lb.sscia();	
	    String user = lb.ssuser();
		 q = em.createNamedQuery("PlanillaHoras.findByFiltro", PlanillaHoras.class )		    
		    .setParameter("codCia",  codCia )
		    .setParameter("usuario", "%"+ user +"%" )
		    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() );
         return q.getResultList();
         }catch(Exception ex){
             return null;
         } 
    
    } 

    public PlanillaHoras findByPK(PlanillaHoras planillaHoras){
	 TypedQuery<PlanillaHoras> q;
	 try{
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
	    
		 q = em.createNamedQuery("PlanillaHoras.findByPK", PlanillaHoras.class )		    
		    .setParameter("codCia",  codCia )		    
		    .setParameter("secuencia",  planillaHoras.getPlanillaHorasPK().getSecuencia() )
                    .setParameter("codDp",  planillaHoras.getPlanillaHorasPK().getCodDp() )
                    .setParameter("codEmp",  planillaHoras.getPlanillaHorasPK().getCodEmp() );
         return q.getSingleResult();
         }catch(Exception ex){
             return null;
         }
    }     
    
}
