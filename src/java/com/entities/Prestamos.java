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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "PRESTAMOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamos.findAll", query = "SELECT p FROM Prestamos p"),
    @NamedQuery(name = "Prestamos.findByPagado", query = "SELECT p FROM Prestamos p WHERE p.prestamosPK.codCia = :codCia and p.saldo= 0"),    
    @NamedQuery(name = "Prestamos.findByPendiente", query = "SELECT p FROM Prestamos p WHERE p.prestamosPK.codCia = :codCia and p.saldo > 0"),    
    @NamedQuery(name = "Prestamos.findByCodCia", query = "SELECT p FROM Prestamos p WHERE p.prestamosPK.codCia = :codCia"),
    @NamedQuery(name = "Prestamos.findByCodEmp", query = "SELECT p FROM Prestamos p WHERE p.prestamosPK.codEmp = :codEmp and p.prestamosPK.codCia = :codCia  and p.saldo>0"),
    @NamedQuery(name = "Prestamos.findByPK", query = "SELECT p FROM Prestamos p WHERE p.prestamosPK.codEmp = :codEmp and p.prestamosPK.codCia = :codCia and p.prestamosPK.codDp = :codDp and p.prestamosPK.codPresta = :codPresta"),
    @NamedQuery(name = "Prestamos.findByCodDp", query = "SELECT p FROM Prestamos p WHERE p.prestamosPK.codDp = :codDp"),
    @NamedQuery(name = "Prestamos.findByNumRef", query = "SELECT p FROM Prestamos p WHERE p.prestamosPK.codPresta = :codPresta"),
    @NamedQuery(name = "Prestamos.findByMonto", query = "SELECT p FROM Prestamos p WHERE p.monto = :monto"),
    @NamedQuery(name = "Prestamos.findBySaldo", query = "SELECT p FROM Prestamos p WHERE p.saldo = :saldo"),
    @NamedQuery(name = "Prestamos.findByCuotas", query = "SELECT p FROM Prestamos p WHERE p.cuotas = :cuotas"),
    @NamedQuery(name = "Prestamos.findByCuotasP", query = "SELECT p FROM Prestamos p WHERE p.cuotasP = :cuotasP"),
    @NamedQuery(name = "Prestamos.findByFrecuencia", query = "SELECT p FROM Prestamos p WHERE p.frecuencia = :frecuencia")})
public class Prestamos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrestamosPK prestamosPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Column(name = "SALDO")
    private BigDecimal saldo;
    @Column(name = "CUOTAS")
    private Short cuotas;
    @Column(name = "CUOTAS_P")
    private Short cuotasP;
    @Column(name = "VALOR_CUOTA")
    private BigDecimal Vcuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FRECUENCIA")
    private Short frecuencia;
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
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    @Column(name = "NUM_REF")    
    private String numRef;        

    
    
    public Prestamos() {
    }

    public Prestamos(PrestamosPK prestamosPK) {
	this.prestamosPK = prestamosPK;
    }

    public Prestamos(short codCia, int codEmp, short codDp, short numRef) {
	this.prestamosPK = new PrestamosPK(codCia, codEmp, codDp, numRef);
    }

    public PrestamosPK getPrestamosPK() {
	return prestamosPK;
    }

    public void setPrestamosPK(PrestamosPK prestamosPK) {
	this.prestamosPK = prestamosPK;
    }

    public BigDecimal getMonto() {
	return monto;
    }

    public void setMonto(BigDecimal monto) {
	this.monto = monto;
    }

    public BigDecimal getVcuota() {
	return Vcuota;
    }

    public String getNumRef() {
	return numRef;
    }

    public void setNumRef(String numRef) {
	this.numRef = numRef;
    }

    
    public void setVcuota(BigDecimal Vcuota) {
	this.Vcuota = Vcuota;
    }

    public BigDecimal getSaldo() {
	return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
	this.saldo = saldo;
    }

    public Short getCuotas() {
	return cuotas;
    }

    public void setCuotas(Short cuotas) {
	this.cuotas = cuotas;
    }

    public Short getCuotasP() {
	return cuotasP;
    }

    public void setCuotasP(Short cuotasP) {
	this.cuotasP = cuotasP;
    }

    public Short getFrecuencia() {
	return frecuencia;
    }

    public void setFrecuencia(Short frecuencia) {
	this.frecuencia = frecuencia;
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

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (prestamosPK != null ? prestamosPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Prestamos)) {
	    return false;
	}
	Prestamos other = (Prestamos) object;
	if ((this.prestamosPK == null && other.prestamosPK != null) || (this.prestamosPK != null && !this.prestamosPK.equals(other.prestamosPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.Prestamos[ prestamosPK=" + prestamosPK + " ]";
    }

    
    
}
