/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Auditoria;
import com.entities.AuditoriaFacade;
import com.entities.LoginBean;
import com.entities.SequenceTableFacade;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_auditoria {
    @EJB
    private SequenceTableFacade sequenceTableFacade;
    @EJB
    private AuditoriaFacade auditoriaFacade;


public  String registrar_audit( String accion,String objecto, String Clase){
   
        LoginBean lb = new LoginBean();       
        Auditoria af = new Auditoria();
        af.setId( new BigDecimal(sequenceTableFacade.Sequence("Auditoria")));
        af.setFecha(lb.sdate());
        af.setObjecto(objecto);
        af.setUsuario(lb.ssuser());
        af.setAccion( accion);
        auditoriaFacade.edit(af);
        return "ok";
    }
}
