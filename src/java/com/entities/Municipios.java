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
import javax.persistence.JoinColumns;
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
@Table(name = "MUNICIPIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipios.findAll", query = "SELECT m FROM Municipios m"),
    @NamedQuery(name = "Municipios.findByCodPais", query = "SELECT m FROM Municipios m WHERE m.municipiosPK.codPais = :codPais"),
    @NamedQuery(name = "Municipios.findByCodDepto", query = "SELECT m FROM Municipios m WHERE m.municipiosPK.codDepto = :codDepto"),
    @NamedQuery(name = "Municipios.findByCodMuni", query = "SELECT m FROM Municipios m WHERE m.municipiosPK.codMuni = :codMuni"),
    @NamedQuery(name = "Municipios.findByNomMuni", query = "SELECT m FROM Municipios m WHERE m.nomMuni = :nomMuni"),
    @NamedQuery(name = "Municipios.findByCodZona", query = "SELECT m FROM Municipios m WHERE m.municipiosPK.codZona = :codZona"),
    @NamedQuery(name = "Municipios.findByPrioridadRuta", query = "SELECT m FROM Municipios m WHERE m.prioridadRuta = :prioridadRuta"),
    @NamedQuery(name = "Municipios.findByCodRutades", query = "SELECT m FROM Municipios m WHERE m.codRutades = :codRutades")})
public class Municipios implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MunicipiosPK municipiosPK;
    @Size(max = 30)
    @Column(name = "NOM_MUNI")
    private String nomMuni;
    @Column(name = "PRIORIDAD_RUTA")
    private Short prioridadRuta;
    @Column(name = "COD_RUTADES")
    private Short codRutades;
    @JoinColumns({
        @JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS", insertable = false, updatable = false),
        @JoinColumn(name = "COD_DEPTO", referencedColumnName = "COD_DEPTO", insertable = false, updatable = false),
        @JoinColumn(name = "COD_ZONA", referencedColumnName = "ZONA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Deptos deptos;

    public Municipios() {
    }

    public Municipios(MunicipiosPK municipiosPK) {
        this.municipiosPK = municipiosPK;
    }

    public Municipios(short codPais, short codDepto, short codMuni, int codZona) {
        this.municipiosPK = new MunicipiosPK(codPais, codDepto, codMuni, codZona);
    }

    public MunicipiosPK getMunicipiosPK() {
        return municipiosPK;
    }

    public void setMunicipiosPK(MunicipiosPK municipiosPK) {
        this.municipiosPK = municipiosPK;
    }

    public String getNomMuni() {
        return nomMuni;
    }

    public void setNomMuni(String nomMuni) {
        this.nomMuni = nomMuni;
    }

    public Short getPrioridadRuta() {
        return prioridadRuta;
    }

    public void setPrioridadRuta(Short prioridadRuta) {
        this.prioridadRuta = prioridadRuta;
    }

    public Short getCodRutades() {
        return codRutades;
    }

    public void setCodRutades(Short codRutades) {
        this.codRutades = codRutades;
    }

    public Deptos getDeptos() {
        return deptos;
    }

    public void setDeptos(Deptos deptos) {
        this.deptos = deptos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (municipiosPK != null ? municipiosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipios)) {
            return false;
        }
        Municipios other = (Municipios) object;
        if ((this.municipiosPK == null && other.municipiosPK != null) || (this.municipiosPK != null && !this.municipiosPK.equals(other.municipiosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Municipios[ municipiosPK=" + municipiosPK + " ]";
    }
    
}
