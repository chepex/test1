/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "PROFESIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesiones.findAll", query = "SELECT p FROM Profesiones p"),
    @NamedQuery(name = "Profesiones.findByCodProfesion", query = "SELECT p FROM Profesiones p WHERE p.codProfesion = :codProfesion"),
    @NamedQuery(name = "Profesiones.findByDescripcion", query = "SELECT p FROM Profesiones p WHERE p.descripcion = :descripcion")})
public class Profesiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PROFESION")
    private Short codProfesion;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "codProfesion")
    private List<Empleados> empleadosList;

    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    public Profesiones() {
    }

    public Profesiones(Short codProfesion) {
	this.codProfesion = codProfesion;
    }

    public Short getCodProfesion() {
	return codProfesion;
    }

    public void setCodProfesion(Short codProfesion) {
	this.codProfesion = codProfesion;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
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
	hash += (codProfesion != null ? codProfesion.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Profesiones)) {
	    return false;
	}
	Profesiones other = (Profesiones) object;
	if ((this.codProfesion == null && other.codProfesion != null) || (this.codProfesion != null && !this.codProfesion.equals(other.codProfesion))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.Profesiones[ codProfesion=" + codProfesion + " ]";
    }
    
}
