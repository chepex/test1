/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "DMGPOLIZA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dmgpoliza.findAll", query = "SELECT d FROM Dmgpoliza d"),
    @NamedQuery(name = "Dmgpoliza.findByCodCia", query = "SELECT d FROM Dmgpoliza d WHERE d.dmgpolizaPK.codCia = :codCia"),
    @NamedQuery(name = "Dmgpoliza.findByTipoDocto", query = "SELECT d FROM Dmgpoliza d WHERE d.dmgpolizaPK.tipoDocto = :tipoDocto"),
    @NamedQuery(name = "Dmgpoliza.findByNumPoliza", query = "SELECT d FROM Dmgpoliza d WHERE d.dmgpolizaPK.numPoliza = :numPoliza"),
    @NamedQuery(name = "Dmgpoliza.findByNumReferencia", query = "SELECT d FROM Dmgpoliza d WHERE d.numReferencia = :numReferencia"),
    @NamedQuery(name = "Dmgpoliza.findByFecha", query = "SELECT d FROM Dmgpoliza d WHERE d.dmgpolizaPK.fecha = :fecha"),
    @NamedQuery(name = "Dmgpoliza.findByConcepto", query = "SELECT d FROM Dmgpoliza d WHERE d.concepto = :concepto"),
    @NamedQuery(name = "Dmgpoliza.findByTotalPoliza", query = "SELECT d FROM Dmgpoliza d WHERE d.totalPoliza = :totalPoliza"),
    @NamedQuery(name = "Dmgpoliza.findByStatPoliza", query = "SELECT d FROM Dmgpoliza d WHERE d.statPoliza = :statPoliza"),
    @NamedQuery(name = "Dmgpoliza.findByFechaCambio", query = "SELECT d FROM Dmgpoliza d WHERE d.fechaCambio = :fechaCambio"),
    @NamedQuery(name = "Dmgpoliza.findByUsuario", query = "SELECT d FROM Dmgpoliza d WHERE d.usuario = :usuario"),
    @NamedQuery(name = "Dmgpoliza.findByCodCiaOrigen", query = "SELECT d FROM Dmgpoliza d WHERE d.codCiaOrigen = :codCiaOrigen"),
    @NamedQuery(name = "Dmgpoliza.findByCodRubro", query = "SELECT d FROM Dmgpoliza d WHERE d.codRubro = :codRubro"),
    @NamedQuery(name = "Dmgpoliza.findByFechaInicio", query = "SELECT d FROM Dmgpoliza d WHERE d.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Dmgpoliza.findByFechaFinal", query = "SELECT d FROM Dmgpoliza d WHERE d.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "Dmgpoliza.findByFechaCreacion", query = "SELECT d FROM Dmgpoliza d WHERE d.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Dmgpoliza.findByVistoBueno", query = "SELECT d FROM Dmgpoliza d WHERE d.vistoBueno = :vistoBueno")})
public class Dmgpoliza implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DmgpolizaPK dmgpolizaPK;
    @Size(max = 20)
    @Column(name = "NUM_REFERENCIA")
    private String numReferencia;
    @Size(max = 120)
    @Column(name = "CONCEPTO")
    private String concepto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL_POLIZA")
    private BigDecimal totalPoliza;
    @Size(max = 1)
    @Column(name = "STAT_POLIZA")
    private String statPoliza;
    @Column(name = "FECHA_CAMBIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;
    @Size(max = 30)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "COD_CIA_ORIGEN")
    private Short codCiaOrigen;
    @Column(name = "COD_RUBRO")
    private Short codRubro;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "VISTO_BUENO")
    private BigInteger vistoBueno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmgpoliza")
    private List<Dmgdetalle> dmgdetalleList;

    public Dmgpoliza() {
    }

    public Dmgpoliza(DmgpolizaPK dmgpolizaPK) {
        this.dmgpolizaPK = dmgpolizaPK;
    }

    public Dmgpoliza(short codCia, String tipoDocto, int numPoliza, Date fecha) {
        this.dmgpolizaPK = new DmgpolizaPK(codCia, tipoDocto, numPoliza, fecha);
    }

    public DmgpolizaPK getDmgpolizaPK() {
        return dmgpolizaPK;
    }

    public void setDmgpolizaPK(DmgpolizaPK dmgpolizaPK) {
        this.dmgpolizaPK = dmgpolizaPK;
    }

    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getTotalPoliza() {
        return totalPoliza;
    }

    public void setTotalPoliza(BigDecimal totalPoliza) {
        this.totalPoliza = totalPoliza;
    }

    public String getStatPoliza() {
        return statPoliza;
    }

    public void setStatPoliza(String statPoliza) {
        this.statPoliza = statPoliza;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Short getCodCiaOrigen() {
        return codCiaOrigen;
    }

    public void setCodCiaOrigen(Short codCiaOrigen) {
        this.codCiaOrigen = codCiaOrigen;
    }

    public Short getCodRubro() {
        return codRubro;
    }

    public void setCodRubro(Short codRubro) {
        this.codRubro = codRubro;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getVistoBueno() {
        return vistoBueno;
    }

    public void setVistoBueno(BigInteger vistoBueno) {
        this.vistoBueno = vistoBueno;
    }

    @XmlTransient
    public List<Dmgdetalle> getDmgdetalleList() {
        return dmgdetalleList;
    }

    public void setDmgdetalleList(List<Dmgdetalle> dmgdetalleList) {
        this.dmgdetalleList = dmgdetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmgpolizaPK != null ? dmgpolizaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dmgpoliza)) {
            return false;
        }
        Dmgpoliza other = (Dmgpoliza) object;
        if ((this.dmgpolizaPK == null && other.dmgpolizaPK != null) || (this.dmgpolizaPK != null && !this.dmgpolizaPK.equals(other.dmgpolizaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Dmgpoliza[ dmgpolizaPK=" + dmgpolizaPK + " ]";
    }
    
}
