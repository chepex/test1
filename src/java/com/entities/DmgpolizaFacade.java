/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mmixco
 */
@Stateless
public class DmgpolizaFacade extends AbstractFacade<Dmgpoliza> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DmgpolizaFacade() {
        super(Dmgpoliza.class);
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
                em.flush();
        return val2;
    
    } 
    
}
