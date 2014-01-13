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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "DMGDETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dmgdetalle.findAll", query = "SELECT d FROM Dmgdetalle d"),
    @NamedQuery(name = "Dmgdetalle.findByCodCia", query = "SELECT d FROM Dmgdetalle d WHERE d.dmgdetallePK.codCia = :codCia"),
    @NamedQuery(name = "Dmgdetalle.findByPkFecha", query = "SELECT d FROM Dmgdetalle d WHERE d.dmgdetallePK.codCia = :codCia and d.dmgdetallePK.tipoDocto = :tipoDocto and d.dmgdetallePK.fecha = :fecha"),
    @NamedQuery(name = "Dmgdetalle.findByCuenta", query = "SELECT d FROM Dmgdetalle d "
        + "WHERE d.dmgdetallePK.codCia = :codCia"
        + " and d.dmgdetallePK.tipoDocto = :tipoDocto"
        + " and d.dmgdetallePK.numPoliza = :numPoliza"
        + " and d.dmgdetallePK.fecha = :fecha"
        + " and d.proyecto = :proyecto"
        + " and d.cta1 = :cta1"
        + " and d.cta2 = :cta2"
        + " and d.cta3 = :cta3"
        + " and d.cta4 = :cta4"
        + " and d.cta5 = :cta5"
        ),    
    @NamedQuery(name = "Dmgdetalle.findByTipoDocto", query = "SELECT d FROM Dmgdetalle d WHERE d.dmgdetallePK.tipoDocto = :tipoDocto"),
    @NamedQuery(name = "Dmgdetalle.findByNumPoliza", query = "SELECT d FROM Dmgdetalle d WHERE d.dmgdetallePK.numPoliza = :numPoliza"),
    @NamedQuery(name = "Dmgdetalle.findByFecha", query = "SELECT d FROM Dmgdetalle d WHERE d.dmgdetallePK.fecha = :fecha"),
    @NamedQuery(name = "Dmgdetalle.findByCorrelat", query = "SELECT d FROM Dmgdetalle d WHERE d.dmgdetallePK.correlat = :correlat"),
    @NamedQuery(name = "Dmgdetalle.findByCta1", query = "SELECT d FROM Dmgdetalle d WHERE d.cta1 = :cta1"),
    @NamedQuery(name = "Dmgdetalle.findByCta2", query = "SELECT d FROM Dmgdetalle d WHERE d.cta2 = :cta2"),
    @NamedQuery(name = "Dmgdetalle.findByCta3", query = "SELECT d FROM Dmgdetalle d WHERE d.cta3 = :cta3"),
    @NamedQuery(name = "Dmgdetalle.findByCta4", query = "SELECT d FROM Dmgdetalle d WHERE d.cta4 = :cta4"),
    @NamedQuery(name = "Dmgdetalle.findByCta5", query = "SELECT d FROM Dmgdetalle d WHERE d.cta5 = :cta5"),
    @NamedQuery(name = "Dmgdetalle.findByConcepto", query = "SELECT d FROM Dmgdetalle d WHERE d.concepto = :concepto"),
    @NamedQuery(name = "Dmgdetalle.findByCargo", query = "SELECT d FROM Dmgdetalle d WHERE d.cargo = :cargo"),
    @NamedQuery(name = "Dmgdetalle.findByAbono", query = "SELECT d FROM Dmgdetalle d WHERE d.abono = :abono"),
    @NamedQuery(name = "Dmgdetalle.findByTipoActiv", query = "SELECT d FROM Dmgdetalle d WHERE d.tipoActiv = :tipoActiv"),
    @NamedQuery(name = "Dmgdetalle.findBySubActiv", query = "SELECT d FROM Dmgdetalle d WHERE d.subActiv = :subActiv"),
    @NamedQuery(name = "Dmgdetalle.findByReferencia", query = "SELECT d FROM Dmgdetalle d WHERE d.referencia = :referencia"),
    @NamedQuery(name = "Dmgdetalle.findByProyecto", query = "SELECT d FROM Dmgdetalle d WHERE d.proyecto = :proyecto"),
    @NamedQuery(name = "Dmgdetalle.findByCodDepto", query = "SELECT d FROM Dmgdetalle d WHERE d.codDepto = :codDepto"),
    @NamedQuery(name = "Dmgdetalle.findByFechaInicio", query = "SELECT d FROM Dmgdetalle d WHERE d.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Dmgdetalle.findByFechaFinal", query = "SELECT d FROM Dmgdetalle d WHERE d.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "Dmgdetalle.findByCodRubro", query = "SELECT d FROM Dmgdetalle d WHERE d.codRubro = :codRubro")})
public class Dmgdetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DmgdetallePK dmgdetallePK;
    @Column(name = "CTA_1")
    private Short cta1;
    @Column(name = "CTA_2")
    private Short cta2;
    @Column(name = "CTA_3")
    private Short cta3;
    @Column(name = "CTA_4")
    private Short cta4;
    @Column(name = "CTA_5")
    private Short cta5;
    @Size(max = 300)
    @Column(name = "CONCEPTO")
    private String concepto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CARGO")
    private BigDecimal cargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ABONO")
    private BigDecimal abono;
    @Size(max = 2)
    @Column(name = "TIPO_ACTIV")
    private String tipoActiv;
    @Column(name = "SUB_ACTIV")
    private Integer subActiv;
    @Size(max = 15)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Size(max = 20)
    @Column(name = "PROYECTO")
    private String proyecto;
    @Column(name = "COD_DEPTO")
    private Short codDepto;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;
    @Column(name = "COD_RUBRO")
    private Short codRubro;
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "TIPO_DOCTO", referencedColumnName = "TIPO_DOCTO", insertable = false, updatable = false),
        @JoinColumn(name = "NUM_POLIZA", referencedColumnName = "NUM_POLIZA", insertable = false, updatable = false),
        @JoinColumn(name = "FECHA", referencedColumnName = "FECHA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Dmgpoliza dmgpoliza;

    public Dmgdetalle() {
    }

    public Dmgdetalle(DmgdetallePK dmgdetallePK) {
        this.dmgdetallePK = dmgdetallePK;
    }

    public Dmgdetalle(DmgdetallePK dmgdetallePK, BigDecimal cargo, BigDecimal abono) {
        this.dmgdetallePK = dmgdetallePK;
        this.cargo = cargo;
        this.abono = abono;
    }

    public Dmgdetalle(short codCia, String tipoDocto, int numPoliza, Date fecha, int correlat) {
        this.dmgdetallePK = new DmgdetallePK(codCia, tipoDocto, numPoliza, fecha, correlat);
    }

    public DmgdetallePK getDmgdetallePK() {
        return dmgdetallePK;
    }

    public void setDmgdetallePK(DmgdetallePK dmgdetallePK) {
        this.dmgdetallePK = dmgdetallePK;
    }

    public Short getCta1() {
        return cta1;
    }

    public void setCta1(Short cta1) {
        this.cta1 = cta1;
    }

    public Short getCta2() {
        return cta2;
    }

    public void setCta2(Short cta2) {
        this.cta2 = cta2;
    }

    public Short getCta3() {
        return cta3;
    }

    public void setCta3(Short cta3) {
        this.cta3 = cta3;
    }

    public Short getCta4() {
        return cta4;
    }

    public void setCta4(Short cta4) {
        this.cta4 = cta4;
    }

    public Short getCta5() {
        return cta5;
    }

    public void setCta5(Short cta5) {
        this.cta5 = cta5;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getCargo() {
        return cargo;
    }

    public void setCargo(BigDecimal cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getAbono() {
        return abono;
    }

    public void setAbono(BigDecimal abono) {
        this.abono = abono;
    }

    public String getTipoActiv() {
        return tipoActiv;
    }

    public void setTipoActiv(String tipoActiv) {
        this.tipoActiv = tipoActiv;
    }

    public Integer getSubActiv() {
        return subActiv;
    }

    public void setSubActiv(Integer subActiv) {
        this.subActiv = subActiv;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public Short getCodDepto() {
        return codDepto;
    }

    public void setCodDepto(Short codDepto) {
        this.codDepto = codDepto;
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

    public Short getCodRubro() {
        return codRubro;
    }

    public void setCodRubro(Short codRubro) {
        this.codRubro = codRubro;
    }

    public Dmgpoliza getDmgpoliza() {
        return dmgpoliza;
    }

    public void setDmgpoliza(Dmgpoliza dmgpoliza) {
        this.dmgpoliza = dmgpoliza;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmgdetallePK != null ? dmgdetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dmgdetalle)) {
            return false;
        }
        Dmgdetalle other = (Dmgdetalle) object;
        if ((this.dmgdetallePK == null && other.dmgdetallePK != null) || (this.dmgdetallePK != null && !this.dmgdetallePK.equals(other.dmgdetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Dmgdetalle[ dmgdetallePK=" + dmgdetallePK + " ]";
    }
    
}
