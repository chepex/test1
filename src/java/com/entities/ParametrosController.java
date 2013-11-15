package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "parametrosController")
@ViewScoped
public class ParametrosController extends AbstractController<Parametros> implements Serializable {

    @EJB
    private ParametrosFacade ejbFacade;

    public ParametrosController() {
	super(Parametros.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setParametrosPK(new com.entities.ParametrosPK());
    }
}
