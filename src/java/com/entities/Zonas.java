/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dromero
 */
@Entity
@Table(name = "ZONAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zonas.findAll", query = "SELECT z FROM Zonas z"),
    @NamedQuery(name = "Zonas.findByCodPais", query = "SELECT z FROM Zonas z WHERE z.zonasPK.codPais = :codPais"),
    @NamedQuery(name = "Zonas.findByCodZona", query = "SELECT z FROM Zonas z WHERE z.zonasPK.codZona = :codZona"),
    @NamedQuery(name = "Zonas.findByDescripcion", query = "SELECT z FROM Zonas z WHERE z.descripcion = :descripcion")})
public class Zonas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ZonasPK zonasPK;
    @Size(max = 30)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paises paises;

    public Zonas() {
    }

    public Zonas(ZonasPK zonasPK) {
        this.zonasPK = zonasPK;
    }

    public Zonas(short codPais, int codZona) {
        this.zonasPK = new ZonasPK(codPais, codZona);
    }

    public ZonasPK getZonasPK() {
        return zonasPK;
    }

    public void setZonasPK(ZonasPK zonasPK) {
        this.zonasPK = zonasPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Paises getPaises() {
        return paises;
    }

    public void setPaises(Paises paises) {
        this.paises = paises;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zonasPK != null ? zonasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zonas)) {
            return false;
        }
        Zonas other = (Zonas) object;
        if ((this.zonasPK == null && other.zonasPK != null) || (this.zonasPK != null && !this.zonasPK.equals(other.zonasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Zonas[ zonasPK=" + zonasPK + " ]";
    }
    
}
