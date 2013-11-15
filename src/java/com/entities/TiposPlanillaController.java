package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tiposPlanillaController")
@ViewScoped
public class TiposPlanillaController extends AbstractController<TiposPlanilla> implements Serializable {

    @EJB
    private TiposPlanillaFacade ejbFacade;

    public TiposPlanillaController() {
	super(TiposPlanilla.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setTiposPlanillaPK(new com.entities.TiposPlanillaPK());
    }



    
}
