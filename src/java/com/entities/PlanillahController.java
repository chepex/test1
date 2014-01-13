package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "planillahController")
@ViewScoped
public class PlanillahController extends AbstractController<Planillah> implements Serializable {

    @EJB
    private PlanillahFacade ejbFacade;

    public PlanillahController() {
        super(Planillah.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPlanillahPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
        this.getSelected().getPlanillahPK().setCodCia(this.getSelected().getDepartamentos().getDepartamentosPK().getCodCia());
        this.getSelected().getPlanillahPK().setSecuencia(this.getSelected().getProgramacionPla().getProgramacionPlaPK().getSecuencia());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPlanillahPK(new com.entities.PlanillahPK());
    }
}
