package com.entities;

import com.entities.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "vplanillaController")
@ViewScoped
public class VplanillaController extends AbstractController<Vplanilla> implements Serializable {
    @EJB
    private MovDpFacade movDpFacade;
    @EJB
    private ProgramacionPlaFacade programacionPlaFacade;
    ProgramacionPla programacionpla;
    String estado;
    List<Vplanilla> listvplanilla;
    List<ProgramacionPla>  programacionplas;
    List<MovDp> lmovDp;

    public List<MovDp> getLmovDp() {
        return lmovDp;
    }

    public void setLmovDp(List<MovDp> lmovDp) {
        this.lmovDp = lmovDp;
    }
    
    
    public List<Vplanilla> getListvplanilla() {
        return listvplanilla;
    }

    public void setListvplanilla(List<Vplanilla> listvplanilla) {
        this.listvplanilla = listvplanilla;
    }

    public List<ProgramacionPla> getProgramacionplas() {
        return programacionplas;
    }

    public void setProgramacionplas(List<ProgramacionPla> programacionplas) {
        this.programacionplas = programacionplas;
    }

    
    
    public ProgramacionPla getProgramacionpla() {
        return programacionpla;
    }

    public void setProgramacionpla(ProgramacionPla programacionpla) {
        this.programacionpla = programacionpla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    @EJB
    private VplanillaFacade ejbFacade;

    public VplanillaController() {
        super(Vplanilla.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }


    
    
    public List<Vplanilla> consultarByPlanilla (){	      
            this.items = ejbFacade.findByPk(programacionpla);
	if (!isValidationFailed()) {
	    this.setSelected(null); 
	}
	JsfUtil.contar_registros(items.size() );
	return this.items;
    } 
    
    
    public void ChangeEstadoPlanilla() {  
        if(estado !=null && !estado.equals(""))  
            programacionplas = programacionPlaFacade.findByEstado(estado);        
    }     

    public String detalleMov(){
        if(this.getSelected()!=null){
            lmovDp = movDpFacade.findByPla(this.getSelected());
        }
        return "";        
    }    
}
