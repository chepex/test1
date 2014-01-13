package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "dmgpolizaController")
@ViewScoped
public class DmgpolizaController extends AbstractController<Dmgpoliza> implements Serializable {

    @EJB
    private DmgpolizaFacade ejbFacade;

    public DmgpolizaController() {
        super(Dmgpoliza.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDmgpolizaPK(new com.entities.DmgpolizaPK());
    }
}
