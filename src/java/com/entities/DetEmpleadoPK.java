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
public class DetEmpleadoPK implements Serializable {
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

    public DetEmpleadoPK() {
        	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}
    }

    public DetEmpleadoPK(short codCia, int codEmp, short codDp) {
        this.codCia = codCia;
        this.codEmp = codEmp;
        this.codDp = codDp;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codCia;
        hash += (int) codEmp;
        hash += (int) codDp;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetEmpleadoPK)) {
            return false;
        }
        DetEmpleadoPK other = (DetEmpleadoPK) object;
        if (this.codCia != other.codCia) {
            return false;
        }
        if (this.codEmp != other.codEmp) {
            return false;
        }
        if (this.codDp != other.codDp) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.DetEmpleadoPK[ codCia=" + codCia + ", codEmp=" + codEmp + ", codDp=" + codDp + " ]";
    }
    
}
