/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import com.ejb.SB_Calculos;
import com.entities.util.JsfUtil;
import java.math.BigInteger;
import java.util.ArrayList;
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
public class EmpleadosFacade extends AbstractFacade<Empleados> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public EmpleadosFacade() {
	super(Empleados.class);
    }
    
public  List<Empleados> findbyNameAndPk( int emp,String apellidos,String nombres){
     LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
            
 	     TypedQuery<Empleados> q;
	     if(emp ==0){
		 q = em.createNamedQuery("Empleados.findByFiltros", Empleados.class )		    
		    .setParameter("codCia", "%"+ codCia + "%" )
		    .setParameter("apellidos", "%" + apellidos + "%")
		    .setParameter("nombres", "%" + nombres + "%");	
	     }else{
		    q = em.createNamedQuery("Empleados.findByPk2", Empleados.class )		    
		    .setParameter("codCia",  codCia)			 
		    .setParameter("codEmp",  emp);
	     } 
         return q.getResultList();
    }   

public  Empleados findbyCodemp( int emp){
    try{
     LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
            
 	     TypedQuery<Empleados> q;     

		    q = em.createNamedQuery("Empleados.findByPk2", Empleados.class )		    
		    .setParameter("codCia",  codCia)			 
		    .setParameter("codEmp",  emp);
            System.out.print("Empleado "+ emp);
         return q.getSingleResult();
    }catch(Exception ex){
           JsfUtil.logs(ex , "Surgio un error", "Proceso findbyCodemp codigo empleado : " +emp,EmpleadosFacade.class,"ERROR");             
                
        return null;
    }
    }  

public  Empleados findbyUsuario(String usuario){
 	     TypedQuery<Empleados> q;	 
	     LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
		 q = em.createNamedQuery("Empleados.findByUsuario", Empleados.class )		    
		    .setParameter("codCia",  codCia)
		    .setParameter("usuario", "%"+ usuario + "%" );
		 
         return q.getSingleResult();
    }   

    @Override
    public List<Empleados> findAll(){
	 TypedQuery<Empleados> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("Empleados.findByCodCia", Empleados.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }

    public List<Empleados> findByNombreNit(String nombreNit){
	 TypedQuery<Empleados> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("Empleados.findByNombreNit", Empleados.class )		    
		    .setParameter("nombreNit",  "%" +nombreNit  + "%")
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }  
    
    public List<Empleados> activos(){
	 TypedQuery<Empleados> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	    String act = "A";
	
		 q = em.createNamedQuery("Empleados.findByStatus", Empleados.class )		    
		    .setParameter("status",  "%" +act  + "%")
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }      

public  List<Empleados> findbytipoPla(  ProgramacionPla tipopla){
 	     TypedQuery<Empleados> q;
	     short tipopla2 = tipopla.getTiposPlanilla().getTiposPlanillaPK().getCodTipopla();
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	      String act = "A";
		 q = em.createNamedQuery("Empleados.findByTipoPla", Empleados.class )		    
		    .setParameter("codTipopla", tipopla2  )
		    .setParameter("status",  "%" +act  + "%")
		    .setParameter("codCia",  codCia );		    
         return q.getResultList() ;
    }      

public  List<Empleados> findbyVacc(  ProgramacionPla tipopla){
    /*vacaciones colectivas*/
 	     TypedQuery<Empleados> q;
	     short tipopla2 = tipopla.getTiposPlanilla().getTiposPlanillaPK().getCodTipopla();
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	    String act = "A";
               BigInteger vac= new BigInteger("1"); 
		 q = em.createNamedQuery("Empleados.findByVac", Empleados.class )		    
		    .setParameter("vacaciones", vac  )
		    .setParameter("status",  "%" +act  + "%")
		    .setParameter("codCia",  codCia );		    
         return q.getResultList() ;
    } 

    public List<Empleados> findbyDeptos(List<Departamentos> deptos){
         TypedQuery<Empleados> q;	 
                LoginBean lb= new LoginBean();	
                short codCia = lb.sscia();	 
                String act = "A";
                    List<Departamentos>   iterador =    deptos;
                    ArrayList<Short> listdeptos = new ArrayList<Short>(); 		
                for( Departamentos d : iterador ){  
                    listdeptos.add( d.departamentosPK.getCodDepto());
                }	   
                     q = em.createNamedQuery("Empleados.findByDeptos", Empleados.class )		    
                        .setParameter("departamentos", listdeptos)
                        .setParameter("status",  "%" +act  + "%")
                        .setParameter("codCia", codCia);
             return q.getResultList();
    }
    
    
}
