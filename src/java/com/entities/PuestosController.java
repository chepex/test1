package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "puestosController")
@ViewScoped
public class PuestosController extends AbstractController<Puestos> implements Serializable {

    @EJB
    private PuestosFacade ejbFacade;

    public PuestosController() {
	super(Puestos.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setPuestosPK(new com.entities.PuestosPK());
    }
}
