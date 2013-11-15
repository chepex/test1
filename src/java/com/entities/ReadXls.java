/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 package com.entities;

import com.ejb.SB_Planilla_horas;
import javax.faces.bean.ManagedBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.primefaces.event.FileUploadEvent;
/**
 *
 * @author mmixco
 */
@ManagedBean(name = "readXls")
@ViewScoped
public class ReadXls {
    @EJB
    private SB_Planilla_horas sB_Planilla_horas;

    /**
     * Creates a new instance of ReadXls
     */
    public ReadXls() {
    }

private String destination="/opt/lib/";
public String inputFile;

    public String getInputFile() {
        return inputFile;
    }

  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }

  public void read() throws IOException  {
    File inputWorkbook = new File(this.getInputFile());
    Workbook w;
    try {
      w = Workbook.getWorkbook(inputWorkbook);
      
      Sheet sheet = w.getSheet(0);
      
        sB_Planilla_horas.uploadXls(sheet);
    } catch (BiffException e) {
      e.printStackTrace();
    }
  }

 /* public static void main(String[] args) throws IOException {
    ReadXls test = new ReadXls();
    test.setInputFile("/opt/lib/mas1.xls");
    test.read();
  }*/
public void handleFileUpload(FileUploadEvent event) throws IOException {  
    this.setInputFile(event.getFile().getInputstream().toString());
    read();
        FacesMessage msg = new FacesMessage("bien", event.getFile().getFileName() + " is subida.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }      


    public void upload(FileUploadEvent event) {  
        System.out.print("aaaaa");
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }  

    public void copyFile(String fileName, InputStream in) {
           try {
             
             
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination + fileName));
             
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
             
                in.close();
                out.flush();
                out.close();
             
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
}
