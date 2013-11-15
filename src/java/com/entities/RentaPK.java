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
public class RentaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private short id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SECUENCIA")
    private short secuencia;

    public RentaPK() {
    }

    public RentaPK(short codCia, short id, short secuencia) {
        this.codCia = codCia;
        this.id = id;
        this.secuencia = secuencia;
    }

    public short getCodCia() {
        return codCia;
    }

    public void setCodCia(short codCia) {
        this.codCia = codCia;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(short secuencia) {
        this.secuencia = secuencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codCia;
        hash += (int) id;
        hash += (int) secuencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RentaPK)) {
            return false;
        }
        RentaPK other = (RentaPK) object;
        if (this.codCia != other.codCia) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.secuencia != other.secuencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.RentaPK[ codCia=" + codCia + ", id=" + id + ", secuencia=" + secuencia + " ]";
    }
    
}
