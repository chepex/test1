package com.entities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "sequenceTableController")
@ViewScoped
public class SequenceTableController extends AbstractController<SequenceTable> implements Serializable {

    @EJB
    private SequenceTableFacade ejbFacade;

    public SequenceTableController() {
        super(SequenceTable.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setSequenceTablePK(new com.entities.SequenceTablePK());
    }
}
