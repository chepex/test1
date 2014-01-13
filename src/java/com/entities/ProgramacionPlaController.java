package com.entities;

import com.entities.util.JsfUtil;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name="programacionPlaController")
@ViewScoped
public class ProgramacionPlaController extends AbstractController<ProgramacionPla> implements Serializable {
 


    @EJB
    private ProgramacionPlaFacade ejbFacade;

    public ProgramacionPlaController() {
        super(ProgramacionPla.class);
    }
    

    
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getProgramacionPlaPK().setCodCia(this.getSelected().getTiposPlanilla().getTiposPlanillaPK().getCodCia());
    }

    @Override
    protected void initializeEmbeddableKey() {
            this.getSelected().setProgramacionPlaPK(new com.entities.ProgramacionPlaPK());
    }

    @Override
    public void saveNew(ActionEvent event) {
	String mes= String.format("%02d", this.getSelected().getMes());
		
	String anio= String.format("%04d",this.getSelected().getAnio() );
	String sc= anio+""+ mes +""
		 + this.getSelected().getTiposPlanilla().getTiposPlanillaPK().getCodTipopla() +""
		 + this.getSelected().getNumPlanilla().toString() ;
	 long secuencia = Long.parseLong( sc ) ;
	 getSelected().getProgramacionPlaPK().setSecuencia(secuencia);
         if(getSelected().getRecalculo().isEmpty()){
             getSelected().setRecalculo("N");
         }         
         getSelected().setStatus("P");
	 String msg = ResourceBundle.getBundle("/MyBundle").getString(super.itemClass.getSimpleName()  + "Created");
	 
	 super.persist(PersistAction.CREATE, msg); 
	 
	 if (!isValidationFailed()) {
	   super.items = null;
	   setSelected(null);
	}	 
    }

    
  
}
