/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Mensaje;
import com.entities.ProgramacionPla;
import javax.ejb.Stateless;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_ProgramacionPla {
Mensaje msg = new Mensaje();

    public Mensaje validarEstado(ProgramacionPla programacionPla) {
	
	msg.setTitulo("ok");
	if (programacionPla.getStatus().equals("C")){
	    msg.setMensajes("Una planilla cerrada no puede modificarse");
	    msg.setTitulo("error");	    
	}	
	return msg;
    }  

}
