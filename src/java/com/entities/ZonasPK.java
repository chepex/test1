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
public class ZonasPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PAIS")
    private short codPais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ZONA")
    private int codZona;

    public ZonasPK() {
    }

    public ZonasPK(short codPais, int codZona) {
        this.codPais = codPais;
        this.codZona = codZona;
    }

    public short getCodPais() {
        return codPais;
    }

    public void setCodPais(short codPais) {
        this.codPais = codPais;
    }

    public int getCodZona() {
        return codZona;
    }

    public void setCodZona(int codZona) {
        this.codZona = codZona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPais;
        hash += (int) codZona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZonasPK)) {
            return false;
        }
        ZonasPK other = (ZonasPK) object;
        if (this.codPais != other.codPais) {
            return false;
        }
        if (this.codZona != other.codZona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.ZonasPK[ codPais=" + codPais + ", codZona=" + codZona + " ]";
    }
    
}
