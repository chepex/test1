/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mmixco
 */
@Stateless
public class DetEmpleadoFacade extends AbstractFacade<DetEmpleado> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetEmpleadoFacade() {
        super(DetEmpleado.class);
    }
    
    @Override
    public List<DetEmpleado> findAll(){
	 TypedQuery<DetEmpleado> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("DetEmpleado.findByCodCia", DetEmpleado.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }    
    
    
    public List<DetEmpleado> findByEmp(Empleados empleado){
	 TypedQuery<DetEmpleado> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("DetEmpleado.findByCodEmp", DetEmpleado.class )		    
                    .setParameter("codEmp",  empleado.getEmpleadosPK().getCodEmp() )
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }        

    public DetEmpleado findByAfp(Empleados empleado){
	 TypedQuery<DetEmpleado> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	 try{
         q = em.createNamedQuery("DetEmpleado.findByAfp", DetEmpleado.class )		    
                    .setParameter("codEmp",  empleado.getEmpleadosPK().getCodEmp() )
                    .setParameter("afp",  "S" )
		    .setParameter("codCia",  codCia );
          
               
                     return q.getSingleResult();
    } catch(NoResultException e) {
        return null;
    }
         
         
		
               
         
                 
    
    }
    
}
