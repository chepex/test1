package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "profesionesController")
@ViewScoped
public class ProfesionesController extends AbstractController<Profesiones> implements Serializable {

    @EJB
    private ProfesionesFacade ejbFacade;

    public ProfesionesController() {
	super(Profesiones.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }
}
