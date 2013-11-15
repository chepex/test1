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
@Table(name = "MOV_DP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovDp.findAll", query = "SELECT m FROM MovDp m"),
    @NamedQuery(name = "MovDp.findByPk", query = "SELECT m FROM MovDp m WHERE m.movDpPK.codCia = :codCia and  m.movDpPK.codDp = :codDp and  m.movDpPK.codEmp = :codEmp and  m.movDpPK.secuencia = :secuencia"),
    @NamedQuery(name = "MovDp.findByCodCia", query = "SELECT m FROM MovDp m WHERE  m.movDpPK.codCia = :codCia"),
    @NamedQuery(name = "MovDp.findBySR", query = "SELECT m FROM MovDp m WHERE  m.movDpPK.codCia = :codCia and m.movDpPK.codEmp = :codEmp and m.movDpPK.secuencia = :secuencia and m.deducPresta.sumaResta = :sumaResta"),    
    @NamedQuery(name = "MovDp.findBySRPrioridad", query = "SELECT m FROM MovDp m WHERE  m.movDpPK.codCia = :codCia and m.movDpPK.codEmp = :codEmp and m.movDpPK.secuencia = :secuencia and m.deducPresta.sumaResta = :sumaResta and  m.deducPresta.prioridad > 0 Order by m.deducPresta.prioridad DESC"),        
    @NamedQuery(name = "MovDp.findBySecuencia", query = "SELECT m FROM MovDp m WHERE m.movDpPK.codCia = :codCia and m.movDpPK.secuencia = :secuencia and m.generado = :generado"),
    @NamedQuery(name = "MovDp.findByCodEmp", query = "SELECT m FROM MovDp m WHERE m.movDpPK.codCia = :codCia and  m.movDpPK.codEmp = :codEmp and m.deducPresta.sumaResta = :sumaResta"),
    @NamedQuery(name = "MovDp.findByCodDp", query = "SELECT m FROM MovDp m WHERE m.movDpPK.codDp = :codDp  "),
    @NamedQuery(name = "MovDp.findByValor", query = "SELECT m FROM MovDp m WHERE m.valor = :valor")})
public class MovDp implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovDpPK movDpPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "PENDIENTE")
    private BigDecimal pendiente;    
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
    @Column(name = "GENERADO")
    private String generado;      
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Column(name = "NUM_REF")    
    private String numRef;   
    
    public MovDp() {
    }

    public MovDp(MovDpPK movDpPK) {
	this.movDpPK = movDpPK;
    }

    public String getGenerado() {
        return generado;
    }

    public BigDecimal getPendiente() {
        return pendiente;
    }

    public void setPendiente(BigDecimal pendiente) {
        this.pendiente = pendiente;
    }

    
    public void setGenerado(String generado) {
        this.generado = generado;
    }

    public MovDp(short codCia, long secuencia, int codEmp, short codDp) {
	this.movDpPK = new MovDpPK(codCia, secuencia, codEmp, codDp);
    }

    public MovDpPK getMovDpPK() {
	return movDpPK;
    }

    public void setMovDpPK(MovDpPK movDpPK) {
	this.movDpPK = movDpPK;
    }

    public String getNumRef() {
	return numRef;
    }

    public void setNumRef(String numRef) {
	this.numRef = numRef;
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

    public Date getFechaReg() {
	return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
	this.fechaReg = fechaReg;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (movDpPK != null ? movDpPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof MovDp)) {
	    return false;
	}
	MovDp other = (MovDp) object;
	if ((this.movDpPK == null && other.movDpPK != null) || (this.movDpPK != null && !this.movDpPK.equals(other.movDpPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.MovDp[ movDpPK=" + movDpPK + " ]";
    }
    
}