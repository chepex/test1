package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "detEmpleadoController")
@ViewScoped
public class DetEmpleadoController extends AbstractController<DetEmpleado> implements Serializable {

    @EJB
    private DetEmpleadoFacade ejbFacade;

    public DetEmpleadoController() {
        super(DetEmpleado.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDetEmpleadoPK(new com.entities.DetEmpleadoPK());
    }
}
