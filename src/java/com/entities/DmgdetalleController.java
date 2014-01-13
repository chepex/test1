package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "dmgdetalleController")
@ViewScoped
public class DmgdetalleController extends AbstractController<Dmgdetalle> implements Serializable {

    @EJB
    private DmgdetalleFacade ejbFacade;

    public DmgdetalleController() {
        super(Dmgdetalle.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getDmgdetallePK().setFecha(this.getSelected().getDmgpoliza().getDmgpolizaPK().getFecha());
        this.getSelected().getDmgdetallePK().setTipoDocto(this.getSelected().getDmgpoliza().getDmgpolizaPK().getTipoDocto());
        this.getSelected().getDmgdetallePK().setNumPoliza(this.getSelected().getDmgpoliza().getDmgpolizaPK().getNumPoliza());
        this.getSelected().getDmgdetallePK().setCodCia(this.getSelected().getDmgpoliza().getDmgpolizaPK().getCodCia());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDmgdetallePK(new com.entities.DmgdetallePK());
    }
}
