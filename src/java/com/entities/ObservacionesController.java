package com.entities;


import com.entities.AbstractController;
import com.entities.Observaciones;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "observacionesController")
@ViewScoped
public class ObservacionesController extends AbstractController<Observaciones> implements Serializable {

    @EJB
    private ObservacionesFacade ejbFacade;

    public ObservacionesController() {
        super(Observaciones.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setObservacionesPK(new com.entities.ObservacionesPK());
    }
}
