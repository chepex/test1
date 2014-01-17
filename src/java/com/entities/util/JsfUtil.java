package com.entities.util;

import com.ejb.SB_Planilla;
import com.entities.Mensaje;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import javax.print.attribute.standard.Severity;

public class JsfUtil {
 private static Logger LOGGER ;

    public static void addErrorMessage(Exception ex, String defaultMsg) {
	String msg = ex.getLocalizedMessage();
	if (msg != null && msg.length() > 0) {
	    addErrorMessage(msg);
	} else {
	    addErrorMessage(defaultMsg);
	}
    }

    public static void addErrorMessages(List<String> messages) {
	for (String message : messages) {
	    addErrorMessage(message);
	}
    }

    public static void addErrorMessage(String msg) {
	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
	FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	FacesContext.getCurrentInstance().validationFailed(); // Invalidate JSF page if we raise an error message

    }

    public static void addErrorMessage(Mensaje msg) {
	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,  msg.getTitulo(),  msg.getMensajes());
	FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	FacesContext.getCurrentInstance().validationFailed(); // Invalidate JSF page if we raise an error message

    }    
    
    public static void addSuccessMessage(String msg) {
	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
	FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static void addSuccessMessage(Mensaje  msg) {
	if( msg.getTitulo().equals("ok")){
	    FacesMessage facesMsg = new FacesMessage( FacesMessage.SEVERITY_INFO, msg.getTitulo(), msg.getMensajes());
	    FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
	    if(!msg.getDescripcion().isEmpty())	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro", msg.getDescripcion() ));  
	    
	} else{
	   FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,  msg.getTitulo(),  msg.getMensajes());
	   FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	   if(!msg.getDescripcion().isEmpty()) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro: ", msg.getDescripcion() ));  
	   FacesContext.getCurrentInstance().validationFailed();
	}
	
    }    

    public static Throwable getRootCause(Throwable cause) {
	if (cause != null) {
	    Throwable source = cause.getCause();
	    if (source != null) {
		return getRootCause(source);
	    } else {
		return cause;
	    }
	}
	return null;
    }

    public static boolean isValidationFailed() {
	return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static boolean isDummySelectItem(UIComponent component, String value) {
	for (UIComponent children : component.getChildren()) {
	    if (children instanceof UISelectItem) {
		UISelectItem item = (UISelectItem) children;
		if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
		    return true;
		}
		break;
	    }
	}
	return false;
    }
    
public static int diasDelMes(int mes, int año){
    mes= mes-1;
    switch(mes){
	case 0:  // Enero
	case 2:  // Marzo
	case 4:  // Mayo
	case 6:  // Julio
	case 7:  // Agosto
	case 9:  // Octubre
	case 11: // Diciembre
	    return 31;
	case 3:  // Abril
	case 5:  // Junio
	case 8:  // Septiembre
	case 10: // Noviembre
	    return 30;
	case 1:  // Febrero
	    if ( ((año%100 == 0) && (año%400 == 0)) ||
		((año%100 != 0) && (año%  4 == 0))   )
		return 29;  // Año Bisiesto
	    else
		return 28;
	default:
	    throw new java.lang.IllegalArgumentException("El mes debe estar entre 0 y 11");
    }
}

public static void contar_registros( int registros ){
    Mensaje msg = new Mensaje();
    if( registros > 0 ){
	    msg.setTitulo("ok");
	    msg.setMensajes("Se realizo la consulta correctamente");
	}else{
	    msg.setTitulo("error");
	    msg.setMensajes("Surgio un error al realizar la consulta");
	} 
    msg.setDescripcion(registros + "  registros");
    JsfUtil.addSuccessMessage( msg);    
}

public static void excepcion (Exception ex,String proceso ){
      JsfUtil.addErrorMessage(ex, "Surgio un error en el proceso"+proceso+ex.getMessage() );
      System.out.println("Surgio un error en el proceso "+proceso+ex.getMessage());
      LOGGER.log(Level.SEVERE, "Surgio un error en el proceso {0}"+ex.getMessage(), proceso);    
}

public static double Redondear(double numero,int digitos)
{
      int cifras=(int) Math.pow(10,digitos);
      return Math.rint(numero*cifras)/cifras;
}

public static void logs(Exception ex,String summary , String detail, Class cl, String level){    
    if(level.equals("ERROR")){
        Logger.getLogger(cl.getName()).log(Level.SEVERE, detail, ex);                              
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL ,summary, cl+detail));                
    }
    if(level.equals("ALERT")){
        Logger.getLogger(cl.getName()).log(Level.WARNING, detail, ex);                                  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,summary, cl+detail));
    }
    if(level.equals("INFO")){
        Logger.getLogger(cl.getName()).log(Level.INFO, detail, ex);                                          
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,summary, cl+detail));
    }  
    
    System.out.print("error "+summary);
    
                          
}

public static String truncate(String value, int length)
{
  if (value != null && value.length() > length)
    value = value.substring(0, length);
  return value;
}    

}