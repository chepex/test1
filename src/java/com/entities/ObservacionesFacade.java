/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;


import com.entities.AbstractFacade;
import com.entities.Observaciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mmixco
 */
@Stateless
public class ObservacionesFacade extends AbstractFacade<Observaciones> {
    @PersistenceContext(unitName = "planilla2013PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObservacionesFacade() {
        super(Observaciones.class);
    }
    
}
