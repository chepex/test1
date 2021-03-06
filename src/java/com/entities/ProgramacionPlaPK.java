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
public class ProgramacionPlaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECUENCIA")
    private long secuencia;
	
    public ProgramacionPlaPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();	    
	}	
    }

    public ProgramacionPlaPK(short codCia, long secuencia) {
	this.codCia = codCia;
	this.secuencia = secuencia;
    }

    public short getCodCia() {
	return codCia;
    }

    public void setCodCia(short codCia) {
	this.codCia = codCia;
    }

    public long getSecuencia() {
	//secuencia = (long)  secuencia + 1;
	return secuencia;
    }

    public void setSecuencia(long secuencia) {
	this.secuencia = secuencia;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) codCia;
	hash += (int) secuencia;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof ProgramacionPlaPK)) {
	    return false;
	}
	ProgramacionPlaPK other = (ProgramacionPlaPK) object;
	if (this.codCia != other.codCia) {
	    return false;
	}
	if (this.secuencia != other.secuencia) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.ProgramacionPlaPK[ codCia=" + codCia + ", secuencia=" + secuencia + " ]";
    }
    
}
