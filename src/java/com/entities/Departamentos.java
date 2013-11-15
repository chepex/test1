/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "DEPARTAMENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByCodCia", query = "SELECT d FROM Departamentos d WHERE d.departamentosPK.codCia = :codCia"),
    @NamedQuery(name = "Departamentos.findByCodDepto", query = "SELECT d FROM Departamentos d WHERE d.departamentosPK.codDepto = :codDepto"),
    @NamedQuery(name = "Departamentos.findByPuesto", query = "SELECT d FROM Departamentos d WHERE d.puestosList = :puestosList"),
    @NamedQuery(name = "Departamentos.findByNomDepto", query = "SELECT d FROM Departamentos d WHERE d.nomDepto = :nomDepto"),
    @NamedQuery(name = "Departamentos.findByEmpleados", query = "SELECT d FROM Departamentos d WHERE d.empleados = :empleados"),
    @NamedQuery(name = "Departamentos.findByBcta1", query = "SELECT d FROM Departamentos d WHERE d.bcta1 = :bcta1"),
    @NamedQuery(name = "Departamentos.findByBcta2", query = "SELECT d FROM Departamentos d WHERE d.bcta2 = :bcta2"),
    @NamedQuery(name = "Departamentos.findByBcta3", query = "SELECT d FROM Departamentos d WHERE d.bcta3 = :bcta3"),
    @NamedQuery(name = "Departamentos.findByBcta4", query = "SELECT d FROM Departamentos d WHERE d.bcta4 = :bcta4"),
    @NamedQuery(name = "Departamentos.findByBcta5", query = "SELECT d FROM Departamentos d WHERE d.bcta5 = :bcta5"),
    @NamedQuery(name = "Departamentos.findByEcta1", query = "SELECT d FROM Departamentos d WHERE d.ecta1 = :ecta1"),
    @NamedQuery(name = "Departamentos.findByEcta2", query = "SELECT d FROM Departamentos d WHERE d.ecta2 = :ecta2"),
    @NamedQuery(name = "Departamentos.findByEcta3", query = "SELECT d FROM Departamentos d WHERE d.ecta3 = :ecta3"),
    @NamedQuery(name = "Departamentos.findByEcta4", query = "SELECT d FROM Departamentos d WHERE d.ecta4 = :ecta4"),
    @NamedQuery(name = "Departamentos.findByEcta5", query = "SELECT d FROM Departamentos d WHERE d.ecta5 = :ecta5"),
    @NamedQuery(name = "Departamentos.findByCta1", query = "SELECT d FROM Departamentos d WHERE d.cta1 = :cta1"),
    @NamedQuery(name = "Departamentos.findByCta2", query = "SELECT d FROM Departamentos d WHERE d.cta2 = :cta2"),
    @NamedQuery(name = "Departamentos.findByCta3", query = "SELECT d FROM Departamentos d WHERE d.cta3 = :cta3"),
    @NamedQuery(name = "Departamentos.findByCta4", query = "SELECT d FROM Departamentos d WHERE d.cta4 = :cta4"),
    @NamedQuery(name = "Departamentos.findByCta5", query = "SELECT d FROM Departamentos d WHERE d.cta5 = :cta5"),
    @NamedQuery(name = "Departamentos.findByContable", query = "SELECT d FROM Departamentos d WHERE d.contable = :contable"),
    @NamedQuery(name = "Departamentos.findByCodTipopla", query = "SELECT d FROM Departamentos d WHERE d.codTipopla = :codTipopla"),
    @NamedQuery(name = "Departamentos.findByProyecto", query = "SELECT d FROM Departamentos d WHERE d.proyecto = :proyecto"),
    @NamedQuery(name = "Departamentos.findByCodEquivalencia", query = "SELECT d FROM Departamentos d WHERE d.codEquivalencia = :codEquivalencia"),
    @NamedQuery(name = "Departamentos.findByUsuario", query = "SELECT d FROM Departamentos d WHERE d.usuario = :usuario"),
    @NamedQuery(name = "Departamentos.findByFechaReg", query = "SELECT d FROM Departamentos d WHERE d.fechaReg = :fechaReg"),
    @NamedQuery(name = "Departamentos.findByDistrito", query = "SELECT d FROM Departamentos d WHERE d.distrito = :distrito"),
    @NamedQuery(name = "Departamentos.findByFirmaDoc", query = "SELECT d FROM Departamentos d WHERE d.firmaDoc = :firmaDoc"),
    @NamedQuery(name = "Departamentos.findByVSecuencia", query = "SELECT d FROM Departamentos d WHERE d.vSecuencia = :vSecuencia")})


public class Departamentos implements Serializable {
@JoinTable(name = "PUESTO_X_DEPTO", joinColumns = {
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA"),
        @JoinColumn(name = "COD_DEPTO", referencedColumnName = "COD_DEPTO")}, inverseJoinColumns = {
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA"),
        @JoinColumn(name = "COD_PUESTO", referencedColumnName = "COD_PUESTO")})
    @ManyToMany
    private List<Puestos> puestosList;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DepartamentosPK departamentosPK;
    @Size(max = 30)
    @Column(name = "NOM_DEPTO")
    private String nomDepto;
    @Column(name = "EMPLEADOS")
    private Integer empleados;
    @Column(name = "BCTA_1")
    private Short bcta1;
    @Column(name = "BCTA_2")
    private Short bcta2;
    @Column(name = "BCTA_3")
    private Short bcta3;
    @Column(name = "BCTA_4")
    private Short bcta4;
    @Column(name = "BCTA_5")
    private Short bcta5;
    @Column(name = "ECTA_1")
    private Short ecta1;
    @Column(name = "ECTA_2")
    private Short ecta2;
    @Column(name = "ECTA_3")
    private Short ecta3;
    @Column(name = "ECTA_4")
    private Short ecta4;
    @Column(name = "ECTA_5")
    private Short ecta5;
    @Column(name = "CTA_1")
    private Short cta1;
    @Column(name = "CTA_2")
    private Short cta2;
    @Column(name = "CTA_3")
    private Short cta3;
    @Column(name = "CTA_4")
    private Short cta4;
    @Column(name = "CTA_5")
    private Short cta5;
    @Size(max = 1)
    @Column(name = "CONTABLE")
    private String contable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_TIPOPLA")
    private short codTipopla;
    @Size(max = 20)
    @Column(name = "PROYECTO")
    private String proyecto;
    @Size(max = 3)
    @Column(name = "COD_EQUIVALENCIA")
    private String codEquivalencia;
    @Size(max = 20)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Column(name = "DISTRITO")
    private Long distrito;
    @Size(max = 1)
    @Column(name = "FIRMA_DOC")
    private String firmaDoc;
    @Column(name = "V_SECUENCIA")
    private Long vSecuencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamentos")
    private List<Planilla> planillaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamentos")
    private List<Empleados> empleadosList;

    
    
    public Departamentos() {
    }

    public Departamentos(DepartamentosPK departamentosPK) {
	this.departamentosPK = departamentosPK;
    }

    public Departamentos(DepartamentosPK departamentosPK, short codTipopla) {
	this.departamentosPK = departamentosPK;
	this.codTipopla = codTipopla;
    }

    public Departamentos(short codCia, short codDepto) {
	this.departamentosPK = new DepartamentosPK(codCia, codDepto);
    }

    public DepartamentosPK getDepartamentosPK() {
	return departamentosPK;
    }

    public void setDepartamentosPK(DepartamentosPK departamentosPK) {
	this.departamentosPK = departamentosPK;
    }

    public String getNomDepto() {
	return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
	this.nomDepto = nomDepto;
    }

    public Integer getEmpleados() {
	return empleados;
    }

    public void setEmpleados(Integer empleados) {
	this.empleados = empleados;
    }

    public Short getBcta1() {
	return bcta1;
    }

    public void setBcta1(Short bcta1) {
	this.bcta1 = bcta1;
    }

    public Short getBcta2() {
	return bcta2;
    }

    public void setBcta2(Short bcta2) {
	this.bcta2 = bcta2;
    }

    public Short getBcta3() {
	return bcta3;
    }

    public void setBcta3(Short bcta3) {
	this.bcta3 = bcta3;
    }

    public Short getBcta4() {
	return bcta4;
    }

    public void setBcta4(Short bcta4) {
	this.bcta4 = bcta4;
    }

    public Short getBcta5() {
	return bcta5;
    }

    public void setBcta5(Short bcta5) {
	this.bcta5 = bcta5;
    }

    public Short getEcta1() {
	return ecta1;
    }

    public void setEcta1(Short ecta1) {
	this.ecta1 = ecta1;
    }

    public Short getEcta2() {
	return ecta2;
    }

    public void setEcta2(Short ecta2) {
	this.ecta2 = ecta2;
    }

    public Short getEcta3() {
	return ecta3;
    }

    public void setEcta3(Short ecta3) {
	this.ecta3 = ecta3;
    }

    public Short getEcta4() {
	return ecta4;
    }

    public void setEcta4(Short ecta4) {
	this.ecta4 = ecta4;
    }

    public Short getEcta5() {
	return ecta5;
    }

    public void setEcta5(Short ecta5) {
	this.ecta5 = ecta5;
    }

    public Short getCta1() {
	return cta1;
    }

    public void setCta1(Short cta1) {
	this.cta1 = cta1;
    }

    public Short getCta2() {
	return cta2;
    }

    public void setCta2(Short cta2) {
	this.cta2 = cta2;
    }

    public Short getCta3() {
	return cta3;
    }

    public void setCta3(Short cta3) {
	this.cta3 = cta3;
    }

    public Short getCta4() {
	return cta4;
    }

    public void setCta4(Short cta4) {
	this.cta4 = cta4;
    }

    public Short getCta5() {
	return cta5;
    }

    public void setCta5(Short cta5) {
	this.cta5 = cta5;
    }

    public String getContable() {
	return contable;
    }

    public void setContable(String contable) {
	this.contable = contable;
    }

    public short getCodTipopla() {
	return codTipopla;
    }

    public void setCodTipopla(short codTipopla) {
	this.codTipopla = codTipopla;
    }

    public String getProyecto() {
	return proyecto;
    }

    public void setProyecto(String proyecto) {
	this.proyecto = proyecto;
    }

    public String getCodEquivalencia() {
	return codEquivalencia;
    }

    public void setCodEquivalencia(String codEquivalencia) {
	this.codEquivalencia = codEquivalencia;
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

    public Long getDistrito() {
	return distrito;
    }

    public void setDistrito(Long distrito) {
	this.distrito = distrito;
    }

    public String getFirmaDoc() {
	return firmaDoc;
    }

    public void setFirmaDoc(String firmaDoc) {
	this.firmaDoc = firmaDoc;
    }

    public Long getVSecuencia() {
	return vSecuencia;
    }

    public void setVSecuencia(Long vSecuencia) {
	this.vSecuencia = vSecuencia;
    }

    @XmlTransient
    public List<Planilla> getPlanillaList() {
	return planillaList;
    }

    public void setPlanillaList(List<Planilla> planillaList) {
	this.planillaList = planillaList;
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
	hash += (departamentosPK != null ? departamentosPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Departamentos)) {
	    return false;
	}
	Departamentos other = (Departamentos) object;
	if ((this.departamentosPK == null && other.departamentosPK != null) || (this.departamentosPK != null && !this.departamentosPK.equals(other.departamentosPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.Departamentos[ departamentosPK=" + departamentosPK + " ]";
    }

    
    
}
