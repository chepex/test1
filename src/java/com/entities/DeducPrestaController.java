package com.entities;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "deducPrestaController")
@ViewScoped
public class DeducPrestaController extends AbstractController<DeducPresta> implements Serializable {
public List<DeducPresta> cprestamos;

    public List<DeducPresta> getCprestamos() {
        cprestamos = ejbFacade.findByCat("Prestamos");
        return cprestamos;
    }

    public void setCprestamos(List<DeducPresta> cprestamos) {
        this.cprestamos = cprestamos;
    }


    @EJB
    private DeducPrestaFacade ejbFacade;

    public DeducPrestaController() {
	super(DeducPresta.class);
    }

    @PostConstruct
    public void init() {
	super.setFacade(ejbFacade);
    }

    @Override
    protected void initializeEmbeddableKey() {
        
	this.getSelected().setDeducPrestaPK(new com.entities.DeducPrestaPK());
        
        
    }
    

      
      @Override     
    public void saveNew(ActionEvent event) {
          
         
        short Sequence = ejbFacade.Sequence("DEDUC_PRESTA_SEQ");
        this.getSelected().getDeducPrestaPK().setCodDp(Sequence);
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Created");
        persist(AbstractController.PersistAction.CREATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
        }
    }      
    
}
