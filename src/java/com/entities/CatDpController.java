package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "catDpController")
@ViewScoped
public class CatDpController extends AbstractController<CatDp> implements Serializable {

    @EJB
    private CatDpFacade ejbFacade;

    public CatDpController() {
        super(CatDp.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setCatDpPK(new com.entities.CatDpPK());
    }
}
