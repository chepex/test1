/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.entities.Prestamos;
import com.entities.PrestamosFacade;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_Prestamos {
    @EJB
    private PrestamosFacade prestamosFacade;
    

    public Prestamos CalcularMonto(Prestamos prestamo) {
	BigDecimal cout = new BigDecimal(prestamo.getCuotas() );  
	BigDecimal monto=   prestamo.getVcuota().multiply(cout);
	prestamo.setMonto(monto);
	prestamo.setSaldo( monto);
	prestamo.setCuotasP((short) 0);
	return prestamo;
    }

    
    public void RestaSaldo(Prestamos prestamo) {
	if (ValidarSaldo(prestamo)==true){	    
	    BigDecimal saldo= prestamo.getSaldo().subtract( prestamo.getVcuota());		    
	    prestamo.setSaldo(saldo);
	    prestamosFacade.edit(prestamo);
	}	
    }
    
    
    public void RestaCuota(Prestamos prestamo) {
	if (ValidarSaldo(prestamo)==true){
	    short coutas= (short) (prestamo.getCuotas()-1);	    
	    prestamo.setCuotasP(coutas);	    
	    prestamosFacade.edit(prestamo);
	}	
    }    
    
    
    public boolean ValidarSaldo(Prestamos prestamo){
	boolean val=false;
	if(prestamo.getSaldo().compareTo(BigDecimal.ZERO) > 0){	
	    val= true;
	}	
	return val;
    }
    
    public void CancelarPrestamo(Prestamos prestamo){
	    if (ValidarSaldo(prestamo)==true){
		prestamo.setCuotasP(prestamo.getCuotas() );
		prestamo.setSaldo(BigDecimal.ZERO);	    
		prestamosFacade.edit(prestamo);
	    }	

    }    
    
   
    

    

}
