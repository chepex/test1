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
public class PuestosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PUESTO")
    private short codPuesto;

    public PuestosPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}		
    }

    public PuestosPK(short codCia, short codPuesto) {
	this.codCia = codCia;
	this.codPuesto = codPuesto;
    }

    public short getCodCia() {
	return codCia;
    }

    public void setCodCia(short codCia) {
	this.codCia = codCia;
    }

    public short getCodPuesto() {
	return codPuesto;
    }

    public void setCodPuesto(short codPuesto) {
	this.codPuesto = codPuesto;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) codCia;
	hash += (int) codPuesto;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof PuestosPK)) {
	    return false;
	}
	PuestosPK other = (PuestosPK) object;
	if (this.codCia != other.codCia) {
	    return false;
	}
	if (this.codPuesto != other.codPuesto) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.PuestosPK[ codCia=" + codCia + ", codPuesto=" + codPuesto + " ]";
    }
    
}
