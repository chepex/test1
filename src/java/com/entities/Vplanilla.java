/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "VPLANILLA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vplanilla.findAll", query = "SELECT v FROM Vplanilla v WHERE v.vplanillaPK.codCia = :codCia"),
    @NamedQuery(name = "Vplanilla.findByPk", query = "SELECT p  FROM Vplanilla p WHERE p.vplanillaPK.codCia = :codCia and p.vplanillaPK.secuencia = :secuencia"),
    @NamedQuery(name = "Vplanilla.findByNombres", query = "SELECT v FROM Vplanilla v WHERE v.nombres = :nombres"),
    @NamedQuery(name = "Vplanilla.findByApellidos", query = "SELECT v FROM Vplanilla v WHERE v.apellidos = :apellidos"),
    @NamedQuery(name = "Vplanilla.findByNomDepto", query = "SELECT v FROM Vplanilla v WHERE v.nomDepto = :nomDepto"),
    @NamedQuery(name = "Vplanilla.findByDias", query = "SELECT v FROM Vplanilla v WHERE v.dias = :dias"),
    @NamedQuery(name = "Vplanilla.findByBruto", query = "SELECT v FROM Vplanilla v WHERE v.bruto = :bruto"),
    @NamedQuery(name = "Vplanilla.findByHorasextras", query = "SELECT v FROM Vplanilla v WHERE v.horasextras = :horasextras"),
    @NamedQuery(name = "Vplanilla.findByBonos", query = "SELECT v FROM Vplanilla v WHERE v.bonos = :bonos"),
    @NamedQuery(name = "Vplanilla.findByPrestaciones", query = "SELECT v FROM Vplanilla v WHERE v.prestaciones = :prestaciones"),
    @NamedQuery(name = "Vplanilla.findByNeto", query = "SELECT v FROM Vplanilla v WHERE v.neto = :neto"),
    @NamedQuery(name = "Vplanilla.findByIsss", query = "SELECT v FROM Vplanilla v WHERE v.isss = :isss"),
    @NamedQuery(name = "Vplanilla.findByAfp", query = "SELECT v FROM Vplanilla v WHERE v.afp = :afp"),
    @NamedQuery(name = "Vplanilla.findByRenta", query = "SELECT v FROM Vplanilla v WHERE v.renta = :renta"),
    @NamedQuery(name = "Vplanilla.findByOtrasd", query = "SELECT v FROM Vplanilla v WHERE v.otrasd = :otrasd"),
    @NamedQuery(name = "Vplanilla.findByDeducciones", query = "SELECT v FROM Vplanilla v WHERE v.deducciones = :deducciones"),
    @NamedQuery(name = "Vplanilla.findByLiquido", query = "SELECT v FROM Vplanilla v WHERE v.liquido = :liquido"),
    @NamedQuery(name = "Vplanilla.findByObservaciones", query = "SELECT v FROM Vplanilla v WHERE v.observaciones = :observaciones"),
    @NamedQuery(name = "Vplanilla.findByCantidahe", query = "SELECT v FROM Vplanilla v WHERE v.cantidahe = :cantidahe"),
    @NamedQuery(name = "Vplanilla.findByLlegadatarde", query = "SELECT v FROM Vplanilla v WHERE v.llegadatarde = :llegadatarde"),
    @NamedQuery(name = "Vplanilla.findByCantLlegat", query = "SELECT v FROM Vplanilla v WHERE v.cantLlegat = :cantLlegat"),
    @NamedQuery(name = "Vplanilla.findByAnio", query = "SELECT v FROM Vplanilla v WHERE v.anio = :anio"),
    @NamedQuery(name = "Vplanilla.findByMes", query = "SELECT v FROM Vplanilla v WHERE v.mes = :mes"),
    @NamedQuery(name = "Vplanilla.findByNumPlanilla", query = "SELECT v FROM Vplanilla v WHERE v.numPlanilla = :numPlanilla"),
    @NamedQuery(name = "Vplanilla.findByNomTipopla", query = "SELECT v FROM Vplanilla v WHERE v.nomTipopla = :nomTipopla")})
public class Vplanilla implements Serializable {
        @EmbeddedId
    protected VplanillaPK vplanillaPK;
    private static final long serialVersionUID = 1L;
    @Size(max = 30)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 30)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 30)
    @Column(name = "NOM_DEPTO")
    private String nomDepto;
    @Size(max = 7)
    @Column(name = "DIAS")
    private String dias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BRUTO")
    private BigDecimal bruto;
    @Column(name = "HORASEXTRAS")
    private BigDecimal horasextras;
    @Column(name = "BONOS")
    private BigDecimal bonos;
    @Column(name = "PRESTACIONES")
    private BigDecimal prestaciones;
    @Column(name = "NETO")
    private BigDecimal neto;
    @Column(name = "ISSS")
    private BigDecimal isss;
    @Column(name = "AFP")
    private BigDecimal afp;
    @Column(name = "RENTA")
    private BigDecimal renta;
    @Column(name = "OTRASD")
    private BigDecimal otrasd;
    @Column(name = "DEDUCCIONES")
    private BigDecimal deducciones;
    @Column(name = "LIQUIDO")
    private BigDecimal liquido;
    @Size(max = 1)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "CANTIDAHE")
    private BigDecimal cantidahe;
    @Column(name = "LLEGADATARDE")
    private BigDecimal llegadatarde;
    @Column(name = "CANT_LLEGAT")
    private BigDecimal cantLlegat;
    @Column(name = "ANIO")
    private Short anio;
    @Column(name = "MES")
    private Short mes;
    @Column(name = "NUM_PLANILLA")
    private Short numPlanilla;
    @Size(max = 40)
    @Column(name = "NOM_TIPOPLA")
    private String nomTipopla;

    public Vplanilla() {
    }

    public VplanillaPK getVplanillaPK() {
        return vplanillaPK;
    }

    public void setVplanillaPK(VplanillaPK vplanillaPK) {
        this.vplanillaPK = vplanillaPK;
    }

    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNomDepto() {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
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

    public BigDecimal getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(BigDecimal prestaciones) {
        this.prestaciones = prestaciones;
    }

    public BigDecimal getIsss() {
        return isss;
    }

    public void setIsss(BigDecimal isss) {
        this.isss = isss;
    }

    public BigDecimal getAfp() {
        return afp;
    }

    public void setAfp(BigDecimal afp) {
        this.afp = afp;
    }

    public BigDecimal getRenta() {
        return renta;
    }

    public void setRenta(BigDecimal renta) {
        this.renta = renta;
    }

    public BigDecimal getOtrasd() {
        return otrasd;
    }

    public void setOtrasd(BigDecimal otrasd) {
        this.otrasd = otrasd;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getCantidahe() {
        return cantidahe;
    }

    public void setCantidahe(BigDecimal cantidahe) {
        this.cantidahe = cantidahe;
    }

    public BigDecimal getLlegadatarde() {
        return llegadatarde;
    }

    public void setLlegadatarde(BigDecimal llegadatarde) {
        this.llegadatarde = llegadatarde;
    }

    public BigDecimal getCantLlegat() {
        return cantLlegat;
    }

    public void setCantLlegat(BigDecimal cantLlegat) {
        this.cantLlegat = cantLlegat;
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

    public Short getNumPlanilla() {
        return numPlanilla;
    }

    public void setNumPlanilla(Short numPlanilla) {
        this.numPlanilla = numPlanilla;
    }

    public String getNomTipopla() {
        return nomTipopla;
    }

    public void setNomTipopla(String nomTipopla) {
        this.nomTipopla = nomTipopla;
    }
    
}
