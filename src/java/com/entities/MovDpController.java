package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "movDpController")
@ViewScoped
public class MovDpController extends AbstractController<MovDp> implements Serializable {

    @EJB
    private MovDpFacade ejbFacade;

    public MovDpController() {
	super(MovDp.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
       
            this.getSelected().getMovDpPK().setCodCia(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodCia());
            this.getSelected().getMovDpPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
            this.getSelected().getMovDpPK().setCodDp(this.getSelected().getDeducPresta().getDeducPrestaPK().getCodDp());
            this.getSelected().getMovDpPK().setSecuencia(this.getSelected().getProgramacionPla().getProgramacionPlaPK().getSecuencia());            
       
	
    }

    @Override
    protected void initializeEmbeddableKey() {
	this.getSelected().setMovDpPK(new com.entities.MovDpPK());
    }
}
