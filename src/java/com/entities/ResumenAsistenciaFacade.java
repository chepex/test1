/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
            try{
                
	 
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("ResumenAsistencia.findByEmp", ResumenAsistencia.class )		    
		    .setParameter("codCia",  codCia )
                     .setParameter("codEmp",   movdp.getMovDpPK().getCodEmp() )
		    .setParameter("secuencia",  movdp.getMovDpPK().getSecuencia() );
             q.getSingleResult();/*para q de error si es nulo*/
            }
            catch(Exception ex){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Surgio un error", "El empleado "+movdp.getMovDpPK().getCodEmp()+" No pertenece a esta planilla"));  
                return null;
            }
        return q.getSingleResult();
    
    }   
        
        
        public ResumenAsistencia ByProgramacionEmp(ProgramacionPla programacionpla, Empleados emp){
            TypedQuery<ResumenAsistencia> q;
            try{
                
	 
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("ResumenAsistencia.findByEmp", ResumenAsistencia.class )		    
		    .setParameter("codCia",  codCia )
                     .setParameter("codEmp",   emp.getEmpleadosPK().getCodEmp() )
		    .setParameter("secuencia",  programacionpla.programacionPlaPK.getSecuencia() );
             q.getSingleResult();/*para q de error si es nulo*/
            }
            catch(Exception ex){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Surgio un error", "El empleado "+emp.getEmpleadosPK().getCodEmp()+" No pertenece a esta planilla"));  
                return null;
            }
        return q.getSingleResult();
    
    }
        
   
        public ResumenAsistencia ByEmp(PlanillaHoras ph){
            TypedQuery<ResumenAsistencia> q;
            try{
                
	 
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("ResumenAsistencia.findByEmp", ResumenAsistencia.class )		    
		    .setParameter("codCia",  codCia )
                     .setParameter("codEmp",   ph.getPlanillaHorasPK().getCodEmp() )
		    .setParameter("secuencia",  ph.getPlanillaHorasPK().getSecuencia() );
             q.getSingleResult();/*para q de error si es nulo*/
            }
            catch(Exception ex){
                return null;
            }
        return q.getSingleResult();
    
    }           
        

}
