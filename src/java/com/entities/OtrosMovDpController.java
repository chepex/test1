package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "otrosMovDpController")
@ViewScoped
public class OtrosMovDpController extends AbstractController<OtrosMovDp> implements Serializable {

    @EJB
    private OtrosMovDpFacade ejbFacade;

    public OtrosMovDpController() {
	super(OtrosMovDp.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
	this.getSelected().getOtrosMovDpPK().setCodCia(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodCia());
	this.getSelected().getOtrosMovDpPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
	this.getSelected().getOtrosMovDpPK().setSecuencia(this.getSelected().getProgramacionPla().getProgramacionPlaPK().getSecuencia());
	this.getSelected().getOtrosMovDpPK().setCodDp(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodDp());
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setOtrosMovDpPK(new com.entities.OtrosMovDpPK());
    }
}
