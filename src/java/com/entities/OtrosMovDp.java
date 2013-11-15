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
@Table(name = "OTROS_MOV_DP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtrosMovDp.findAll", query = "SELECT o FROM OtrosMovDp o"),
    @NamedQuery(name = "OtrosMovDp.findByCodCia", query = "SELECT o FROM OtrosMovDp o WHERE o.otrosMovDpPK.codCia = :codCia"),
    @NamedQuery(name = "OtrosMovDp.findBySecuencia", query = "SELECT o FROM OtrosMovDp o WHERE o.otrosMovDpPK.secuencia = :secuencia"),
    @NamedQuery(name = "OtrosMovDp.findByCodEmp", query = "SELECT o FROM OtrosMovDp o WHERE o.otrosMovDpPK.codEmp = :codEmp"),
    @NamedQuery(name = "OtrosMovDp.findByCodDp", query = "SELECT o FROM OtrosMovDp o WHERE o.otrosMovDpPK.codDp = :codDp"),
    @NamedQuery(name = "OtrosMovDp.findByValor", query = "SELECT o FROM OtrosMovDp o WHERE o.valor = :valor")})
public class OtrosMovDp implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OtrosMovDpPK otrosMovDpPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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

    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    
    public OtrosMovDp() {
    }

    public OtrosMovDp(OtrosMovDpPK otrosMovDpPK) {
	this.otrosMovDpPK = otrosMovDpPK;
    }

    public OtrosMovDp(short codCia, long secuencia, int codEmp, short codDp) {
	this.otrosMovDpPK = new OtrosMovDpPK(codCia, secuencia, codEmp, codDp);
    }

    public OtrosMovDpPK getOtrosMovDpPK() {
	return otrosMovDpPK;
    }

    public void setOtrosMovDpPK(OtrosMovDpPK otrosMovDpPK) {
	this.otrosMovDpPK = otrosMovDpPK;
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

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (otrosMovDpPK != null ? otrosMovDpPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof OtrosMovDp)) {
	    return false;
	}
	OtrosMovDp other = (OtrosMovDp) object;
	if ((this.otrosMovDpPK == null && other.otrosMovDpPK != null) || (this.otrosMovDpPK != null && !this.otrosMovDpPK.equals(other.otrosMovDpPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.OtrosMovDp[ otrosMovDpPK=" + otrosMovDpPK + " ]";
    }
    
}
