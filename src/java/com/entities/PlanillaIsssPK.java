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
public class PlanillaIsssPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO")
    private short anio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MES")
    private short mes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_EMP")
    private int codEmp;

    public PlanillaIsssPK() {
    
	if( this.codCia == 0 ){
	    LoginBean lb= new LoginBean();	
	    this.codCia = lb.sscia();
	}		
    
    }

    public PlanillaIsssPK(short codCia, short anio, short mes, int codEmp) {
        this.codCia = codCia;
        this.anio = anio;
        this.mes = mes;
        this.codEmp = codEmp;
    }

    public short getCodCia() {
        return codCia;
    }

    public void setCodCia(short codCia) {
        this.codCia = codCia;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public short getMes() {
        return mes;
    }

    public void setMes(short mes) {
        this.mes = mes;
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
        hash += (int) anio;
        hash += (int) mes;
        hash += (int) codEmp;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanillaIsssPK)) {
            return false;
        }
        PlanillaIsssPK other = (PlanillaIsssPK) object;
        if (this.codCia != other.codCia) {
            return false;
        }
        if (this.anio != other.anio) {
            return false;
        }
        if (this.mes != other.mes) {
            return false;
        }
        if (this.codEmp != other.codEmp) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.PlanillaIsssPK[ codCia=" + codCia + ", anio=" + anio + ", mes=" + mes + ", codEmp=" + codEmp + " ]";
    }
    
}
