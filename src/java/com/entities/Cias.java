/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "CIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cias.findAll", query = "SELECT c FROM Cias c"),
    @NamedQuery(name = "Cias.findByCodCia", query = "SELECT c FROM Cias c WHERE c.codCia = :codCia"),
    @NamedQuery(name = "Cias.findByRazonSocial", query = "SELECT c FROM Cias c WHERE c.razonSocial = :razonSocial"),
    @NamedQuery(name = "Cias.findByNomComercial", query = "SELECT c FROM Cias c WHERE c.nomComercial = :nomComercial"),
    @NamedQuery(name = "Cias.findByDirecEmpresa", query = "SELECT c FROM Cias c WHERE c.direcEmpresa = :direcEmpresa"),
    @NamedQuery(name = "Cias.findByTelefEmpresa", query = "SELECT c FROM Cias c WHERE c.telefEmpresa = :telefEmpresa"),
    @NamedQuery(name = "Cias.findByNitEmpresa", query = "SELECT c FROM Cias c WHERE c.nitEmpresa = :nitEmpresa"),
    @NamedQuery(name = "Cias.findByNumeroPatronal", query = "SELECT c FROM Cias c WHERE c.numeroPatronal = :numeroPatronal"),
    @NamedQuery(name = "Cias.findByMesCierre", query = "SELECT c FROM Cias c WHERE c.mesCierre = :mesCierre"),
    @NamedQuery(name = "Cias.findByMesProceso", query = "SELECT c FROM Cias c WHERE c.mesProceso = :mesProceso"),
    @NamedQuery(name = "Cias.findByCta1resulAct", query = "SELECT c FROM Cias c WHERE c.cta1resulAct = :cta1resulAct"),
    @NamedQuery(name = "Cias.findByCta2resulAct", query = "SELECT c FROM Cias c WHERE c.cta2resulAct = :cta2resulAct"),
    @NamedQuery(name = "Cias.findByCta3resulAct", query = "SELECT c FROM Cias c WHERE c.cta3resulAct = :cta3resulAct"),
    @NamedQuery(name = "Cias.findByCta4resulAct", query = "SELECT c FROM Cias c WHERE c.cta4resulAct = :cta4resulAct"),
    @NamedQuery(name = "Cias.findByCta5resulAct", query = "SELECT c FROM Cias c WHERE c.cta5resulAct = :cta5resulAct"),
    @NamedQuery(name = "Cias.findByCta1resulAnt", query = "SELECT c FROM Cias c WHERE c.cta1resulAnt = :cta1resulAnt"),
    @NamedQuery(name = "Cias.findByCta2resulAnt", query = "SELECT c FROM Cias c WHERE c.cta2resulAnt = :cta2resulAnt"),
    @NamedQuery(name = "Cias.findByCta3resulAnt", query = "SELECT c FROM Cias c WHERE c.cta3resulAnt = :cta3resulAnt"),
    @NamedQuery(name = "Cias.findByCta4resulAnt", query = "SELECT c FROM Cias c WHERE c.cta4resulAnt = :cta4resulAnt"),
    @NamedQuery(name = "Cias.findByCta5resulAnt", query = "SELECT c FROM Cias c WHERE c.cta5resulAnt = :cta5resulAnt"),
    @NamedQuery(name = "Cias.findByCta1perGan", query = "SELECT c FROM Cias c WHERE c.cta1perGan = :cta1perGan"),
    @NamedQuery(name = "Cias.findByCta2perGan", query = "SELECT c FROM Cias c WHERE c.cta2perGan = :cta2perGan"),
    @NamedQuery(name = "Cias.findByCta3perGan", query = "SELECT c FROM Cias c WHERE c.cta3perGan = :cta3perGan"),
    @NamedQuery(name = "Cias.findByCta4perGan", query = "SELECT c FROM Cias c WHERE c.cta4perGan = :cta4perGan"),
    @NamedQuery(name = "Cias.findByCta5perGan", query = "SELECT c FROM Cias c WHERE c.cta5perGan = :cta5perGan"),
    @NamedQuery(name = "Cias.findByFechUlt", query = "SELECT c FROM Cias c WHERE c.fechUlt = :fechUlt"),
    @NamedQuery(name = "Cias.findByFecUltCie", query = "SELECT c FROM Cias c WHERE c.fecUltCie = :fecUltCie"),
    @NamedQuery(name = "Cias.findByTasaIva", query = "SELECT c FROM Cias c WHERE c.tasaIva = :tasaIva"),
    @NamedQuery(name = "Cias.findByTipoNumeracion", query = "SELECT c FROM Cias c WHERE c.tipoNumeracion = :tipoNumeracion"),
    @NamedQuery(name = "Cias.findBySiReferencia", query = "SELECT c FROM Cias c WHERE c.siReferencia = :siReferencia"),
    @NamedQuery(name = "Cias.findByRegistoIva", query = "SELECT c FROM Cias c WHERE c.registoIva = :registoIva"),
    @NamedQuery(name = "Cias.findByGiro", query = "SELECT c FROM Cias c WHERE c.giro = :giro"),
    @NamedQuery(name = "Cias.findByUtilizaProyecto", query = "SELECT c FROM Cias c WHERE c.utilizaProyecto = :utilizaProyecto"),
    @NamedQuery(name = "Cias.findByEnCierre", query = "SELECT c FROM Cias c WHERE c.enCierre = :enCierre"),
    @NamedQuery(name = "Cias.findByAnoProceso", query = "SELECT c FROM Cias c WHERE c.anoProceso = :anoProceso"),
    @NamedQuery(name = "Cias.findByCodPais", query = "SELECT c FROM Cias c WHERE c.codPais = :codPais"),
    @NamedQuery(name = "Cias.findByMonedaBase", query = "SELECT c FROM Cias c WHERE c.monedaBase = :monedaBase"),
    @NamedQuery(name = "Cias.findByPathLogo", query = "SELECT c FROM Cias c WHERE c.pathLogo = :pathLogo"),
    @NamedQuery(name = "Cias.findByTelfPbx", query = "SELECT c FROM Cias c WHERE c.telfPbx = :telfPbx"),
    @NamedQuery(name = "Cias.findByCiudad", query = "SELECT c FROM Cias c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "Cias.findByValorOrdenes", query = "SELECT c FROM Cias c WHERE c.valorOrdenes = :valorOrdenes"),
    @NamedQuery(name = "Cias.findByDireccionCia", query = "SELECT c FROM Cias c WHERE c.direccionCia = :direccionCia"),
    @NamedQuery(name = "Cias.findByTelefCobros", query = "SELECT c FROM Cias c WHERE c.telefCobros = :telefCobros"),
    @NamedQuery(name = "Cias.findByFirmaCobros", query = "SELECT c FROM Cias c WHERE c.firmaCobros = :firmaCobros"),
    @NamedQuery(name = "Cias.findByCargoFirmaCobros", query = "SELECT c FROM Cias c WHERE c.cargoFirmaCobros = :cargoFirmaCobros"),
    @NamedQuery(name = "Cias.findByUtilizaNumserie", query = "SELECT c FROM Cias c WHERE c.utilizaNumserie = :utilizaNumserie"),
    @NamedQuery(name = "Cias.findByRequiereBodega", query = "SELECT c FROM Cias c WHERE c.requiereBodega = :requiereBodega"),
    @NamedQuery(name = "Cias.findByTipoDoctoDefault", query = "SELECT c FROM Cias c WHERE c.tipoDoctoDefault = :tipoDoctoDefault"),
    @NamedQuery(name = "Cias.findByTipoDoctoSecundario", query = "SELECT c FROM Cias c WHERE c.tipoDoctoSecundario = :tipoDoctoSecundario"),
    @NamedQuery(name = "Cias.findByCiaLocal", query = "SELECT c FROM Cias c WHERE c.ciaLocal = :ciaLocal"),
    @NamedQuery(name = "Cias.findByBodegaOrigen", query = "SELECT c FROM Cias c WHERE c.bodegaOrigen = :bodegaOrigen"),
    @NamedQuery(name = "Cias.findByTelefAuditoria", query = "SELECT c FROM Cias c WHERE c.telefAuditoria = :telefAuditoria"),
    @NamedQuery(name = "Cias.findByTasaCambio", query = "SELECT c FROM Cias c WHERE c.tasaCambio = :tasaCambio"),
    @NamedQuery(name = "Cias.findBySueldoXDia", query = "SELECT c FROM Cias c WHERE c.sueldoXDia = :sueldoXDia"),
    @NamedQuery(name = "Cias.findByDominio", query = "SELECT c FROM Cias c WHERE c.dominio = :dominio"),
    @NamedQuery(name = "Cias.findByTasaPercepcion", query = "SELECT c FROM Cias c WHERE c.tasaPercepcion = :tasaPercepcion"),
    @NamedQuery(name = "Cias.findByMontoPercepcion", query = "SELECT c FROM Cias c WHERE c.montoPercepcion = :montoPercepcion"),
    @NamedQuery(name = "Cias.findByDiasAcumularPercepcion", query = "SELECT c FROM Cias c WHERE c.diasAcumularPercepcion = :diasAcumularPercepcion"),
    @NamedQuery(name = "Cias.findByTasaRetencion", query = "SELECT c FROM Cias c WHERE c.tasaRetencion = :tasaRetencion"),
    @NamedQuery(name = "Cias.findByMontoRetencion", query = "SELECT c FROM Cias c WHERE c.montoRetencion = :montoRetencion")})
public class Cias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CIA")
    private Short codCia;
    @Size(max = 60)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Size(max = 60)
    @Column(name = "NOM_COMERCIAL")
    private String nomComercial;
    @Size(max = 100)
    @Column(name = "DIREC_EMPRESA")
    private String direcEmpresa;
    @Size(max = 50)
    @Column(name = "TELEF_EMPRESA")
    private String telefEmpresa;
    @Size(max = 17)
    @Column(name = "NIT_EMPRESA")
    private String nitEmpresa;
    @Size(max = 15)
    @Column(name = "NUMERO_PATRONAL")
    private String numeroPatronal;
    @Column(name = "MES_CIERRE")
    private Short mesCierre;
    @Column(name = "MES_PROCESO")
    private Short mesProceso;
    @Column(name = "CTA_1RESUL_ACT")
    private Short cta1resulAct;
    @Column(name = "CTA_2RESUL_ACT")
    private Short cta2resulAct;
    @Column(name = "CTA_3RESUL_ACT")
    private Short cta3resulAct;
    @Column(name = "CTA_4RESUL_ACT")
    private Short cta4resulAct;
    @Column(name = "CTA_5RESUL_ACT")
    private Short cta5resulAct;
    @Column(name = "CTA_1RESUL_ANT")
    private Short cta1resulAnt;
    @Column(name = "CTA_2RESUL_ANT")
    private Short cta2resulAnt;
    @Column(name = "CTA_3RESUL_ANT")
    private Short cta3resulAnt;
    @Column(name = "CTA_4RESUL_ANT")
    private Short cta4resulAnt;
    @Column(name = "CTA_5RESUL_ANT")
    private Short cta5resulAnt;
    @Column(name = "CTA_1PER_GAN")
    private Short cta1perGan;
    @Column(name = "CTA_2PER_GAN")
    private Short cta2perGan;
    @Column(name = "CTA_3PER_GAN")
    private Short cta3perGan;
    @Column(name = "CTA_4PER_GAN")
    private Short cta4perGan;
    @Column(name = "CTA_5PER_GAN")
    private Short cta5perGan;
    @Column(name = "FECH_ULT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechUlt;
    @Column(name = "FEC_ULT_CIE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecUltCie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TASA_IVA")
    private BigDecimal tasaIva;
    @Column(name = "TIPO_NUMERACION")
    private Short tipoNumeracion;
    @Size(max = 1)
    @Column(name = "SI_REFERENCIA")
    private String siReferencia;
    @Size(max = 15)
    @Column(name = "REGISTO_IVA")
    private String registoIva;
    @Size(max = 35)
    @Column(name = "GIRO")
    private String giro;
    @Size(max = 1)
    @Column(name = "UTILIZA_PROYECTO")
    private String utilizaProyecto;
    @Size(max = 1)
    @Column(name = "EN_CIERRE")
    private String enCierre;
    @Column(name = "ANO_PROCESO")
    private Short anoProceso;
    @Column(name = "COD_PAIS")
    private Short codPais;
    @Column(name = "MONEDA_BASE")
    private Short monedaBase;
    @Size(max = 150)
    @Column(name = "PATH_LOGO")
    private String pathLogo;
    @Size(max = 30)
    @Column(name = "TELF_PBX")
    private String telfPbx;
    @Size(max = 50)
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "VALOR_ORDENES")
    private BigDecimal valorOrdenes;
    @Size(max = 100)
    @Column(name = "DIRECCION_CIA")
    private String direccionCia;
    @Size(max = 50)
    @Column(name = "TELEF_COBROS")
    private String telefCobros;
    @Size(max = 50)
    @Column(name = "FIRMA_COBROS")
    private String firmaCobros;
    @Size(max = 50)
    @Column(name = "CARGO_FIRMA_COBROS")
    private String cargoFirmaCobros;
    @Size(max = 1)
    @Column(name = "UTILIZA_NUMSERIE")
    private String utilizaNumserie;
    @Size(max = 1)
    @Column(name = "REQUIERE_BODEGA")
    private String requiereBodega;
    @Column(name = "TIPO_DOCTO_DEFAULT")
    private Short tipoDoctoDefault;
    @Column(name = "TIPO_DOCTO_SECUNDARIO")
    private Short tipoDoctoSecundario;
    @Size(max = 1)
    @Column(name = "CIA_LOCAL")
    private String ciaLocal;
    @Column(name = "BODEGA_ORIGEN")
    private Long bodegaOrigen;
    @Size(max = 50)
    @Column(name = "TELEF_AUDITORIA")
    private String telefAuditoria;
    @Column(name = "TASA_CAMBIO")
    private BigDecimal tasaCambio;
    @Size(max = 1)
    @Column(name = "SUELDO_X_DIA")
    private String sueldoXDia;
    @Size(max = 10)
    @Column(name = "DOMINIO")
    private String dominio;
    @Column(name = "TASA_PERCEPCION")
    private BigDecimal tasaPercepcion;
    @Column(name = "MONTO_PERCEPCION")
    private BigDecimal montoPercepcion;
    @Column(name = "DIAS_ACUMULAR_PERCEPCION")
    private Short diasAcumularPercepcion;
    @Column(name = "TASA_RETENCION")
    private BigDecimal tasaRetencion;
    @Column(name = "MONTO_RETENCION")
    private BigDecimal montoRetencion;
    @OneToMany(mappedBy = "codCiaMatriz")
    private List<Cias> ciasList;
    @JoinColumn(name = "COD_CIA_MATRIZ", referencedColumnName = "COD_CIA")
    @ManyToOne
    private Cias codCiaMatriz;
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    public Cias() {
    }

    public Cias(Short codCia) {
	this.codCia = codCia;
    }

    public Short getCodCia() {
	return codCia;
    }

    public void setCodCia(Short codCia) {
	this.codCia = codCia;
    }

    public String getRazonSocial() {
	return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
    }

    public String getNomComercial() {
	return nomComercial;
    }

    public void setNomComercial(String nomComercial) {
	this.nomComercial = nomComercial;
    }

    public String getDirecEmpresa() {
	return direcEmpresa;
    }

    public void setDirecEmpresa(String direcEmpresa) {
	this.direcEmpresa = direcEmpresa;
    }

    public String getTelefEmpresa() {
	return telefEmpresa;
    }

    public void setTelefEmpresa(String telefEmpresa) {
	this.telefEmpresa = telefEmpresa;
    }

    public String getNitEmpresa() {
	return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
	this.nitEmpresa = nitEmpresa;
    }

    public String getNumeroPatronal() {
	return numeroPatronal;
    }

    public void setNumeroPatronal(String numeroPatronal) {
	this.numeroPatronal = numeroPatronal;
    }

    public Short getMesCierre() {
	return mesCierre;
    }

    public void setMesCierre(Short mesCierre) {
	this.mesCierre = mesCierre;
    }

    public Short getMesProceso() {
	return mesProceso;
    }

    public void setMesProceso(Short mesProceso) {
	this.mesProceso = mesProceso;
    }

    public Short getCta1resulAct() {
	return cta1resulAct;
    }

    public void setCta1resulAct(Short cta1resulAct) {
	this.cta1resulAct = cta1resulAct;
    }

    public Short getCta2resulAct() {
	return cta2resulAct;
    }

    public void setCta2resulAct(Short cta2resulAct) {
	this.cta2resulAct = cta2resulAct;
    }

    public Short getCta3resulAct() {
	return cta3resulAct;
    }

    public void setCta3resulAct(Short cta3resulAct) {
	this.cta3resulAct = cta3resulAct;
    }

    public Short getCta4resulAct() {
	return cta4resulAct;
    }

    public void setCta4resulAct(Short cta4resulAct) {
	this.cta4resulAct = cta4resulAct;
    }

    public Short getCta5resulAct() {
	return cta5resulAct;
    }

    public void setCta5resulAct(Short cta5resulAct) {
	this.cta5resulAct = cta5resulAct;
    }

    public Short getCta1resulAnt() {
	return cta1resulAnt;
    }

    public void setCta1resulAnt(Short cta1resulAnt) {
	this.cta1resulAnt = cta1resulAnt;
    }

    public Short getCta2resulAnt() {
	return cta2resulAnt;
    }

    public void setCta2resulAnt(Short cta2resulAnt) {
	this.cta2resulAnt = cta2resulAnt;
    }

    public Short getCta3resulAnt() {
	return cta3resulAnt;
    }

    public void setCta3resulAnt(Short cta3resulAnt) {
	this.cta3resulAnt = cta3resulAnt;
    }

    public Short getCta4resulAnt() {
	return cta4resulAnt;
    }

    public void setCta4resulAnt(Short cta4resulAnt) {
	this.cta4resulAnt = cta4resulAnt;
    }

    public Short getCta5resulAnt() {
	return cta5resulAnt;
    }

    public void setCta5resulAnt(Short cta5resulAnt) {
	this.cta5resulAnt = cta5resulAnt;
    }

    public Short getCta1perGan() {
	return cta1perGan;
    }

    public void setCta1perGan(Short cta1perGan) {
	this.cta1perGan = cta1perGan;
    }

    public Short getCta2perGan() {
	return cta2perGan;
    }

    public void setCta2perGan(Short cta2perGan) {
	this.cta2perGan = cta2perGan;
    }

    public Short getCta3perGan() {
	return cta3perGan;
    }

    public void setCta3perGan(Short cta3perGan) {
	this.cta3perGan = cta3perGan;
    }

    public Short getCta4perGan() {
	return cta4perGan;
    }

    public void setCta4perGan(Short cta4perGan) {
	this.cta4perGan = cta4perGan;
    }

    public Short getCta5perGan() {
	return cta5perGan;
    }

    public void setCta5perGan(Short cta5perGan) {
	this.cta5perGan = cta5perGan;
    }

    public Date getFechUlt() {
	return fechUlt;
    }

    public void setFechUlt(Date fechUlt) {
	this.fechUlt = fechUlt;
    }

    public Date getFecUltCie() {
	return fecUltCie;
    }

    public void setFecUltCie(Date fecUltCie) {
	this.fecUltCie = fecUltCie;
    }

    public BigDecimal getTasaIva() {
	return tasaIva;
    }

    public void setTasaIva(BigDecimal tasaIva) {
	this.tasaIva = tasaIva;
    }

    public Short getTipoNumeracion() {
	return tipoNumeracion;
    }

    public void setTipoNumeracion(Short tipoNumeracion) {
	this.tipoNumeracion = tipoNumeracion;
    }

    public String getSiReferencia() {
	return siReferencia;
    }

    public void setSiReferencia(String siReferencia) {
	this.siReferencia = siReferencia;
    }

    public String getRegistoIva() {
	return registoIva;
    }

    public void setRegistoIva(String registoIva) {
	this.registoIva = registoIva;
    }

    public String getGiro() {
	return giro;
    }

    public void setGiro(String giro) {
	this.giro = giro;
    }

    public String getUtilizaProyecto() {
	return utilizaProyecto;
    }

    public void setUtilizaProyecto(String utilizaProyecto) {
	this.utilizaProyecto = utilizaProyecto;
    }

    public String getEnCierre() {
	return enCierre;
    }

    public void setEnCierre(String enCierre) {
	this.enCierre = enCierre;
    }

    public Short getAnoProceso() {
	return anoProceso;
    }

    public void setAnoProceso(Short anoProceso) {
	this.anoProceso = anoProceso;
    }

    public Short getCodPais() {
	return codPais;
    }

    public void setCodPais(Short codPais) {
	this.codPais = codPais;
    }

    public Short getMonedaBase() {
	return monedaBase;
    }

    public void setMonedaBase(Short monedaBase) {
	this.monedaBase = monedaBase;
    }

    public String getPathLogo() {
	return pathLogo;
    }

    public void setPathLogo(String pathLogo) {
	this.pathLogo = pathLogo;
    }

    public String getTelfPbx() {
	return telfPbx;
    }

    public void setTelfPbx(String telfPbx) {
	this.telfPbx = telfPbx;
    }

    public String getCiudad() {
	return ciudad;
    }

    public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
    }

    public BigDecimal getValorOrdenes() {
	return valorOrdenes;
    }

    public void setValorOrdenes(BigDecimal valorOrdenes) {
	this.valorOrdenes = valorOrdenes;
    }

    public String getDireccionCia() {
	return direccionCia;
    }

    public void setDireccionCia(String direccionCia) {
	this.direccionCia = direccionCia;
    }

    public String getTelefCobros() {
	return telefCobros;
    }

    public void setTelefCobros(String telefCobros) {
	this.telefCobros = telefCobros;
    }

    public String getFirmaCobros() {
	return firmaCobros;
    }

    public void setFirmaCobros(String firmaCobros) {
	this.firmaCobros = firmaCobros;
    }

    public String getCargoFirmaCobros() {
	return cargoFirmaCobros;
    }

    public void setCargoFirmaCobros(String cargoFirmaCobros) {
	this.cargoFirmaCobros = cargoFirmaCobros;
    }

    public String getUtilizaNumserie() {
	return utilizaNumserie;
    }

    public void setUtilizaNumserie(String utilizaNumserie) {
	this.utilizaNumserie = utilizaNumserie;
    }

    public String getRequiereBodega() {
	return requiereBodega;
    }

    public void setRequiereBodega(String requiereBodega) {
	this.requiereBodega = requiereBodega;
    }

    public Short getTipoDoctoDefault() {
	return tipoDoctoDefault;
    }

    public void setTipoDoctoDefault(Short tipoDoctoDefault) {
	this.tipoDoctoDefault = tipoDoctoDefault;
    }

    public Short getTipoDoctoSecundario() {
	return tipoDoctoSecundario;
    }

    public void setTipoDoctoSecundario(Short tipoDoctoSecundario) {
	this.tipoDoctoSecundario = tipoDoctoSecundario;
    }

    public String getCiaLocal() {
	return ciaLocal;
    }

    public void setCiaLocal(String ciaLocal) {
	this.ciaLocal = ciaLocal;
    }

    public Long getBodegaOrigen() {
	return bodegaOrigen;
    }

    public void setBodegaOrigen(Long bodegaOrigen) {
	this.bodegaOrigen = bodegaOrigen;
    }

    public String getTelefAuditoria() {
	return telefAuditoria;
    }

    public void setTelefAuditoria(String telefAuditoria) {
	this.telefAuditoria = telefAuditoria;
    }

    public BigDecimal getTasaCambio() {
	return tasaCambio;
    }

    public void setTasaCambio(BigDecimal tasaCambio) {
	this.tasaCambio = tasaCambio;
    }

    public String getSueldoXDia() {
	return sueldoXDia;
    }

    public void setSueldoXDia(String sueldoXDia) {
	this.sueldoXDia = sueldoXDia;
    }

    public String getDominio() {
	return dominio;
    }

    public void setDominio(String dominio) {
	this.dominio = dominio;
    }

    public BigDecimal getTasaPercepcion() {
	return tasaPercepcion;
    }

    public void setTasaPercepcion(BigDecimal tasaPercepcion) {
	this.tasaPercepcion = tasaPercepcion;
    }

    public BigDecimal getMontoPercepcion() {
	return montoPercepcion;
    }

    public void setMontoPercepcion(BigDecimal montoPercepcion) {
	this.montoPercepcion = montoPercepcion;
    }

    public Short getDiasAcumularPercepcion() {
	return diasAcumularPercepcion;
    }

    public void setDiasAcumularPercepcion(Short diasAcumularPercepcion) {
	this.diasAcumularPercepcion = diasAcumularPercepcion;
    }

    public BigDecimal getTasaRetencion() {
	return tasaRetencion;
    }

    public void setTasaRetencion(BigDecimal tasaRetencion) {
	this.tasaRetencion = tasaRetencion;
    }

    public BigDecimal getMontoRetencion() {
	return montoRetencion;
    }

    public void setMontoRetencion(BigDecimal montoRetencion) {
	this.montoRetencion = montoRetencion;
    }

    @XmlTransient
    public List<Cias> getCiasList() {
	return ciasList;
    }

    public void setCiasList(List<Cias> ciasList) {
	this.ciasList = ciasList;
    }

    public Cias getCodCiaMatriz() {
	return codCiaMatriz;
    }

    public void setCodCiaMatriz(Cias codCiaMatriz) {
	this.codCiaMatriz = codCiaMatriz;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (codCia != null ? codCia.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Cias)) {
	    return false;
	}
	Cias other = (Cias) object;
	if ((this.codCia == null && other.codCia != null) || (this.codCia != null && !this.codCia.equals(other.codCia))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.Cias[ codCia=" + codCia + " ]";
    }
    
}
