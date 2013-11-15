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
@Table(name = "PLANILLA_HORAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanillaHoras.findAll", query = "SELECT p FROM PlanillaHoras p"),
    @NamedQuery(name = "PlanillaHoras.findBySecuencia", query = "SELECT p FROM PlanillaHoras p WHERE p.planillaHorasPK.secuencia = :secuencia"),
    @NamedQuery(name = "PlanillaHoras.findByFiltro", query = "SELECT p FROM PlanillaHoras p WHERE p.planillaHorasPK.codCia = :codCia and p.usuario like :usuario and p.planillaHorasPK.secuencia = :secuencia"),
    @NamedQuery(name = "PlanillaHoras.findByFiltroSum", query = "SELECT p.planillaHorasPK.codCia, "
	+ "p.planillaHorasPK.codDp, "
	+ "p.planillaHorasPK.secuencia,"	
	+ "SUM(p.valor) "	
	+ "FROM PlanillaHoras p "
	+ "WHERE p.planillaHorasPK.codCia = :codCia and p.usuario like :usuario and p.planillaHorasPK.secuencia = :secuencia "
	+ "GROUP BY p.planillaHorasPK.codCia, p.planillaHorasPK.codDp,p.planillaHorasPK.secuencia"),	
    @NamedQuery(name = "PlanillaHoras.findByCodCia", query = "SELECT p FROM PlanillaHoras p WHERE p.planillaHorasPK.codCia = :codCia"),
    @NamedQuery(name = "PlanillaHoras.findByCodEmp", query = "SELECT p FROM PlanillaHoras p WHERE p.planillaHorasPK.codEmp = :codEmp"),
    @NamedQuery(name = "PlanillaHoras.findByCodDp", query = "SELECT p FROM PlanillaHoras p WHERE p.planillaHorasPK.codDp = :codDp"),
    @NamedQuery(name = "PlanillaHoras.findByValor", query = "SELECT p FROM PlanillaHoras p WHERE p.valor = :valor")})
public class PlanillaHoras implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlanillaHorasPK planillaHorasPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;

    public Date getFechaReg() {
	return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
	this.fechaReg = fechaReg;
    }


    @Column(name = "VALOR")
    private BigDecimal valor;
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
        @JoinColumn(name = "COD_DP", referencedColumnName = "COD_DP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private DeducPresta deducPresta;


    
    
    public PlanillaHoras() {
    }

    public PlanillaHoras(PlanillaHorasPK planillaHorasPK) {
	this.planillaHorasPK = planillaHorasPK;
    }

    public PlanillaHoras(long secuencia, short codCia, int codEmp, short codDp) {
	this.planillaHorasPK = new PlanillaHorasPK(secuencia, codCia, codEmp, codDp);
    }

    public PlanillaHorasPK getPlanillaHorasPK() {
	return planillaHorasPK;
    }

    public void setPlanillaHorasPK(PlanillaHorasPK planillaHorasPK) {
	this.planillaHorasPK = planillaHorasPK;
    }

    public BigDecimal getValor() {
	return valor;
    }

    public void setValor(BigDecimal valor) {
	this.valor = valor;
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

    public DeducPresta getDeducPresta() {
	return deducPresta;
    }

    public void setDeducPresta(DeducPresta deducPresta) {
	this.deducPresta = deducPresta;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }
    @Override
    public int hashCode() {
	int hash = 0;
	hash += (planillaHorasPK != null ? planillaHorasPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof PlanillaHoras)) {
	    return false;
	}
	PlanillaHoras other = (PlanillaHoras) object;
	if ((this.planillaHorasPK == null && other.planillaHorasPK != null) || (this.planillaHorasPK != null && !this.planillaHorasPK.equals(other.planillaHorasPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.PlanillaHoras[ planillaHorasPK=" + planillaHorasPK + " ]";
    }
    
}
