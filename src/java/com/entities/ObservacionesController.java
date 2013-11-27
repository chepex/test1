package com.entities;


import com.entities.AbstractController;
import com.entities.Observaciones;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "observacionesController")
@ViewScoped
public class ObservacionesController extends AbstractController<Observaciones> implements Serializable {

    @EJB
    private ObservacionesFacade ejbFacade;
    public List<Observaciones> obs;

    public ObservacionesController() {
        super(Observaciones.class);
    }

    public List<Observaciones> getObs() {
        obs = ejbFacade.findAll();
        return obs;
    }

    public void setObs(List<Observaciones> obs) {
        this.obs = obs;
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
