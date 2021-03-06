package com.entities;

import com.ejb.SB_Planilla;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name = "planillaIsssController")
@ViewScoped
public class PlanillaIsssController extends AbstractController<PlanillaIsss> implements Serializable {
    @EJB
    private PlanillaFacade planillaFacade;
    @EJB
    private SB_Planilla sB_Planilla;

    @EJB
    private PlanillaIsssFacade ejbFacade;

    
    public PlanillaIsssController() {
        super(PlanillaIsss.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getPlanillaIsssPK().setCodEmp(this.getSelected().getEmpleados().getEmpleadosPK().getCodEmp());
        this.getSelected().getPlanillaIsssPK().setCodCia(this.getSelected().getEmpleados().getEmpleadosPK().getCodCia());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setPlanillaIsssPK(new com.entities.PlanillaIsssPK());
    }
    
     public String Gcvs()  {                
        try{
            short Anio = this.getSelected().getPlanillaIsssPK().getAnio();
            short Mes = this.getSelected().getPlanillaIsssPK().getMes();
            short corelativo = this.getSelected().getCorrelativo();
          FacesContext fc = FacesContext.getCurrentInstance();
          HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
          response.setContentType("text/csv"); 
          fc.responseComplete();         
          response.setHeader ( "Content-disposition", "attachment; filename=\"Reporting-" + 
          new Date().getTime() + ".csv\"" );
          ServletOutputStream output = response.getOutputStream();      
          String encabezado = sB_Planilla.generarTxtISSS(Anio, Mes, corelativo);
          InputStream is = new ByteArrayInputStream( encabezado.getBytes("UTF-8") );
          int nextChar;
           while ((nextChar = is.read()) != -1) 
           {
              output.write(nextChar);
           }
           output.flush();
           output.close();
          } catch ( IOException  e )
            {           
                e.printStackTrace();
            }
        return "";           
    }     
     
     public String procesar(){
        List<Planilla> lpla  =   planillaFacade.findByAnioMes( (short)2014,(short)3);
        for( Planilla pla : lpla ){ 
             sB_Planilla.CalculoIsss(pla);            
        }
        
     return "";
     }
}
