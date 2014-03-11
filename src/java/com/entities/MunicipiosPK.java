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
public class MunicipiosPK implements Serializable {
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
    @Column(name = "COD_MUNI")
    private short codMuni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ZONA")
    private int codZona;

    public MunicipiosPK() {
    }

    public MunicipiosPK(short codPais, short codDepto, short codMuni, int codZona) {
        this.codPais = codPais;
        this.codDepto = codDepto;
        this.codMuni = codMuni;
        this.codZona = codZona;
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

    public short getCodMuni() {
        return codMuni;
    }

    public void setCodMuni(short codMuni) {
        this.codMuni = codMuni;
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
        hash += (int) codDepto;
        hash += (int) codMuni;
        hash += (int) codZona;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MunicipiosPK)) {
            return false;
        }
        MunicipiosPK other = (MunicipiosPK) object;
        if (this.codPais != other.codPais) {
            return false;
        }
        if (this.codDepto != other.codDepto) {
            return false;
        }
        if (this.codMuni != other.codMuni) {
            return false;
        }
        if (this.codZona != other.codZona) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.MunicipiosPK[ codPais=" + codPais + ", codDepto=" + codDepto + ", codMuni=" + codMuni + ", codZona=" + codZona + " ]";
    }
    
}
