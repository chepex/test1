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
public class TiposPlanillaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_TIPOPLA")
    private short codTipopla;

    public TiposPlanillaPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}		
    }

    public TiposPlanillaPK(short codCia, short codTipopla) {
	this.codCia = codCia;
	this.codTipopla = codTipopla;
    }

    public short getCodCia() {
	return codCia;
    }

    public void setCodCia(short codCia) {
	this.codCia = codCia;
    }

    public short getCodTipopla() {
	return codTipopla;
    }

    public void setCodTipopla(short codTipopla) {
	this.codTipopla = codTipopla;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) codCia;
	hash += (int) codTipopla;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof TiposPlanillaPK)) {
	    return false;
	}
	TiposPlanillaPK other = (TiposPlanillaPK) object;
	if (this.codCia != other.codCia) {
	    return false;
	}
	if (this.codTipopla != other.codTipopla) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.TiposPlanillaPK[ codCia=" + codCia + ", codTipopla=" + codTipopla + " ]";
    }
    
}
