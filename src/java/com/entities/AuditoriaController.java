package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "auditoriaController")
@ViewScoped
public class AuditoriaController extends AbstractController<Auditoria> implements Serializable {

    @EJB
    private AuditoriaFacade ejbFacade;

    public AuditoriaController() {
        super(Auditoria.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
