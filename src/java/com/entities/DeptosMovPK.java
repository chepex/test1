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
public class DeptosMovPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DEPTO")
    private short codDepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CAT")
    private short codCat;

    public DeptosMovPK() {
    }

    public DeptosMovPK(short codCia, short codDepto, short codCat) {
        this.codCia = codCia;
        this.codDepto = codDepto;
        this.codCat = codCat;
    }

    public short getCodCia() {
        return codCia;
    }

    public void setCodCia(short codCia) {
        this.codCia = codCia;
    }

    public short getCodDepto() {
        return codDepto;
    }

    public void setCodDepto(short codDepto) {
        this.codDepto = codDepto;
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
        hash += (int) codDepto;
        hash += (int) codCat;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeptosMovPK)) {
            return false;
        }
        DeptosMovPK other = (DeptosMovPK) object;
        if (this.codCia != other.codCia) {
            return false;
        }
        if (this.codDepto != other.codDepto) {
            return false;
        }
        if (this.codCat != other.codCat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.DeptosMovPK[ codCia=" + codCia + ", codDepto=" + codDepto + ", codCat=" + codCat + " ]";
    }
    
}
