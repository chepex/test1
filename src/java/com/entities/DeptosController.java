package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "deptosController")
@ViewScoped
public class DeptosController extends AbstractController<Deptos> implements Serializable {

    @EJB
    private DeptosFacade ejbFacade;

    public DeptosController() {
        super(Deptos.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getDeptosPK().setCodPais(this.getSelected().getPaises().getCodPais());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDeptosPK(new com.entities.DeptosPK());
    }
}
