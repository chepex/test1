/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dromero
 */
@Entity
@Table(name = "PAISES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paises.findAll", query = "SELECT p FROM Paises p"),
    @NamedQuery(name = "Paises.findByCodPais", query = "SELECT p FROM Paises p WHERE p.codPais = :codPais"),
    @NamedQuery(name = "Paises.findByNombPais", query = "SELECT p FROM Paises p WHERE p.nombPais = :nombPais"),
    @NamedQuery(name = "Paises.findByMonedaSimbolo", query = "SELECT p FROM Paises p WHERE p.monedaSimbolo = :monedaSimbolo"),
    @NamedQuery(name = "Paises.findByMonedaDescrip", query = "SELECT p FROM Paises p WHERE p.monedaDescrip = :monedaDescrip"),
    @NamedQuery(name = "Paises.findByLocal", query = "SELECT p FROM Paises p WHERE p.local = :local"),
    @NamedQuery(name = "Paises.findByTasaCambio", query = "SELECT p FROM Paises p WHERE p.tasaCambio = :tasaCambio"),
    @NamedQuery(name = "Paises.findByNacionalidad", query = "SELECT p FROM Paises p WHERE p.nacionalidad = :nacionalidad")})
public class Paises implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PAIS")
    private Short codPais;
    @Size(max = 40)
    @Column(name = "NOMB_PAIS")
    private String nombPais;
    @Size(max = 1)
    @Column(name = "MONEDA_SIMBOLO")
    private String monedaSimbolo;
    @Size(max = 30)
    @Column(name = "MONEDA_DESCRIP")
    private String monedaDescrip;
    @Size(max = 1)
    @Column(name = "LOCAL")
    private String local;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TASA_CAMBIO")
    private BigDecimal tasaCambio;
    @Size(max = 75)
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paises")
    private List<Deptos> deptosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paises")
    private List<Zonas> zonasList;

    public Paises() {
    }

    public Paises(Short codPais) {
        this.codPais = codPais;
    }

    public Short getCodPais() {
        return codPais;
    }

    public void setCodPais(Short codPais) {
        this.codPais = codPais;
    }

    public String getNombPais() {
        return nombPais;
    }

    public void setNombPais(String nombPais) {
        this.nombPais = nombPais;
    }

    public String getMonedaSimbolo() {
        return monedaSimbolo;
    }

    public void setMonedaSimbolo(String monedaSimbolo) {
        this.monedaSimbolo = monedaSimbolo;
    }

    public String getMonedaDescrip() {
        return monedaDescrip;
    }

    public void setMonedaDescrip(String monedaDescrip) {
        this.monedaDescrip = monedaDescrip;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public BigDecimal getTasaCambio() {
        return tasaCambio;
    }

    public void setTasaCambio(BigDecimal tasaCambio) {
        this.tasaCambio = tasaCambio;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @XmlTransient
    public List<Deptos> getDeptosList() {
        return deptosList;
    }

    public void setDeptosList(List<Deptos> deptosList) {
        this.deptosList = deptosList;
    }

    @XmlTransient
    public List<Zonas> getZonasList() {
        return zonasList;
    }

    public void setZonasList(List<Zonas> zonasList) {
        this.zonasList = zonasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPais != null ? codPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paises)) {
            return false;
        }
        Paises other = (Paises) object;
        if ((this.codPais == null && other.codPais != null) || (this.codPais != null && !this.codPais.equals(other.codPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Paises[ codPais=" + codPais + " ]";
    }
    
}
