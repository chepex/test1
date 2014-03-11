/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dromero
 */
@Entity
@Table(name = "DEPTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deptos.findAll", query = "SELECT d FROM Deptos d"),
    @NamedQuery(name = "Deptos.findByCodPais", query = "SELECT d FROM Deptos d WHERE d.deptosPK.codPais = :codPais"),
    @NamedQuery(name = "Deptos.findByCodDepto", query = "SELECT d FROM Deptos d WHERE d.deptosPK.codDepto = :codDepto"),
    @NamedQuery(name = "Deptos.findByNomDepto", query = "SELECT d FROM Deptos d WHERE d.nomDepto = :nomDepto"),
    @NamedQuery(name = "Deptos.findByZona", query = "SELECT d FROM Deptos d WHERE d.deptosPK.zona = :zona")})
public class Deptos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DeptosPK deptosPK;
    @Size(max = 30)
    @Column(name = "NOM_DEPTO")
    private String nomDepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deptos")
    private List<Municipios> municipiosList;
    @JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paises paises;
    @JoinColumns({
    @JoinColumn(name = "COD_PAIS", referencedColumnName = "COD_PAIS", insertable = false, updatable = false),
    @JoinColumn(name = "ZONA", referencedColumnName = "COD_ZONA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Zonas zonas;

    public Deptos() {
    }

    public Deptos(DeptosPK deptosPK) {
        this.deptosPK = deptosPK;
    }

    public Deptos(short codPais, short codDepto, int zona) {
        this.deptosPK = new DeptosPK(codPais, codDepto, zona);
    }

    public DeptosPK getDeptosPK() {
        return deptosPK;
    }

    public void setDeptosPK(DeptosPK deptosPK) {
        this.deptosPK = deptosPK;
    }

    public String getNomDepto() {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }

    @XmlTransient
    public List<Municipios> getMunicipiosList() {
        return municipiosList;
    }

    public void setMunicipiosList(List<Municipios> municipiosList) {
        this.municipiosList = municipiosList;
    }

    public Paises getPaises() {
        return paises;
    }

    public void setPaises(Paises paises) {
        this.paises = paises;
    }

    public Zonas getZonas() {
        return zonas;
    }

    public void setZonas(Zonas zonas) {
        this.zonas = zonas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptosPK != null ? deptosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deptos)) {
            return false;
        }
        Deptos other = (Deptos) object;
        if ((this.deptosPK == null && other.deptosPK != null) || (this.deptosPK != null && !this.deptosPK.equals(other.deptosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Deptos[ deptosPK=" + deptosPK + " ]";
    }
    
}
