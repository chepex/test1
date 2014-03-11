package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "municipiosController")
@ViewScoped
public class MunicipiosController extends AbstractController<Municipios> implements Serializable {

    @EJB
    private MunicipiosFacade ejbFacade;

    public MunicipiosController() {
        super(Municipios.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getMunicipiosPK().setCodZona(this.getSelected().getDeptos().getDeptosPK().getZona());
        this.getSelected().getMunicipiosPK().setCodDepto(this.getSelected().getDeptos().getDeptosPK().getCodDepto());
        this.getSelected().getMunicipiosPK().setCodPais(this.getSelected().getDeptos().getDeptosPK().getCodPais());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setMunicipiosPK(new com.entities.MunicipiosPK());
    }
}
