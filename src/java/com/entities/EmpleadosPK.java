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
public class EmpleadosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_EMP")
    private int codEmp;

    public EmpleadosPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}		
    }

    public EmpleadosPK(short codCia, int codEmp) {
	this.codCia = codCia;
	this.codEmp = codEmp;
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

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) codCia;
	hash += (int) codEmp;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof EmpleadosPK)) {
	    return false;
	}
	EmpleadosPK other = (EmpleadosPK) object;
	if (this.codCia != other.codCia) {
	    return false;
	}
	if (this.codEmp != other.codEmp) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.EmpleadosPK[ codCia=" + codCia + ", codEmp=" + codEmp + " ]";
    }
    
}
