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
public class VplanillaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECUENCIA")
    private long secuencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_EMP")
    private int codEmp;

    public VplanillaPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}		
    }

    public VplanillaPK(short codCia, long secuencia, int codEmp) {
	this.codCia = codCia;
	this.secuencia = secuencia;
	this.codEmp = codEmp;
    }

    public short getCodCia() {
	return codCia;
    }

    public void setCodCia(short codCia) {
	this.codCia = codCia;
    }

    public long getSecuencia() {
	return secuencia;
    }

    public void setSecuencia(long secuencia) {
	this.secuencia = secuencia;
    }

    public int getCodEmp() {
	return codEmp;
    }

    public void setCodEmp(int codEmp) {
	this.codEmp = codEmp;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) codCia;
	hash += (int) secuencia;
	hash += (int) codEmp;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof VplanillaPK)) {
	    return false;
	}
	VplanillaPK other = (VplanillaPK) object;
	if (this.codCia != other.codCia) {
	    return false;
	}
	if (this.secuencia != other.secuencia) {
	    return false;
	}
	if (this.codEmp != other.codEmp) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.VplanillaPK[ codCia=" + codCia + ", secuencia=" + secuencia + ", codEmp=" + codEmp + " ]";
    }
    
}
