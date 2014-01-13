/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Mensaje;
import com.entities.ProgramacionPla;
import com.entities.util.JsfUtil;
import javax.ejb.Stateless;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_ProgramacionPla {
Mensaje msg = new Mensaje();

    public String validarEstado(ProgramacionPla programacionPla) {
	if (programacionPla.getStatus().equals("C")){
            JsfUtil.logs(new Exception() , "Surgio un error", "Planilla Cerrada",SB_Asistencia.class,"ALERT");                                                                                 
	    return "error";             
	}	
	return "ok";
    }  
}
