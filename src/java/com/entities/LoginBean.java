/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mmixco
 */
@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
    @EJB
    private CiasFacade ciasFacade;
    
    private String username;
    private String password;
    public String usuario;
    public Cias codCia1;
    public String theme;

    public String getTheme() {
        theme = "home";
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    
    public Cias getCodCia1() {
	return codCia1;
    }

    public void setCodCia1(Cias codCia1) {
	this.codCia1 = codCia1;
    }

    @EJB
    private com.entities.EmpleadosFacade ejbFacadeEmpleados;
    @EJB
    private com.entities.CiasFacade ejbFacadeCia;
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
 
    @ManagedProperty(value="#{param.from}")
    private String from;
     public String getFrom() {
        return from;
    }
 
    public void setFrom(String from) {
        this.from = from;
    }
  public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
     public void login(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {             
	    
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
            request.login(username, password); 
            
	    Empleados emp = ejbFacadeEmpleados.findbyUsuario(username);	    
	    this.codCia1 = ejbFacadeCia.find(emp.getEmpleadosPK().getCodCia());
            session.setAttribute("SSUSUARIO", username);    
	    session.setAttribute("SSCIANOM", this.codCia1.getNomComercial() );                           
	    session.setAttribute("SSCIAVAL", this.codCia1.getCodCia() );   
	   String themeExpression = "home";
	String theme = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), themeExpression, String.class);
            if(from != null){
                FacesContext.getCurrentInstance().getExternalContext().redirect(from);                
            }
	    
        } catch (Exception e) {
	    
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An Error Occured: Login failed", null));
         

        }
    }
 
         public void logout() throws ServletException, IOException{
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        request.logout();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
	 
	 public  void changecia() throws IOException{	          
	    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
            
            
	    session.setAttribute("SSCIANOM", this.codCia1.getNomComercial() );                           
	    session.setAttribute("SSCIAVAL", this.codCia1.getCodCia() );   
	     FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	
	 }
         
          public String getLoggedUser(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request.getUserPrincipal() != null)
            return request.getUserPrincipal().getName();
        return "";
    }
	  
	  
	  public short sscia () {	      
	      short cia=1;	      	      
	      try
		{
		  HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		    if(session != null){		    
			cia  = (short) session.getAttribute("SSCIAVAL") ;
		    }
		}
	     catch(NullPointerException e){ 
		 
		return cia ;	
	     }
	      
	    return cia ;	
	    
	      
	  }
	  
	  
	  public String ssuser (){	      
	      String user ="";	      
	      try
		{
		  HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		    if(session != null){		    
			user  =  (String) session.getAttribute("SSUSUARIO") ;
		    }
		}
	     catch(NullPointerException e){ 
		return user ;	
	     }
	      
	    return user ;	
	    
	      
	  }
          
	  public int sspro (){	      
	      int sspro =0;	      
	      try
		{
		  HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
		    if(session != null){		    
			sspro  =  (int) session.getAttribute("SSPROCESO") ;
		    }
		}
	     catch(NullPointerException e){ 
		return sspro ;	
	     }
	      
	    return sspro ;	
	     
	  }          
	  
          
        public String ssnompla (){	      
                    String sspro ="";	      
                    try
                      {
                        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false); 
                          if(session != null){		    
                              sspro  =  (String) session.getAttribute("SSNOMPLA") ;
                          }
                      }
                   catch(NullPointerException e){ 
                      return "" ;	
                   }

                  return sspro ;	

                } 
  
	  public Date sdate(){
	     
	      Calendar currentDate = Calendar.getInstance();
	      return currentDate.getTime();
	  }	  
          
          
        
}
