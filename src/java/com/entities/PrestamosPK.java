/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


/**
 *
 * @author mmixco
 */
@Embeddable
public class PrestamosPK implements Serializable {
    private static final long serialVersionUID = 1L;    
    @Column(name = "COD_PRESTA")    
    @Basic(optional = false)
    @NotNull
    private int codPresta;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_EMP")
    private int codEmp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DP")
    private short codDp;
    
    
    

    public PrestamosPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}		
    }

    public PrestamosPK(short codCia, int codEmp, short codDp, int codPresta) {
	this.codCia = codCia;
	this.codEmp = codEmp;
	this.codDp = codDp;
	this.codPresta = codPresta;
    }

    public short getCodCia() {
	return codCia;
    }

    public void setCodCia(short codCia) {
	this.codCia = codCia;
    }

    public int getCodEmp() {
	return codEmp;
    }

    public void setCodEmp(int codEmp) {
	this.codEmp = codEmp;
    }

    public short getCodDp() {
	return codDp;
    }

    public void setCodDp(short codDp) {
	this.codDp = codDp;
    }

    public int getCodPresta() {
	return codPresta;
    }

    public void setCodPresta(int codPresta) {
	this.codPresta = codPresta;
    }





    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) codCia;
	hash += (int) codEmp;
	hash += (int) codDp;
	hash += (int) codPresta;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof PrestamosPK)) {
	    return false;
	}
	PrestamosPK other = (PrestamosPK) object;
	if (this.codCia != other.codCia) {
	    return false;
	}
	if (this.codEmp != other.codEmp) {
	    return false;
	}
	if (this.codDp != other.codDp) {
	    return false;
	}
	if (this.codPresta != other.codPresta) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.PrestamosPK[ codCia=" + codCia + ", codEmp=" + codEmp + ", codDp=" + codDp + ", codPresta=" + codPresta + " ]";
    }
    
}
