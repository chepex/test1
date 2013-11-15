package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "departamentosController")
@ViewScoped
public class DepartamentosController extends AbstractController<Departamentos> implements Serializable {

    @EJB
    private DepartamentosFacade ejbFacade;

    public DepartamentosController() {
	super(Departamentos.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setDepartamentosPK(new com.entities.DepartamentosPK());
    }
}
