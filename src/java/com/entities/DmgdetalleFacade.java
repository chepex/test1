/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.Date;
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
public class DmgdetalleFacade extends AbstractFacade<Dmgdetalle> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DmgdetalleFacade() {
        super(Dmgdetalle.class);
    }
    
    

    public Dmgdetalle findByCuenta(Dmgdetalle detalle){
	 TypedQuery<Dmgdetalle> q;
	 try{
		 q = em.createNamedQuery("Dmgdetalle.findByCuenta", Dmgdetalle.class )		    		    
                    .setParameter("codCia",  detalle.getDmgdetallePK().getCodCia())
		    .setParameter("tipoDocto",  detalle.getDmgdetallePK().getTipoDocto() )
                    .setParameter("numPoliza",  detalle.getDmgdetallePK().getNumPoliza() )
                    .setParameter("proyecto",  detalle.getProyecto() )
                    .setParameter("fecha",  detalle.getDmgdetallePK().getFecha() )
                    .setParameter("cta1",  detalle.getCta1() )
                    .setParameter("cta2",  detalle.getCta2() )
                    .setParameter("cta3",  detalle.getCta3() )
                    .setParameter("cta4",  detalle.getCta4() )
                    .setParameter("cta5",  detalle.getCta5() );
                     
         return q.getSingleResult();
         }catch(NoResultException ex){
            return null;
        }catch(Exception ex){
            return null;
        }
    }  
    
    
 public List<Dmgdetalle> findByFecha(Date fecha){
	 TypedQuery<Dmgdetalle> q;
	 
              LoginBean lb= new LoginBean();	
	    short codCia = lb.sscia();	
		 q = em.createNamedQuery("Dmgdetalle.findByPkFecha", Dmgdetalle.class )		    		    
                    .setParameter("codCia", codCia)
		    .setParameter("tipoDocto","PL" )                    
                    .setParameter("fecha",  fecha );
                     
         return q.getResultList();
         
    }      
    
}
