package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="ciasController")
@ViewScoped
public class CiasController extends AbstractController<Cias> implements Serializable {
    public Cias selected2;

    public Cias getSelected2() {
	return selected2;
    }

    public void setSelected2(Cias selected2) {
	this.selected2 = selected2;
    }
    @EJB
    private CiasFacade ejbFacade;

    public CiasController() {
        super(Cias.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }





}
