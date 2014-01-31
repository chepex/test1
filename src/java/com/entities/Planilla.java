/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "PLANILLA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planilla.findAll", query = "SELECT p FROM Planilla p"),
    @NamedQuery(name = "Planilla.findByCodCia", query = "SELECT p FROM Planilla p WHERE p.planillaPK.codCia = :codCia"),
    @NamedQuery(name = "Planilla.findByStatus", query = "SELECT p FROM Planilla p WHERE p.planillaPK.codCia = :codCia and  p.programacionPla.status = :status"),
    @NamedQuery(name = "Planilla.findByAnioMes", query = "SELECT p FROM Planilla p WHERE p.planillaPK.codCia = :codCia and p.programacionPla.anio = :anio and p.programacionPla.mes = :mes and p.programacionPla.status = 'C'"),    
    @NamedQuery(name = "Planilla.findByAnioMes2", query = "SELECT p FROM Planilla p WHERE p.planillaPK.codCia = :codCia and p.programacionPla.anio = :anio and p.programacionPla.mes = :mes and p.programacionPla.status = 'C' and p.planillaPK.codEmp = :codEmp"),        
    @NamedQuery(name = "Planilla.findByMes", query = "SELECT p FROM Planilla p WHERE p.planillaPK.codCia = :codCia and p.programacionPla.anio = :anio and p.programacionPla.mes = :mes and p.planillaPK.codEmp = :codEmp"),        
    @NamedQuery(name = "Planilla.findByPk", query = "SELECT p  FROM Planilla p WHERE p.planillaPK.codCia = :codCia and p.planillaPK.secuencia = :secuencia"),
    @NamedQuery(name = "Planilla.findBySecuencia", query = "SELECT p FROM Planilla p WHERE p.planillaPK.secuencia = :secuencia"),
    @NamedQuery(name = "Planilla.findByEmpt", query = "SELECT p FROM Planilla p WHERE p.planillaPK.codEmp = :codEmp and p.planillaPK.codCia= :codCia and p.programacionPla.anio = :anio"),        
    @NamedQuery(name = "Planilla.findByEmp", query = "SELECT p FROM Planilla p WHERE p.planillaPK.codEmp = :codEmp and p.planillaPK.secuencia = :secuencia and p.planillaPK.codCia= :codCia"),        
    @NamedQuery(name = "Planilla.findByCodEmp", query = "SELECT p FROM Planilla p WHERE p.planillaPK.codEmp = :codEmp and p.planillaPK.secuencia = :secuencia and p.planillaPK.codCia= :codCia"),
    @NamedQuery(name = "Planilla.findByDeducciones", query = "SELECT p FROM Planilla p WHERE p.deducciones = :deducciones"),        
    @NamedQuery(name = "Planilla.findByLiquido", query = "SELECT p FROM Planilla p WHERE p.liquido = :liquido")})
public class Planilla implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlanillaPK planillaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @Column(name = "CANTIDAHE")
    private BigDecimal cantidahe;
    @Column(name = "LLEGADATARDE")
    private BigDecimal llegadatarde;
    @Column(name = "CANT_LLEGAT")
    private BigDecimal cantLlegat;
    @Column(name = "BRUTO")
    private BigDecimal bruto;
    @Column(name = "HORASEXTRAS")
    private BigDecimal horasExtras;    
    @Column(name = "BONOS")
    private BigDecimal bonos;    
    @Column(name = "NETO")
    private BigDecimal neto;        
    @Column(name = "DEDUCCIONES")
    private BigDecimal deducciones;    
    @Column(name = "LIQUIDO")
    private BigDecimal liquido;
    @Column(name = "COD_DEPTO")
    private short codDepto;
    @Column(name = "DIAS")
    private String dias;  
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "SECUENCIA", referencedColumnName = "SECUENCIA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ProgramacionPla programacionPla;
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_EMP", referencedColumnName = "COD_EMP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Empleados empleados;
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_DEPTO", referencedColumnName = "COD_DEPTO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Departamentos departamentos;
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "SECUENCIA", referencedColumnName = "SECUENCIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_EMP", referencedColumnName = "COD_EMP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private ResumenAsistencia resumenAsistencia;    
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    
    public Planilla() {
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public BigDecimal getCantidahe() {
        return cantidahe;
    }

    public void setCantidahe(BigDecimal cantidahe) {
        this.cantidahe = cantidahe;
    }

    public BigDecimal getLlegadatarde() {
        return llegadatarde;
    }

    public void setLlegadatarde(BigDecimal llegadatarde) {
        this.llegadatarde = llegadatarde;
    }

    public BigDecimal getCantLlegat() {
        return cantLlegat;
    }

    public void setCantLlegat(BigDecimal cantLlegat) {
        this.cantLlegat = cantLlegat;
    }

    
    
    public ResumenAsistencia getResumenAsistencia() {
        return resumenAsistencia;
    }

    public void setResumenAsistencia(ResumenAsistencia resumenAsistencia) {
        this.resumenAsistencia = resumenAsistencia;
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

    public BigDecimal getBruto() {
        return bruto;
    }

    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public BigDecimal getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(BigDecimal horasExtras) {
        this.horasExtras = horasExtras;
    }

    public BigDecimal getBonos() {
        return bonos;
    }

    public void setBonos(BigDecimal bonos) {
        this.bonos = bonos;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public short getCodDepto() {
        return codDepto;
    }

    public void setCodDepto(short codDepto) {
        this.codDepto = codDepto;
    }
        
    public Planilla(PlanillaPK planillaPK) {
	this.planillaPK = planillaPK;
    }

    public Planilla(short codCia, long secuencia, int codEmp) {
	this.planillaPK = new PlanillaPK(codCia, secuencia, codEmp);
    }

    public PlanillaPK getPlanillaPK() {
	return planillaPK;
    }

    public void setPlanillaPK(PlanillaPK planillaPK) {
	this.planillaPK = planillaPK;
    }

    public BigDecimal getDeducciones() {
	return deducciones;
    }

    public void setDeducciones(BigDecimal deducciones) {
	this.deducciones = deducciones;
    }

    

    public BigDecimal getLiquido() {
	return liquido;
    }

    public void setLiquido(BigDecimal liquido) {
	this.liquido = liquido;
    }

    public ProgramacionPla getProgramacionPla() {
	return programacionPla;
    }

    public void setProgramacionPla(ProgramacionPla programacionPla) {
	this.programacionPla = programacionPla;
    }

    public Empleados getEmpleados() {
	return empleados;
    }

    public void setEmpleados(Empleados empleados) {
	this.empleados = empleados;
    }

    public Departamentos getDepartamentos() {
	return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
	this.departamentos = departamentos;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (planillaPK != null ? planillaPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Planilla)) {
	    return false;
	}
	Planilla other = (Planilla) object;
	if ((this.planillaPK == null && other.planillaPK != null) || (this.planillaPK != null && !this.planillaPK.equals(other.planillaPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.Planilla[ planillaPK=" + planillaPK + " ]";
    }


    
    
}
