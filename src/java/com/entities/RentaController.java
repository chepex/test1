package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "rentaController")
@ViewScoped
public class RentaController extends AbstractController<Renta> implements Serializable {

    @EJB
    private RentaFacade ejbFacade;

    public RentaController() {
        super(Renta.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getRentaPK().setCodCia(this.getSelected().getRentaPK().getCodCia());
    }
    
    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setRentaPK(new com.entities.RentaPK());
    }

}
