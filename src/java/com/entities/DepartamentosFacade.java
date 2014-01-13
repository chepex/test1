/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mmixco
 */
@Stateless
public class DepartamentosFacade extends AbstractFacade<Departamentos> {
    @EJB
    private EmpleadosFacade empleadosFacade;
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public DepartamentosFacade() {
	super(Departamentos.class);
    }
    
    @Override
    public List<Departamentos> findAll(){
	 TypedQuery<Departamentos> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("Departamentos.findByCodCia", Departamentos.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }      
    public List<Departamentos> findByPuesto(){
	 TypedQuery<Departamentos> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	    String user = lb.ssuser();
	    Empleados emp = empleadosFacade.findbyUsuario(user);	 
		 q = em.createNamedQuery("Departamentos.findByPuesto", Departamentos.class )		    		    
		    .setParameter("puestosList", emp.getPuestos() );
         return q.getResultList();
	
    } 
    
    
     
}
