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
 * @author dromero
 */
@Embeddable
public class DeptosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PAIS")
    private short codPais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DEPTO")
    private short codDepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZONA")
    private int zona;

    public DeptosPK() {
    }

    public DeptosPK(short codPais, short codDepto, int zona) {
        this.codPais = codPais;
        this.codDepto = codDepto;
        this.zona = zona;
    }

    public short getCodPais() {
        return codPais;
    }

    public void setCodPais(short codPais) {
        this.codPais = codPais;
    }

    public short getCodDepto() {
        return codDepto;
    }

    public void setCodDepto(short codDepto) {
        this.codDepto = codDepto;
    }

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPais;
        hash += (int) codDepto;
        hash += (int) zona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeptosPK)) {
            return false;
        }
        DeptosPK other = (DeptosPK) object;
        if (this.codPais != other.codPais) {
            return false;
        }
        if (this.codDepto != other.codDepto) {
            return false;
        }
        if (this.zona != other.zona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.DeptosPK[ codPais=" + codPais + ", codDepto=" + codDepto + ", zona=" + zona + " ]";
    }
    
}
