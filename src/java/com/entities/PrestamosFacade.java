/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.math.BigDecimal;
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
public class PrestamosFacade extends AbstractFacade<Prestamos> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public PrestamosFacade() {
	super(Prestamos.class);
    }
    
@Override
    public List<Prestamos> findAll(){
	 TypedQuery<Prestamos> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("Prestamos.findByCodCia", Prestamos.class )		    
		    .setParameter("codCia", 0 );
         return q.getResultList();
    
    }  

 public Integer SeqNext() {
    BigDecimal seq = (BigDecimal)((List)em.createNativeQuery("select PRESTAMOS_SEQ.nextval from dual").getResultList()).get(0);
    return seq.intValue();
 }

    public List<Prestamos> findByFiltro(String estado){
	 TypedQuery<Prestamos> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
	    String user = lb.ssuser();
	    if(estado.equals("P")){
		q = em.createNamedQuery("Prestamos.findByPagado", Prestamos.class )		    
		    .setParameter("codCia",  codCia );
	    }else{
		q = em.createNamedQuery("Prestamos.findByPendiente", Prestamos.class )		    
		    .setParameter("codCia",  codCia );
	    } 
		 
         return q.getResultList();
    
    }  

    public List<Prestamos> findByEmpleado(Empleados empleado){
	 TypedQuery<Prestamos> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
	    
	    
		q = em.createNamedQuery("Prestamos.findByCodEmp", Prestamos.class )		    
                    .setParameter("codEmp",  empleado.getEmpleadosPK().getCodEmp() )                    
		    .setParameter("codCia",  codCia );
	    
		 
         return q.getResultList();
    
    }      

}
