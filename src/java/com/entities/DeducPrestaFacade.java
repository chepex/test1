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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author mmixco
 */
@Stateless
public class DeducPrestaFacade extends AbstractFacade<DeducPresta> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
	return em;
    }

    public DeducPrestaFacade() {
	super(DeducPresta.class);
    }
    
    @Override
    public List<DeducPresta> findAll(){
	 TypedQuery<DeducPresta> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("DeducPresta.findByCodCia", DeducPresta.class )		    
		    .setParameter("codCia",  codCia );
         return q.getResultList();
    
    }    
    
    
    public DeducPresta findCodDeduc(MovDp movdp){
	 TypedQuery<DeducPresta> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("DeducPresta.findByCodDp", DeducPresta.class )		    
		    .setParameter("codCia",  codCia )
                 .setParameter("codDp",  movdp.getMovDpPK().getCodDp() );
         return q.getSingleResult();
    
    }  
   
    public List<DeducPresta> findByCat(String catDp){
	 TypedQuery<DeducPresta> q;
	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("DeducPresta.findByCatDp", DeducPresta.class )		    
                    .setParameter("catDp","%" + catDp  + "%")
		    .setParameter("codCia",  codCia );
         return q.getResultList(); 
    }   
    
    public DeducPresta findByTipoPla(TiposPlanilla tiposPlanilla){
	 TypedQuery<DeducPresta> q;
	 try{
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();
	
		 q = em.createNamedQuery("DeducPresta.findByTipoPla", DeducPresta.class )		    
                    .setParameter("codTipopla", tiposPlanilla.getTiposPlanillaPK().getCodTipopla()  )
		    .setParameter("codCia",  codCia );
         return q.getSingleResult();
         }catch(Exception ex){
             
             return null;
             
         }
    }       
    
    

        
    public Short Sequence(String seq_name ){	 	 
	    LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		Query q =  em.createNativeQuery("Select nvl(SEQ_COUNT ,0)+1 from SEQUENCE_TABLE s where s.cod_Cia = ?  and SEQ_NAME = ?" );		                                                   
                q.setParameter(1, codCia);  
                q.setParameter(2, seq_name);  
                BigDecimal val = (BigDecimal)q.getSingleResult(); 
                short val2 = val.shortValue();
                
                Query q2 =  em.createNativeQuery("UPDATE SEQUENCE_TABLE SET SEQ_COUNT = SEQ_COUNT+1 WHERE cod_Cia = ?  and SEQ_NAME = ?" );		                                                       
                q2.setParameter(1, codCia);  
                q2.setParameter(2, seq_name);                  
                q2.executeUpdate();
        return val2;
    
    }        
}
