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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "RENTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Renta.findAll", query = "SELECT r FROM Renta r"),
    @NamedQuery(name = "Renta.findByCodCia", query = "SELECT r FROM Renta r WHERE r.rentaPK.codCia = :codCia"),
    @NamedQuery(name = "Renta.findByDevengado", query = "SELECT r FROM Renta r WHERE r.rentaPK.codCia = :codCia and r.rentaPK.id= :id and :devengado between r.del and r.al"),
    @NamedQuery(name = "Renta.findById", query = "SELECT r FROM Renta r WHERE r.rentaPK.id = :id"),
    @NamedQuery(name = "Renta.findBySecuencia", query = "SELECT r FROM Renta r WHERE r.rentaPK.secuencia = :secuencia"),
    @NamedQuery(name = "Renta.findByDel", query = "SELECT r FROM Renta r WHERE r.del = :del"),
    @NamedQuery(name = "Renta.findByAl", query = "SELECT r FROM Renta r WHERE r.al = :al"),
    @NamedQuery(name = "Renta.findByValorFijo", query = "SELECT r FROM Renta r WHERE r.valorFijo = :valorFijo"),
    @NamedQuery(name = "Renta.findByPorcentaje", query = "SELECT r FROM Renta r WHERE r.porcentaje = :porcentaje"),
    @NamedQuery(name = "Renta.findByExceso", query = "SELECT r FROM Renta r WHERE r.exceso = :exceso")})
public class Renta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RentaPK rentaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEL")
    private BigDecimal del;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AL")
    private BigDecimal al;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_FIJO")
    private BigDecimal valorFijo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE")
    private BigDecimal porcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXCESO")
    private BigDecimal exceso;
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;    
    public Renta() {
    }

    public Renta(RentaPK rentaPK) {
        this.rentaPK = rentaPK;
    }

    public Renta(RentaPK rentaPK, BigDecimal del, BigDecimal al, BigDecimal valorFijo, BigDecimal porcentaje, BigDecimal exceso) {
        this.rentaPK = rentaPK;
        this.del = del;
        this.al = al;
        this.valorFijo = valorFijo;
        this.porcentaje = porcentaje;
        this.exceso = exceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }

    public Renta(short codCia, short id, short secuencia) {
        this.rentaPK = new RentaPK(codCia, id, secuencia);
    }

    public RentaPK getRentaPK() {
        return rentaPK;
    }

    public void setRentaPK(RentaPK rentaPK) {
        this.rentaPK = rentaPK;
    }

    public BigDecimal getDel() {
        return del;
    }

    public void setDel(BigDecimal del) {
        this.del = del;
    }

    public BigDecimal getAl() {
        return al;
    }

    public void setAl(BigDecimal al) {
        this.al = al;
    }

    public BigDecimal getValorFijo() {
        return valorFijo;
    }

    public void setValorFijo(BigDecimal valorFijo) {
        this.valorFijo = valorFijo;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getExceso() {
        return exceso;
    }

    public void setExceso(BigDecimal exceso) {
        this.exceso = exceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentaPK != null ? rentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Renta)) {
            return false;
        }
        Renta other = (Renta) object;
        if ((this.rentaPK == null && other.rentaPK != null) || (this.rentaPK != null && !this.rentaPK.equals(other.rentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Renta[ rentaPK=" + rentaPK + " ]";
    }
    
}
