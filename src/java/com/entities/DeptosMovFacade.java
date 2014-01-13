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
public class DeptosMovFacade extends AbstractFacade<DeptosMov> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeptosMovFacade() {
        super(DeptosMov.class);
    }
    
    @Override
    public List<DeptosMov> findAll(){
	 TypedQuery<DeptosMov> q;	 
         LoginBean lb= new LoginBean();	
         short codCia = lb.sscia();	
            q = em.createNamedQuery("DeptosMov.findByCodCia", DeptosMov.class )		    
                .setParameter("codCia",  codCia );
         return q.getResultList();    
    }  
    
    
    public List<DeptosMov> findSumaResta(CatDp catDp){
            TypedQuery<DeptosMov> q;	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
		 q = em.createNamedQuery("DeptosMov.findBySumaResta", DeptosMov.class )		    
                    .setParameter("SumaResta",  catDp.getSumaResta() )
		    .setParameter("codCia",  codCia );                 
            return q.getResultList();    
    }   
    
    public DeptosMov findCatdp(CatDp catDp , Departamentos depto){
            TypedQuery<DeptosMov> q;	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
		 q = em.createNamedQuery("DeptosMov.findByCodCat", DeptosMov.class )		    
                    .setParameter("codCia",  codCia )
                    .setParameter("codDepto",  depto.getDepartamentosPK().getCodDepto() )
                    .setParameter("codCat",  catDp.getCatDpPK().getCodCat() );		    
            return q.getSingleResult();
    }       
}
