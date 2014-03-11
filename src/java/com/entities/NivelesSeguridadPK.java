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
public class NivelesSeguridadPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_SEGURIDAD")
    private short codSeguridad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;

    public NivelesSeguridadPK() {
    }

    public NivelesSeguridadPK(short codSeguridad, short codCia) {
        this.codSeguridad = codSeguridad;
        this.codCia = codCia;
    }

    public short getCodSeguridad() {
        return codSeguridad;
    }

    public void setCodSeguridad(short codSeguridad) {
        this.codSeguridad = codSeguridad;
    }

    public short getCodCia() {
        return codCia;
    }

    public void setCodCia(short codCia) {
        this.codCia = codCia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codSeguridad;
        hash += (int) codCia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelesSeguridadPK)) {
            return false;
        }
        NivelesSeguridadPK other = (NivelesSeguridadPK) object;
        if (this.codSeguridad != other.codSeguridad) {
            return false;
        }
        if (this.codCia != other.codCia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.NivelesSeguridadPK[ codSeguridad=" + codSeguridad + ", codCia=" + codCia + " ]";
    }
    
}
