/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p"),
    @NamedQuery(name = "Parametros.findById", query = "SELECT p FROM Parametros p WHERE p.parametrosPK.id = :id"),
    @NamedQuery(name = "Parametros.findByNombre", query = "SELECT p FROM Parametros p WHERE p.nombre = :nombre and p.parametrosPK.codCia = :codCia"),
    @NamedQuery(name = "Parametros.findByDescripcion", query = "SELECT p FROM Parametros p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Parametros.findByValorTxt", query = "SELECT p FROM Parametros p WHERE p.valorTxt = :valorTxt"),
    @NamedQuery(name = "Parametros.findByValorInt", query = "SELECT p FROM Parametros p WHERE p.valorInt = :valorInt"),
    @NamedQuery(name = "Parametros.findByCodCia", query = "SELECT p FROM Parametros p WHERE p.parametrosPK.codCia = :codCia")})
public class Parametros implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParametrosPK parametrosPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 250)
    @Column(name = "VALOR_TXT")
    private String valorTxt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_INT")
    private BigDecimal valorInt;

    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    
    public Parametros() {
    }

    public Parametros(ParametrosPK parametrosPK) {
	this.parametrosPK = parametrosPK;
    }

    public Parametros(ParametrosPK parametrosPK, String nombre) {
	this.parametrosPK = parametrosPK;
	this.nombre = nombre;
    }

    public Parametros(short id, short codCia) {
	this.parametrosPK = new ParametrosPK(id, codCia);
    }

    public ParametrosPK getParametrosPK() {
	return parametrosPK;
    }

    public void setParametrosPK(ParametrosPK parametrosPK) {
	this.parametrosPK = parametrosPK;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public String getValorTxt() {
	return valorTxt;
    }

    public void setValorTxt(String valorTxt) {
	this.valorTxt = valorTxt;
    }

    public BigDecimal getValorInt() {
	return valorInt;
    }

    public void setValorInt(BigDecimal valorInt) {
	this.valorInt = valorInt;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (parametrosPK != null ? parametrosPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Parametros)) {
	    return false;
	}
	Parametros other = (Parametros) object;
	if ((this.parametrosPK == null && other.parametrosPK != null) || (this.parametrosPK != null && !this.parametrosPK.equals(other.parametrosPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.Parametros[ parametrosPK=" + parametrosPK + " ]";
    }
    
}
