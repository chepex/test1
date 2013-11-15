/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author mmixco
 */
@Stateless
public class Reportes {
    
    public String GenerarReporte(String rep, HashMap params) throws NamingException, SQLException, JRException, IOException {
        String reporPath = "";          
        reporPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(rep);              
        Context ctx= new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("jdbc/desa");
        Connection cn = ds.getConnection();            
        JasperPrint jasperprint = JasperFillManager.fillReport(reporPath, params, cn);        
        HttpServletResponse httpserveltresponse= (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpserveltresponse.setContentType("application/pdf");
        ServletOutputStream servletOutputStream = httpserveltresponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperprint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();  
      return "";
    } 
    
     
    }

   


