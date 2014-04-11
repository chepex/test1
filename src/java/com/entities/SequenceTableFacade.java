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
public class SequenceTableFacade extends AbstractFacade<SequenceTable> {
        @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SequenceTableFacade() {
        super(SequenceTable.class);
    }
    
    

  public long Sequence(String tabla){
        List<SequenceTable> lseq;
        long seq=0;
        SequenceTable q;
         LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
            SequenceTablePK pk = new SequenceTablePK(tabla,codCia);
            q=em.find(SequenceTable.class,pk);
         if(q==null){
             SequenceTable ts = new SequenceTable(tabla ,codCia );            
             ts.setSeqCount((long)1);
             this.edit(ts);            
             seq= 1;            
         }else{
            // SequenceTable ts =  q.get(0);
             seq =q.getSeqCount()+(long) 1;            
             q.setSeqCount((long)seq);
             this.edit(q);  
         }
        
         return seq;
    }       
    
}
