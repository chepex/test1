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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Departamentos.findByLcta1", query = "SELECT d FROM Departamentos d WHERE d.lcta1 = :lcta1"),
    @NamedQuery(name = "Departamentos.findByLcta2", query = "SELECT d FROM Departamentos d WHERE d.lcta2 = :lcta2"),
    @NamedQuery(name = "Departamentos.findByLcta3", query = "SELECT d FROM Departamentos d WHERE d.lcta3 = :lcta3"),
    @NamedQuery(name = "Departamentos.findByLcta4", query = "SELECT d FROM Departamentos d WHERE d.lcta4 = :lcta4"),
    @NamedQuery(name = "Departamentos.findByLcta5", query = "SELECT d FROM Departamentos d WHERE d.lcta5 = :lcta5"),
    @NamedQuery(name = "Departamentos.findByScta1", query = "SELECT d FROM Departamentos d WHERE d.scta1 = :scta1"),
    @NamedQuery(name = "Departamentos.findByScta2", query = "SELECT d FROM Departamentos d WHERE d.scta2 = :scta2"),
    @NamedQuery(name = "Departamentos.findByScta3", query = "SELECT d FROM Departamentos d WHERE d.scta3 = :scta3"),
    @NamedQuery(name = "Departamentos.findByScta4", query = "SELECT d FROM Departamentos d WHERE d.scta4 = :scta4"),
    @NamedQuery(name = "Departamentos.findByScta5", query = "SELECT d FROM Departamentos d WHERE d.scta5 = :scta5"),
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
    @Column(name = "LCTA_1")
    private Short lcta1;
    @Column(name = "LCTA_2")
    private Short lcta2;
    @Column(name = "LCTA_3")
    private Short lcta3;
    @Column(name = "LCTA_4")
    private Short lcta4;
    @Column(name = "LCTA_5")
    private Short lcta5;
    @Column(name = "SCTA_1")
    private Short scta1;
    @Column(name = "SCTA_2")
    private Short scta2;
    @Column(name = "SCTA_3")
    private Short scta3;
    @Column(name = "SCTA_4")
    private Short scta4;
    @Column(name = "SCTA_5")
    private Short scta5;
      
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

    public Short getLcta1() {
        return lcta1;
    }

    public void setLcta1(Short lcta1) {
        this.lcta1 = lcta1;
    }

    public Short getLcta2() {
        return lcta2;
    }

    public void setLcta2(Short lcta2) {
        this.lcta2 = lcta2;
    }

    public Short getLcta3() {
        return lcta3;
    }

    public void setLcta3(Short lcta3) {
        this.lcta3 = lcta3;
    }

    public Short getLcta4() {
        return lcta4;
    }

    public void setLcta4(Short lcta4) {
        this.lcta4 = lcta4;
    }

    public Short getLcta5() {
        return lcta5;
    }

    public void setLcta5(Short lcta5) {
        this.lcta5 = lcta5;
    }

    public Short getScta1() {
        return scta1;
    }

    public void setScta1(Short scta1) {
        this.scta1 = scta1;
    }

    public Short getScta2() {
        return scta2;
    }

    public void setScta2(Short scta2) {
        this.scta2 = scta2;
    }

    public Short getScta3() {
        return scta3;
    }

    public void setScta3(Short scta3) {
        this.scta3 = scta3;
    }

    public Short getScta4() {
        return scta4;
    }

    public void setScta4(Short scta4) {
        this.scta4 = scta4;
    }

    public Short getScta5() {
        return scta5;
    }

    public void setScta5(Short scta5) {
        this.scta5 = scta5;
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
