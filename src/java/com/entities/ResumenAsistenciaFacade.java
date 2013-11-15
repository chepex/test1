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
public class ResumenAsistenciaFacade extends AbstractFacade<ResumenAsistencia> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public ResumenAsistenciaFacade() {
	super(ResumenAsistencia.class);
    }
    
@Override
    public List<ResumenAsistencia> findAll(){
	 TypedQuery<ResumenAsistencia> q;
	 
	   
	
		 q = em.createNamedQuery("ResumenAsistencia.findByCodCia", ResumenAsistencia.class )		    
		    .setParameter("codCia",  0 );		    
         return q.getResultList();
    
    }          

    public List<ResumenAsistencia> findByFiltro(ProgramacionPla programacionPla){
	 TypedQuery<ResumenAsistencia> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("ResumenAsistencia.findByFiltro", ResumenAsistencia.class )		    
		    .setParameter("codCia",  codCia )
		    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() );
         return q.getResultList();
    
    } 
    
    
        public ResumenAsistencia delByFiltro(ProgramacionPla programacionPla){
	 TypedQuery<ResumenAsistencia> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("ResumenAsistencia.delByFiltro", ResumenAsistencia.class )		    
		    .setParameter("codCia",  codCia )
		    .setParameter("secuencia",  programacionPla.getProgramacionPlaPK().getSecuencia() );
         return q.getSingleResult();
    
    } 
        
        public List<ResumenAsistencia> findBysecuencia(ProgramacionPla programacionPla){
	 TypedQuery<ResumenAsistencia> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("ResumenAsistencia.findBySecuencia", ResumenAsistencia.class )		    
		    .setParameter("codCia",  codCia )
		    .setParameter("secuencia",   programacionPla.getProgramacionPlaPK().getSecuencia() );
         return q.getResultList();
    
    }         
 
      
        public ResumenAsistencia ByEmp(MovDp movdp){
	 TypedQuery<ResumenAsistencia> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("ResumenAsistencia.findByEmp", ResumenAsistencia.class )		    
		    .setParameter("codCia",  codCia )
                     .setParameter("codEmp",   movdp.getMovDpPK().getCodEmp() )
		    .setParameter("secuencia",  movdp.getMovDpPK().getSecuencia() );
         return q.getSingleResult();
    
    }   
    
   
}
