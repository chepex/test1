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
public class DepartamentosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DEPTO")
    private short codDepto;

    public DepartamentosPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}		
    }

    public DepartamentosPK(short codCia, short codDepto) {
	this.codCia = codCia;
	this.codDepto = codDepto;
    }

    public short getCodCia() {
	return codCia;
    }

    public void setCodCia(short codCia) {
	this.codCia = codCia;
    }

    public short getCodDepto() {
	return codDepto;
    }

    public void setCodDepto(short codDepto) {
	this.codDepto = codDepto;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) codCia;
	hash += (int) codDepto;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof DepartamentosPK)) {
	    return false;
	}
	DepartamentosPK other = (DepartamentosPK) object;
	if (this.codCia != other.codCia) {
	    return false;
	}
	if (this.codDepto != other.codDepto) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.DepartamentosPK[ codCia=" + codCia + ", codDepto=" + codDepto + " ]";
    }
    
}
