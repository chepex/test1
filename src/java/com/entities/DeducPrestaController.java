package com.entities;

import com.ejb.SB_auditoria;
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
    @EJB
    private SB_auditoria sB_auditoria;
public List<DeducPresta> cprestamos;
public List<DeducPresta> LAfp;
public List<DeducPresta> LIsss;

    public List<DeducPresta> getLIsss() {
         LIsss= ejbFacade.findByIsss();
        return LIsss;
    }

    public void setLIsss(List<DeducPresta> LIsss) {
        this.LIsss = LIsss;
    }


    public List<DeducPresta> getLAfp() {
       LAfp= ejbFacade.findByAfp();
        return LAfp;
    }

    public void setLAfp(List<DeducPresta> LAfp) {
        this.LAfp = LAfp;
    }



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
    
      
    @Override  
    public void postCreate(){
      sB_auditoria.registrar_audit(this.getAccion() , this.getSelected().toString(), this.getSelected().getClass().getName());
    }   
    
    
}
