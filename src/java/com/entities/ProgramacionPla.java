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
@Table(name = "PROGRAMACION_PLA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramacionPla.findAll", query = "SELECT p FROM ProgramacionPla p"),
    @NamedQuery(name = "ProgramacionPla.findByCodCia", query = "SELECT p FROM ProgramacionPla p WHERE p.programacionPlaPK.codCia = :codCia"),
    @NamedQuery(name = "ProgramacionPla.findByStatus", query = "SELECT p FROM ProgramacionPla p WHERE p.status = :status and p.programacionPlaPK.codCia = :codCia "),
    @NamedQuery(name = "ProgramacionPla.findByPK", query = "SELECT p FROM ProgramacionPla p WHERE p.programacionPlaPK.codCia = :codCia and p.programacionPlaPK.secuencia = :secuencia "),
    @NamedQuery(name = "ProgramacionPla.findByFechaPago", query = "SELECT p FROM ProgramacionPla p WHERE p.fechaPago = :fechaPago"),
    @NamedQuery(name = "ProgramacionPla.findByNumPlanilla", query = "SELECT p FROM ProgramacionPla p WHERE p.numPlanilla = :numPlanilla"),
    @NamedQuery(name = "ProgramacionPla.findByAnio", query = "SELECT p FROM ProgramacionPla p WHERE p.anio = :anio"),
    @NamedQuery(name = "ProgramacionPla.findByMes", query = "SELECT p FROM ProgramacionPla p WHERE p.mes = :mes"),
    @NamedQuery(name = "ProgramacionPla.findBySecuencia", query = "SELECT p FROM ProgramacionPla p WHERE p.programacionPlaPK.secuencia = :secuencia")})
public class ProgramacionPla implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramacionPlaPK programacionPlaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Column(name = "FECHA_CORTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCorte;    
    @Column(name = "FRECUENCIA")
    private short frecuencia;   
    public Date getFechaCorte() {
	return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
	this.fechaCorte = fechaCorte;
    }
    @Column(name = "NUM_PLANILLA")
    private Short numPlanilla;
    @Column(name = "ANIO")
    private Short anio;
    @Column(name = "MES")
    private Short mes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programacionPla")
    private List<PlanillaHoras> planillaHorasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programacionPla")
    private List<OtrosMovDp> otrosMovDpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programacionPla")
    private List<MovDp> movDpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programacionPla")
    private List<ResumenAsistencia> resumenAsistenciaList;
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_TIPOPLA", referencedColumnName = "COD_TIPOPLA")})
    @ManyToOne(optional = false)
    private TiposPlanilla tiposPlanilla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programacionPla")
    private List<Planilla> planillaList;
    
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    
    public ProgramacionPla() {
	
	
    }

    public short getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(short frecuencia) {
        this.frecuencia = frecuencia;
    }

    public ProgramacionPla(ProgramacionPlaPK programacionPlaPK) {
	this.programacionPlaPK = programacionPlaPK;
    }

    public ProgramacionPla(ProgramacionPlaPK programacionPlaPK, String status) {
	this.programacionPlaPK = programacionPlaPK;
	this.status = status;
    }

    public ProgramacionPla(short codCia, long secuencia) {
	
	this.programacionPlaPK = new ProgramacionPlaPK(codCia, secuencia);
    }

    public ProgramacionPlaPK getProgramacionPlaPK() {
	
    	return programacionPlaPK;
    }

    public void setProgramacionPlaPK(ProgramacionPlaPK programacionPlaPK) {
	
	this.programacionPlaPK = programacionPlaPK;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Date getFechaPago() {
	return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
	this.fechaPago = fechaPago;
    }

    public Short getNumPlanilla() {
	return numPlanilla;
    }

    public void setNumPlanilla(Short numPlanilla) {
	this.numPlanilla = numPlanilla;
    }

    public Short getAnio() {
	return anio;
    }

    public void setAnio(Short anio) {
	this.anio = anio;
    }

    public Short getMes() {
	return mes;
    }

    public void setMes(Short mes) {
	this.mes = mes;
    }

    @XmlTransient
    public List<PlanillaHoras> getPlanillaHorasList() {
	return planillaHorasList;
    }

    public void setPlanillaHorasList(List<PlanillaHoras> planillaHorasList) {
	this.planillaHorasList = planillaHorasList;
    }

    @XmlTransient
    public List<OtrosMovDp> getOtrosMovDpList() {
	return otrosMovDpList;
    }

    public void setOtrosMovDpList(List<OtrosMovDp> otrosMovDpList) {
	this.otrosMovDpList = otrosMovDpList;
    }

    @XmlTransient
    public List<MovDp> getMovDpList() {
	return movDpList;
    }

    public void setMovDpList(List<MovDp> movDpList) {
	this.movDpList = movDpList;
    }

    @XmlTransient
    public List<ResumenAsistencia> getResumenAsistenciaList() {
	return resumenAsistenciaList;
    }

    public void setResumenAsistenciaList(List<ResumenAsistencia> resumenAsistenciaList) {
	this.resumenAsistenciaList = resumenAsistenciaList;
    }

    public TiposPlanilla getTiposPlanilla() {
	return tiposPlanilla;
    }

    public void setTiposPlanilla(TiposPlanilla tiposPlanilla) {
	this.tiposPlanilla = tiposPlanilla;
    }

    @XmlTransient
    public List<Planilla> getPlanillaList() {
	return planillaList;
    }

    public void setPlanillaList(List<Planilla> planillaList) {
	this.planillaList = planillaList;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (programacionPlaPK != null ? programacionPlaPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof ProgramacionPla)) {
	    return false;
	}
	ProgramacionPla other = (ProgramacionPla) object;
	if ((this.programacionPlaPK == null && other.programacionPlaPK != null) || (this.programacionPlaPK != null && !this.programacionPlaPK.equals(other.programacionPlaPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.ProgramacionPla[ programacionPlaPK=" + programacionPlaPK + " ]";
    }
    
}
