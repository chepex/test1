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
public class HorariosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_HORARIO")
    private short codHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_DEPTO")
    private short codDepto;

    public HorariosPK() {
    }

    public HorariosPK(short codCia, short codHorario, short codDepto) {
        this.codCia = codCia;
        this.codHorario = codHorario;
        this.codDepto = codDepto;
    }

    public short getCodCia() {
        return codCia;
    }

    public void setCodCia(short codCia) {
        this.codCia = codCia;
    }

    public short getCodHorario() {
        return codHorario;
    }

    public void setCodHorario(short codHorario) {
        this.codHorario = codHorario;
    }

    public short getCodDepto() {
        return codDepto;
    }

    public void setCodDepto(short codDepto) {
        this.codDepto = codDepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codCia;
        hash += (int) codHorario;
        hash += (int) codDepto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorariosPK)) {
            return false;
        }
        HorariosPK other = (HorariosPK) object;
        if (this.codCia != other.codCia) {
            return false;
        }
        if (this.codHorario != other.codHorario) {
            return false;
        }
        if (this.codDepto != other.codDepto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.HorariosPK[ codCia=" + codCia + ", codHorario=" + codHorario + ", codDepto=" + codDepto + " ]";
    }
    
}
