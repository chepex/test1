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
public class DeducPrestaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DP")
    private short codDp;

    public DeducPrestaPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}	
    }

    public DeducPrestaPK(short codCia, short codDp) {
	this.codCia = codCia;
	this.codDp = codDp;
    }

    public short getCodCia() {
	return codCia;
    }

    public void setCodCia(short codCia) {
	this.codCia = codCia;
    }

    public short getCodDp() {
	return codDp;
    }

    public void setCodDp(short codDp) {
	this.codDp = codDp;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) codCia;
	hash += (int) codDp;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof DeducPrestaPK)) {
	    return false;
	}
	DeducPrestaPK other = (DeducPrestaPK) object;
	if (this.codCia != other.codCia) {
	    return false;
	}
	if (this.codDp != other.codDp) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.DeducPrestaPK[ codCia=" + codCia + ", codDp=" + codDp + " ]";
    }
    
}
