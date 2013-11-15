package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="empleadosController")
@ViewScoped
public class EmpleadosController extends AbstractController<Empleados> implements Serializable {

@EJB
private EmpleadosFacade ejbFacade;

public Empleados selected2;
public String nomb;
public String apel;
public int emp;
public List<Empleados> items2;
public List<Empleados> activos;

   



    public EmpleadosController() {
        super(Empleados.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    
    
 public Empleados getSelected2() {
	  if(this.selected2== null){
	    this.selected2 = new Empleados();
	    initializeEmbeddableKey();
        } 
	return selected2;
    }

    public void setSelected2(Empleados selected2) {
	this.selected2 = selected2;
    }

    public String getNomb() {
	return nomb;
    }

    public void setNomb(String nomb) {
	this.nomb = nomb;
    }

    public String getApel() {
	return apel;
    }

    public void setApel(String apel) {
	this.apel = apel;
    }



    public int getEmp() {
	return emp;
    }

    public void setEmp(int emp) {
	this.emp = emp;
    }

    public List<Empleados> getItems2() {
	return items2;
    }

    public void setItems2(List<Empleados> items2) {
	this.items2 = items2;
    }
    
    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getEmpleadosPK().setCodCia(this.getSelected().getDepartamentos().getDepartamentosPK().getCodCia());
    }

    @Override
    protected void initializeEmbeddableKey() {
            this.getSelected().setEmpleadosPK(new com.entities.EmpleadosPK());
    }

    
    
    
    public void Buscar(){
	
	//HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
	
	this.setItems2( ejbFacade.findbyNameAndPk(emp, apel,nomb));	
	
   }
    
    public List<Empleados> list_empleados(String query){
	return ejbFacade.findByNombreNit(query);	
	
    }



    public List<Empleados> getActivos() {
	if (activos == null) {
	    activos = this.ejbFacade.activos();
	    
	}	
	return activos;
    }

    public void setActivos(List<Empleados> activos) {
	this.activos = activos;
    }
    
    
    

}
