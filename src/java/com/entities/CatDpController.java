package com.entities;


import com.ejb.SB_auditoria;
import com.ejb.SB_readXLS;
import com.entities.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import jxl.read.biff.BiffException;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "catDpController")
@ViewScoped
public class CatDpController extends AbstractController<CatDp> implements Serializable {
    @EJB
    private SB_auditoria sB_auditoria;
    @EJB
    private SB_readXLS sB_readXLS;

    @EJB
    private CatDpFacade ejbFacade;
    
  

    public CatDpController() {
        super(CatDp.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setCatDpPK(new com.entities.CatDpPK());
    }
    
    @Override  
    public void postCreate(){
      sB_auditoria.registrar_audit(this.getAccion() , this.getSelected().toString(), this.getSelected().getClass().getName());
    }   
    
}
