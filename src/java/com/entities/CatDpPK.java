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
public class CatDpPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CAT")
    private short codCat;

    public CatDpPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}        
    }

    public CatDpPK(short codCia, short codCat) {
	this.codCia = codCia;
	this.codCat = codCat;
    }

    public short getCodCia() {
	return codCia;
    }

    public void setCodCia(short codCia) {
	this.codCia = codCia;
    }

    public short getCodCat() {
	return codCat;
    }

    public void setCodCat(short codCat) {
	this.codCat = codCat;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) codCia;
	hash += (int) codCat;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof CatDpPK)) {
	    return false;
	}
	CatDpPK other = (CatDpPK) object;
	if (this.codCia != other.codCia) {
	    return false;
	}
	if (this.codCat != other.codCat) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.CatDpPK[ codCia=" + codCia + ", codCat=" + codCat + " ]";
    }
    
}
