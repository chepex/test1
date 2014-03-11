package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "paisesController")
@ViewScoped
public class PaisesController extends AbstractController<Paises> implements Serializable {

    @EJB
    private PaisesFacade ejbFacade;

    public PaisesController() {
        super(Paises.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
