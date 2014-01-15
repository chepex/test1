/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "PUESTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puestos.findAll", query = "SELECT p FROM Puestos p"),
    @NamedQuery(name = "Puestos.findByCodCia", query = "SELECT p FROM Puestos p WHERE p.puestosPK.codCia = :codCia"),
    @NamedQuery(name = "Puestos.findByCodPuesto", query = "SELECT p FROM Puestos p WHERE p.puestosPK.codPuesto = :codPuesto"),
    @NamedQuery(name = "Puestos.findByNomPuesto", query = "SELECT p FROM Puestos p WHERE p.nomPuesto = :nomPuesto"),
    @NamedQuery(name = "Puestos.findBySalMaximo", query = "SELECT p FROM Puestos p WHERE p.salMaximo = :salMaximo"),
    @NamedQuery(name = "Puestos.findBySalMinimo", query = "SELECT p FROM Puestos p WHERE p.salMinimo = :salMinimo"),
    @NamedQuery(name = "Puestos.findByRol", query = "SELECT p FROM Puestos p WHERE p.rol = :rol")})
public class Puestos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PuestosPK puestosPK;
    @Size(max = 60)
    @Column(name = "NOM_PUESTO")
    private String nomPuesto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SAL_MAXIMO")
    private BigDecimal salMaximo;
    @Column(name = "SAL_MINIMO")
    private BigDecimal salMinimo;
    @Size(max = 250)
    @Column(name = "ROL")
    private String rol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puestos")
    private List<Empleados> empleadosList;
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    public Puestos() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }

    
    public Puestos(PuestosPK puestosPK) {
	this.puestosPK = puestosPK;
    }

    public Puestos(short codCia, short codPuesto) {
	this.puestosPK = new PuestosPK(codCia, codPuesto);
    }

    public PuestosPK getPuestosPK() {
	return puestosPK;
    }

    public void setPuestosPK(PuestosPK puestosPK) {
	this.puestosPK = puestosPK;
    }

    public String getNomPuesto() {
	return nomPuesto;
    }

    public void setNomPuesto(String nomPuesto) {
	this.nomPuesto = nomPuesto;
    }

    public BigDecimal getSalMaximo() {
	return salMaximo;
    }

    public void setSalMaximo(BigDecimal salMaximo) {
	this.salMaximo = salMaximo;
    }

    public BigDecimal getSalMinimo() {
	return salMinimo;
    }

    public void setSalMinimo(BigDecimal salMinimo) {
	this.salMinimo = salMinimo;
    }

    public String getRol() {
	return rol;
    }

    public void setRol(String rol) {
	this.rol = rol;
    }

    @XmlTransient
    public List<Empleados> getEmpleadosList() {
	return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
	this.empleadosList = empleadosList;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (puestosPK != null ? puestosPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Puestos)) {
	    return false;
	}
	Puestos other = (Puestos) object;
	if ((this.puestosPK == null && other.puestosPK != null) || (this.puestosPK != null && !this.puestosPK.equals(other.puestosPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.Puestos[ puestosPK=" + puestosPK + " ]";
    }
    
}
