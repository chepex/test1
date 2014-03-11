package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "zonasController")
@ViewScoped
public class ZonasController extends AbstractController<Zonas> implements Serializable {

    @EJB
    private ZonasFacade ejbFacade;

    public ZonasController() {
        super(Zonas.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getZonasPK().setCodPais(this.getSelected().getPaises().getCodPais());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setZonasPK(new com.entities.ZonasPK());
    }
}
