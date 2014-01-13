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
@Table(name = "PLANILLAH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planillah.findAll", query = "SELECT p FROM Planillah p"),
    @NamedQuery(name = "Planillah.findByCodCia", query = "SELECT p FROM Planillah p WHERE p.planillahPK.codCia = :codCia"),
    @NamedQuery(name = "Planillah.findBySecuencia", query = "SELECT p FROM Planillah p WHERE p.planillahPK.secuencia = :secuencia"),
    @NamedQuery(name = "Planillah.findByCodEmp", query = "SELECT p FROM Planillah p WHERE p.planillahPK.codEmp = :codEmp"),
    @NamedQuery(name = "Planillah.findByDias", query = "SELECT p FROM Planillah p WHERE p.dias = :dias"),
    @NamedQuery(name = "Planillah.findByBruto", query = "SELECT p FROM Planillah p WHERE p.bruto = :bruto"),
    @NamedQuery(name = "Planillah.findByHorasextras", query = "SELECT p FROM Planillah p WHERE p.horasextras = :horasextras"),
    @NamedQuery(name = "Planillah.findByBonos", query = "SELECT p FROM Planillah p WHERE p.bonos = :bonos"),
    @NamedQuery(name = "Planillah.findByNeto", query = "SELECT p FROM Planillah p WHERE p.neto = :neto"),
    @NamedQuery(name = "Planillah.findByAfp", query = "SELECT p FROM Planillah p WHERE p.afp = :afp"),
    @NamedQuery(name = "Planillah.findByIsss", query = "SELECT p FROM Planillah p WHERE p.isss = :isss"),
    @NamedQuery(name = "Planillah.findByRenta", query = "SELECT p FROM Planillah p WHERE p.renta = :renta"),
    @NamedQuery(name = "Planillah.findByDeducciones", query = "SELECT p FROM Planillah p WHERE p.deducciones = :deducciones"),
    @NamedQuery(name = "Planillah.findByLiquido", query = "SELECT p FROM Planillah p WHERE p.liquido = :liquido"),
    @NamedQuery(name = "Planillah.findByUsuario", query = "SELECT p FROM Planillah p WHERE p.usuario = :usuario"),
    @NamedQuery(name = "Planillah.findByFechaReg", query = "SELECT p FROM Planillah p WHERE p.fechaReg = :fechaReg"),
    @NamedQuery(name = "Planillah.findByAnio", query = "SELECT p FROM Planillah p WHERE p.anio = :anio"),
    @NamedQuery(name = "Planillah.findByMes", query = "SELECT p FROM Planillah p WHERE p.mes = :mes"),
    @NamedQuery(name = "Planillah.findByCodTipopla", query = "SELECT p FROM Planillah p WHERE p.codTipopla = :codTipopla"),
    @NamedQuery(name = "Planillah.findByNumPlanilla", query = "SELECT p FROM Planillah p WHERE p.numPlanilla = :numPlanilla")})
public class Planillah implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlanillahPK planillahPK;
    @Size(max = 3)
    @Column(name = "DIAS")
    private String dias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BRUTO")
    private BigDecimal bruto;
    @Column(name = "HORASEXTRAS")
    private BigDecimal horasextras;
    @Column(name = "BONOS")
    private BigDecimal bonos;
    @Column(name = "NETO")
    private BigDecimal neto;
    @Column(name = "AFP")
    private BigDecimal afp;
    @Column(name = "ISSS")
    private BigDecimal isss;
    @Column(name = "RENTA")
    private BigDecimal renta;
    @Column(name = "DEDUCCIONES")
    private BigDecimal deducciones;
    @Column(name = "LIQUIDO")
    private BigDecimal liquido;
    @Size(max = 100)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Column(name = "ANIO")
    private Short anio;
    @Column(name = "MES")
    private Short mes;
    @Column(name = "COD_TIPOPLA")
    private Short codTipopla;
    @Column(name = "NUM_PLANILLA")
    private Short numPlanilla;
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
        @JoinColumn(name = "COD_DEPTO", referencedColumnName = "COD_DEPTO")})
    @ManyToOne(optional = false)
    private Departamentos departamentos;

    public Planillah() {
    }

    public Planillah(PlanillahPK planillahPK) {
        this.planillahPK = planillahPK;
    }

    public Planillah(short codCia, long secuencia, int codEmp) {
        this.planillahPK = new PlanillahPK(codCia, secuencia, codEmp);
    }

    public PlanillahPK getPlanillahPK() {
        return planillahPK;
    }

    public void setPlanillahPK(PlanillahPK planillahPK) {
        this.planillahPK = planillahPK;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public BigDecimal getBruto() {
        return bruto;
    }

    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public BigDecimal getHorasextras() {
        return horasextras;
    }

    public void setHorasextras(BigDecimal horasextras) {
        this.horasextras = horasextras;
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

    public BigDecimal getAfp() {
        return afp;
    }

    public void setAfp(BigDecimal afp) {
        this.afp = afp;
    }

    public BigDecimal getIsss() {
        return isss;
    }

    public void setIsss(BigDecimal isss) {
        this.isss = isss;
    }

    public BigDecimal getRenta() {
        return renta;
    }

    public void setRenta(BigDecimal renta) {
        this.renta = renta;
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

    public Short getCodTipopla() {
        return codTipopla;
    }

    public void setCodTipopla(Short codTipopla) {
        this.codTipopla = codTipopla;
    }

    public Short getNumPlanilla() {
        return numPlanilla;
    }

    public void setNumPlanilla(Short numPlanilla) {
        this.numPlanilla = numPlanilla;
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
        hash += (planillahPK != null ? planillahPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planillah)) {
            return false;
        }
        Planillah other = (Planillah) object;
        if ((this.planillahPK == null && other.planillahPK != null) || (this.planillahPK != null && !this.planillahPK.equals(other.planillahPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Planillah[ planillahPK=" + planillahPK + " ]";
    }
    
}
