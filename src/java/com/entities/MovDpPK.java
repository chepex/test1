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
public class MovDpPK implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DP")
    private short codDp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PRESTA")
    private int codPresta;

     public MovDpPK() {
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}		
    }

    public MovDpPK(short codCia, long secuencia, int codEmp, short codDp, int codPresta) {
        this.codCia = codCia;
        this.secuencia = secuencia;
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
        hash += (int) secuencia;
        hash += (int) codEmp;
        hash += (int) codDp;
        hash += (int) codPresta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovDpPK)) {
            return false;
        }
        MovDpPK other = (MovDpPK) object;
        if (this.codCia != other.codCia) {
            return false;
        }
        if (this.secuencia != other.secuencia) {
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
        return "com.entities.MovDpPK[ codCia=" + codCia + ", secuencia=" + secuencia + ", codEmp=" + codEmp + ", codDp=" + codDp + ", codPresta=" + codPresta + " ]";
    }
    
}
