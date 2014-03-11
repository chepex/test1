package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "nivelesSeguridadController")
@ViewScoped
public class NivelesSeguridadController extends AbstractController<NivelesSeguridad> implements Serializable {

    @EJB
    private NivelesSeguridadFacade ejbFacade;

    public NivelesSeguridadController() {
        super(NivelesSeguridad.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setNivelesSeguridadPK(new com.entities.NivelesSeguridadPK());
    }
}
