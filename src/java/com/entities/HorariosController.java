package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "horariosController")
@ViewScoped
public class HorariosController extends AbstractController<Horarios> implements Serializable {

    @EJB
    private HorariosFacade ejbFacade;

    public HorariosController() {
        super(Horarios.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getHorariosPK().setCodCia(this.getSelected().getDepartamentos().getDepartamentosPK().getCodCia());
        this.getSelected().getHorariosPK().setCodDepto(this.getSelected().getDepartamentos().getDepartamentosPK().getCodDepto());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setHorariosPK(new com.entities.HorariosPK());
    }
}
