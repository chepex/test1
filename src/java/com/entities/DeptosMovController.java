package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "deptosMovController")
@ViewScoped
public class DeptosMovController extends AbstractController<DeptosMov> implements Serializable {

    @EJB
    private DeptosMovFacade ejbFacade;

    public DeptosMovController() {
        super(DeptosMov.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getDeptosMovPK().setCodCia(this.getSelected().getCatDp().getCatDpPK().getCodCia());
        this.getSelected().getDeptosMovPK().setCodCat(this.getSelected().getCatDp().getCatDpPK().getCodCat());
        this.getSelected().getDeptosMovPK().setCodDepto(this.getSelected().getDepartamentos().getDepartamentosPK().getCodDepto());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDeptosMovPK(new com.entities.DeptosMovPK());
    }
}
