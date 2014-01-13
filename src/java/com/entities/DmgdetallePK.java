/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mmixco
 */
@Embeddable
public class DmgdetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private short codCia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "TIPO_DOCTO")
    private String tipoDocto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_POLIZA")
    private int numPoliza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CORRELAT")
    private int correlat;

    public DmgdetallePK() {
    }

    public DmgdetallePK(short codCia, String tipoDocto, int numPoliza, Date fecha, int correlat) {
        this.codCia = codCia;
        this.tipoDocto = tipoDocto;
        this.numPoliza = numPoliza;
        this.fecha = fecha;
        this.correlat = correlat;
    }

    public short getCodCia() {
        return codCia;
    }

    public void setCodCia(short codCia) {
        this.codCia = codCia;
    }

    public String getTipoDocto() {
        return tipoDocto;
    }

    public void setTipoDocto(String tipoDocto) {
        this.tipoDocto = tipoDocto;
    }

    public int getNumPoliza() {
        return numPoliza;
    }

    public void setNumPoliza(int numPoliza) {
        this.numPoliza = numPoliza;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCorrelat() {
        return correlat;
    }

    public void setCorrelat(int correlat) {
        this.correlat = correlat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codCia;
        hash += (tipoDocto != null ? tipoDocto.hashCode() : 0);
        hash += (int) numPoliza;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) correlat;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmgdetallePK)) {
            return false;
        }
        DmgdetallePK other = (DmgdetallePK) object;
        if (this.codCia != other.codCia) {
            return false;
        }
        if ((this.tipoDocto == null && other.tipoDocto != null) || (this.tipoDocto != null && !this.tipoDocto.equals(other.tipoDocto))) {
            return false;
        }
        if (this.numPoliza != other.numPoliza) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if (this.correlat != other.correlat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.DmgdetallePK[ codCia=" + codCia + ", tipoDocto=" + tipoDocto + ", numPoliza=" + numPoliza + ", fecha=" + fecha + ", correlat=" + correlat + " ]";
    }
    
}
