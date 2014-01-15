package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "planillaIsssController")
@ViewScoped
public class PlanillaIsssController extends AbstractController<PlanillaIsss> implements Serializable {

    @EJB
    private PlanillaIsssFacade ejbFacade;

    public PlanillaIsssController() {
        super(PlanillaIsss.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPlanillaIsssPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
        this.getSelected().getPlanillaIsssPK().setCodCia(this.getSelected().getEmpleados().getEmpleadosPK().getCodCia());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPlanillaIsssPK(new com.entities.PlanillaIsssPK());
    }
}
