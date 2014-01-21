/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "TIPOS_PLANILLA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposPlanilla.findAll", query = "SELECT t FROM TiposPlanilla t"),
    @NamedQuery(name = "TiposPlanilla.findByCodCia", query = "SELECT t FROM TiposPlanilla t WHERE t.tiposPlanillaPK.codCia = :codCia"),
    @NamedQuery(name = "TiposPlanilla.findByCodTipopla", query = "SELECT t FROM TiposPlanilla t WHERE t.tiposPlanillaPK.codTipopla = :codTipopla"),
    @NamedQuery(name = "TiposPlanilla.findByNomTipopla", query = "SELECT t FROM TiposPlanilla t WHERE t.nomTipopla = :nomTipopla"),
    @NamedQuery(name = "TiposPlanilla.findByStatus", query = "SELECT t FROM TiposPlanilla t WHERE t.status = :status")})
public class TiposPlanilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TiposPlanillaPK tiposPlanillaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NOM_TIPOPLA")
    private String nomTipopla;
    @Column(name = "COD_DP")
    private short codDp;    
    @Size(max = 1)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 1)
    @Basic(optional = false)
    @NotNull    
    @Column(name = "LEY")
    private String ley;
    @Size(max = 1)
    @Basic(optional = false)
    @NotNull    
    @Column(name = "PROMEDIO")
    private String promedio;
    @Size(max = 1)
    @Basic(optional = false)
    @NotNull    
    @Column(name = "Frecuencia")
    private String frecuencia;    
    @Size(max = 1)
    @Basic(optional = false)
    @NotNull    
    @Column(name = "PRESTAMOS")
    private String prestamos;
    @Size(max = 1)
    @Basic(optional = false)
    @NotNull    
    @Column(name = "LIQUIDO")
    private String liquido;
    @Size(max = 1)
    @Basic(optional = false)
    @NotNull    
    @Column(name = "ADICIONAL")
    private String adicional;  
  
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiposPlanilla")
    private List<ProgramacionPla> programacionPlaList;
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
    
    public TiposPlanilla() {
    }

    public String getFrecuencia() {
        return frecuencia;
    }



    
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public DeducPresta getDeducPresta() {
        return deducPresta;
    }

    public void setDeducPresta(DeducPresta deducPresta) {
        this.deducPresta = deducPresta;
    }

    public short getCodDp() {
        return codDp;
    }

    public void setCodDp(short codDp) {
        this.codDp = codDp;
    }

    public String getLey() {
        return ley;
    }

    public void setLey(String ley) {
        this.ley = ley;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(String prestamos) {
        this.prestamos = prestamos;
    }

    public String getLiquido() {
        return liquido;
    }

    public void setLiquido(String liquido) {
        this.liquido = liquido;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
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


    public TiposPlanilla(TiposPlanillaPK tiposPlanillaPK) {
	this.tiposPlanillaPK = tiposPlanillaPK;
    }

    public TiposPlanilla(TiposPlanillaPK tiposPlanillaPK, String nomTipopla) {
	this.tiposPlanillaPK = tiposPlanillaPK;
	this.nomTipopla = nomTipopla;
    }

    public TiposPlanilla(short codCia, short codTipopla) {
	this.tiposPlanillaPK = new TiposPlanillaPK(codCia, codTipopla);
    }

    public TiposPlanillaPK getTiposPlanillaPK() {
	return tiposPlanillaPK;
    }

    public void setTiposPlanillaPK(TiposPlanillaPK tiposPlanillaPK) {
	this.tiposPlanillaPK = tiposPlanillaPK;
    }

    public String getNomTipopla() {
	return nomTipopla;
    }

    public void setNomTipopla(String nomTipopla) {
	this.nomTipopla = nomTipopla;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @XmlTransient
    public List<ProgramacionPla> getProgramacionPlaList() {
	return programacionPlaList;
    }

    public void setProgramacionPlaList(List<ProgramacionPla> programacionPlaList) {
	this.programacionPlaList = programacionPlaList;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (tiposPlanillaPK != null ? tiposPlanillaPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof TiposPlanilla)) {
	    return false;
	}
	TiposPlanilla other = (TiposPlanilla) object;
	if ((this.tiposPlanillaPK == null && other.tiposPlanillaPK != null) || (this.tiposPlanillaPK != null && !this.tiposPlanillaPK.equals(other.tiposPlanillaPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.TiposPlanilla[ tiposPlanillaPK=" + tiposPlanillaPK + " ]";
    }
    
}
