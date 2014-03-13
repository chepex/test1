package com.entities;

import com.entities.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;


@ManagedBean(name="empleadosController")
@ViewScoped
public class EmpleadosController extends AbstractController<Empleados> implements Serializable {
    @EJB
    private DetEmpleadoFacade detEmpleadoFacade;

@EJB
private EmpleadosFacade ejbFacade;

public Empleados selected2;
public String nomb;
public String apel;
public int emp;
public List<Empleados> items2;
public List<Empleados> activos;
private String userName;
private String nvaClave;
private String confClave;
   



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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNvaClave() {
        return nvaClave;
    }

    public void setNvaClave(String nvaClave) {
        this.nvaClave = nvaClave;
    }

    public String getConfClave() {
        return confClave;
    }

    public void setConfClave(String confClave) {
        this.confClave = confClave;
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
        if(this.getSelected().getEmpleadosPK().getCodEmp() ==0) {
            int codemp = ejbFacade.Sequence("EMPLEADOS");
            this.getSelected().getEmpleadosPK().setCodEmp(codemp);
            
        }

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
    

   /* public void UpdDetEmpleado(){
        List<DetEmpleado> ldetm= detEmpleadoFacade.findByEmp(this.getSelected());
        if(ldetm.isEmpty()){
            
        }else{
            
            detEmpleadoFacade.remove();
        }
        
       
        
    }*/

    
  /**
     * Metodo que actualiza el cambio de contraseña
     * @param event evento del boton.
     * @throws Exception error generico.
     */
    public void actualizaClave(ActionEvent event) throws Exception {
        String a = acepta();
        if(a.equals("ok")) {
            userName = this.getSelected().getUserName();
            this.getSelected().setUserName(userName);
            this.getSelected().setPassword(JsfUtil.EncriptadorMD5(this.nvaClave));
            String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Updated");
            persist(AbstractController.PersistAction.UPDATE, msg);
        } else {
            JsfUtil.addErrorMessage(a);
        }  
    }
    /**
     * Metodo que ejecuta el cambio de clave
     * @return un mensaje de exito o fracaso
     */
    public String acepta() {
        String msg="";
        if (this.nvaClave.equals("") 
                || this.confClave.equals("")) {
            msg = "Por favor ingrese la nueva contraseña";
        } else if (!this.nvaClave.equals(this.confClave)) {
            msg = "Las contraseñas no coinciden. Por "
                    + " favor, ingrese de nuevo la nueva contraseña";
        } else {
            try {
                msg = "ok";
                JsfUtil.addSuccessMessage("Cambio efectuado con exito!!!");
            } catch (Exception e) {
                Logger.getLogger(e.getMessage());
            }
        }
        return msg;
    }    

    
    public void handleChangeApellidos(AjaxBehaviorEvent vce){
      String lastname= (String) ((UIOutput) vce.getSource()).getValue();
      this.getSelected().setNombreIsss(lastname);
      this.getSelected().setNombreNit(lastname);
    }

     public void handleChangeNombres(AjaxBehaviorEvent vce){
        String name= (String) ((UIOutput) vce.getSource()).getValue();
        this.getSelected().setNombreIsss(this.getSelected().getNombreIsss().concat(" ".concat(name)));
        this.getSelected().setNombreNit(this.getSelected().getNombreNit().concat(" ".concat(name)));
    }


}    
    

  

