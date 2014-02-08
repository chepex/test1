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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "PLANILLA_ISSS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanillaIsss.findAll", query = "SELECT p FROM PlanillaIsss p"),
    @NamedQuery(name = "PlanillaIsss.findByCodCia", query = "SELECT p FROM PlanillaIsss p WHERE p.planillaIsssPK.codCia = :codCia"),
    @NamedQuery(name = "PlanillaIsss.findByAnio", query = "SELECT p FROM PlanillaIsss p WHERE p.planillaIsssPK.anio = :anio"),
    @NamedQuery(name = "PlanillaIsss.findByMes", query = "SELECT p FROM PlanillaIsss p WHERE p.planillaIsssPK.mes = :mes"),    
    @NamedQuery(name = "PlanillaIsss.findByCodEmp", query = "SELECT p FROM PlanillaIsss p WHERE  p.planillaIsssPK.codCia = :codCia  "
        + " and p.planillaIsssPK.anio = :anio"
        + " and p.planillaIsssPK.mes = :mes "
        + " and p.planillaIsssPK.codEmp = :codEmp")        
        ,    
    @NamedQuery(name = "PlanillaIsss.findByNoPatronal", query = "SELECT p FROM PlanillaIsss p WHERE p.noPatronal = :noPatronal"),
    @NamedQuery(name = "PlanillaIsss.findByNoAfilacion", query = "SELECT p FROM PlanillaIsss p WHERE p.noAfilacion = :noAfilacion"),
    @NamedQuery(name = "PlanillaIsss.findByNombre", query = "SELECT p FROM PlanillaIsss p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PlanillaIsss.findBySalDebengado", query = "SELECT p FROM PlanillaIsss p WHERE p.salDebengado = :salDebengado"),
    @NamedQuery(name = "PlanillaIsss.findByDiasRemunerados", query = "SELECT p FROM PlanillaIsss p WHERE p.diasRemunerados = :diasRemunerados"),
    @NamedQuery(name = "PlanillaIsss.findByHorasJornada", query = "SELECT p FROM PlanillaIsss p WHERE p.horasJornada = :horasJornada"),
    @NamedQuery(name = "PlanillaIsss.findByCodObserva", query = "SELECT p FROM PlanillaIsss p WHERE p.codObserva = :codObserva"),
    @NamedQuery(name = "PlanillaIsss.findByUsuario", query = "SELECT p FROM PlanillaIsss p WHERE p.usuario = :usuario"),
    @NamedQuery(name = "PlanillaIsss.findByFechaReg", query = "SELECT p FROM PlanillaIsss p WHERE p.fechaReg = :fechaReg"),
    @NamedQuery(name = "PlanillaIsss.findByCorrelativo", query = "SELECT p FROM PlanillaIsss p WHERE p.correlativo = :correlativo")})
public class PlanillaIsss implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlanillaIsssPK planillaIsssPK;
    @Size(max = 9)
    @Column(name = "NO_PATRONAL")
    private String noPatronal;
    @Size(max = 9)
    @Column(name = "NO_AFILACION")
    private String noAfilacion;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SAL_DEBENGADO")
    private BigDecimal salDebengado;
    @Column(name = "DIAS_REMUNERADOS")
    private String diasRemunerados;
    @Size(max = 2)
    @Column(name = "HORAS_JORNADA")
    private String horasJornada;    
    @Column(name = "COD_OBSERVA")
    private Short codObserva;
    @Size(max = 20)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Column(name = "CORRELATIVO")
    private Short correlativo;
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_EMP", referencedColumnName = "COD_EMP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Empleados empleados;

    public PlanillaIsss() {
    }

    public PlanillaIsss(PlanillaIsssPK planillaIsssPK) {
        this.planillaIsssPK = planillaIsssPK;
    }

    public PlanillaIsss(short codCia, short anio, short mes, int codEmp) {
        this.planillaIsssPK = new PlanillaIsssPK(codCia, anio, mes, codEmp);
    }

    public PlanillaIsssPK getPlanillaIsssPK() {
        return planillaIsssPK;
    }

    public void setPlanillaIsssPK(PlanillaIsssPK planillaIsssPK) {
        this.planillaIsssPK = planillaIsssPK;
    }

    public String getNoPatronal() {
        return noPatronal;
    }

    public void setNoPatronal(String noPatronal) {
        this.noPatronal = noPatronal;
    }

    public String getNoAfilacion() {
        return noAfilacion;
    }

    public void setNoAfilacion(String noAfilacion) {
        this.noAfilacion = noAfilacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSalDebengado() {
        return salDebengado;
    }

    public void setSalDebengado(BigDecimal salDebengado) {
        this.salDebengado = salDebengado;
    }

    public String getDiasRemunerados() {
        return diasRemunerados;
    }

    public void setDiasRemunerados(String diasRemunerados) {
        this.diasRemunerados = diasRemunerados;
    }

    public Short getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Short correlativo) {
        this.correlativo = correlativo;
    }



    public String getHorasJornada() {
        return horasJornada;
    }

    public void setHorasJornada(String horasJornada) {
        this.horasJornada = horasJornada;
    }

    public Short getCodObserva() {
        return codObserva;
    }

    public void setCodObserva(Short codObserva) {
        this.codObserva = codObserva;
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



    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planillaIsssPK != null ? planillaIsssPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanillaIsss)) {
            return false;
        }
        PlanillaIsss other = (PlanillaIsss) object;
        if ((this.planillaIsssPK == null && other.planillaIsssPK != null) || (this.planillaIsssPK != null && !this.planillaIsssPK.equals(other.planillaIsssPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.PlanillaIsss[ planillaIsssPK=" + planillaIsssPK + " ]";
    }
    
}
