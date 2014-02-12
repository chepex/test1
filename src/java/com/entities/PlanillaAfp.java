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
@Table(name = "PLANILLA_AFP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanillaAfp.findAll", query = "SELECT p FROM PlanillaAfp p"),
    @NamedQuery(name = "PlanillaAfp.findByCodCia", query = "SELECT p FROM PlanillaAfp p WHERE p.planillaAfpPK.codCia = :codCia"),
    @NamedQuery(name = "PlanillaAfp.findByCodEmp", query = "SELECT p FROM PlanillaAfp p WHERE p.planillaAfpPK.codEmp = :codEmp and p.planillaAfpPK.codCia = :codCia and p.planillaAfpPK.anio = :anio and p.planillaAfpPK.mes = :mes"),
    @NamedQuery(name = "PlanillaAfp.findByAnioMes", query = "SELECT p FROM PlanillaAfp p WHERE p.planillaAfpPK.codCia = :codCia and p.planillaAfpPK.anio = :anio and p.planillaAfpPK.mes = :mes"),    
    @NamedQuery(name = "PlanillaAfp.findByAnio", query = "SELECT p FROM PlanillaAfp p WHERE p.planillaAfpPK.anio = :anio"),
    @NamedQuery(name = "PlanillaAfp.findByMes", query = "SELECT p FROM PlanillaAfp p WHERE p.planillaAfpPK.mes = :mes"),
    @NamedQuery(name = "PlanillaAfp.findByNumAfp", query = "SELECT p FROM PlanillaAfp p WHERE p.numAfp = :numAfp"),
    @NamedQuery(name = "PlanillaAfp.findByApellidos", query = "SELECT p FROM PlanillaAfp p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "PlanillaAfp.findByApCasada", query = "SELECT p FROM PlanillaAfp p WHERE p.apCasada = :apCasada"),
    @NamedQuery(name = "PlanillaAfp.findByNombres", query = "SELECT p FROM PlanillaAfp p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "PlanillaAfp.findByDocumento", query = "SELECT p FROM PlanillaAfp p WHERE p.documento = :documento"),
    @NamedQuery(name = "PlanillaAfp.findByNumDocumento", query = "SELECT p FROM PlanillaAfp p WHERE p.numDocumento = :numDocumento"),
    @NamedQuery(name = "PlanillaAfp.findByEstadoCivil", query = "SELECT p FROM PlanillaAfp p WHERE p.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "PlanillaAfp.findByPensionado", query = "SELECT p FROM PlanillaAfp p WHERE p.pensionado = :pensionado"),
    @NamedQuery(name = "PlanillaAfp.findByAfp", query = "SELECT p FROM PlanillaAfp p WHERE p.afp = :afp"),
    @NamedQuery(name = "PlanillaAfp.findByFechaIngreso", query = "SELECT p FROM PlanillaAfp p WHERE p.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "PlanillaAfp.findByDevengado", query = "SELECT p FROM PlanillaAfp p WHERE p.devengado = :devengado"),
    @NamedQuery(name = "PlanillaAfp.findByHoras", query = "SELECT p FROM PlanillaAfp p WHERE p.horas = :horas"),
    @NamedQuery(name = "PlanillaAfp.findByDias", query = "SELECT p FROM PlanillaAfp p WHERE p.dias = :dias"),
    @NamedQuery(name = "PlanillaAfp.findByIngreso", query = "SELECT p FROM PlanillaAfp p WHERE p.ingreso = :ingreso"),
    @NamedQuery(name = "PlanillaAfp.findByRetiro", query = "SELECT p FROM PlanillaAfp p WHERE p.retiro = :retiro"),
    @NamedQuery(name = "PlanillaAfp.findByLicencia", query = "SELECT p FROM PlanillaAfp p WHERE p.licencia = :licencia"),
    @NamedQuery(name = "PlanillaAfp.findByIncapacidad", query = "SELECT p FROM PlanillaAfp p WHERE p.incapacidad = :incapacidad"),
    @NamedQuery(name = "PlanillaAfp.findByAprendiz", query = "SELECT p FROM PlanillaAfp p WHERE p.aprendiz = :aprendiz"),
    @NamedQuery(name = "PlanillaAfp.findByPAnticipada", query = "SELECT p FROM PlanillaAfp p WHERE p.pAnticipada = :pAnticipada"),
    @NamedQuery(name = "PlanillaAfp.findByPSinObligacion", query = "SELECT p FROM PlanillaAfp p WHERE p.pSinObligacion = :pSinObligacion"),
    @NamedQuery(name = "PlanillaAfp.findByPRiesgo", query = "SELECT p FROM PlanillaAfp p WHERE p.pRiesgo = :pRiesgo"),
    @NamedQuery(name = "PlanillaAfp.findByDocente", query = "SELECT p FROM PlanillaAfp p WHERE p.docente = :docente"),
    @NamedQuery(name = "PlanillaAfp.findByCVoluntaria", query = "SELECT p FROM PlanillaAfp p WHERE p.cVoluntaria = :cVoluntaria"),
    @NamedQuery(name = "PlanillaAfp.findByCtv", query = "SELECT p FROM PlanillaAfp p WHERE p.ctv = :ctv"),
    @NamedQuery(name = "PlanillaAfp.findByCodCentro", query = "SELECT p FROM PlanillaAfp p WHERE p.codCentro = :codCentro"),
    @NamedQuery(name = "PlanillaAfp.findByUsuario", query = "SELECT p FROM PlanillaAfp p WHERE p.usuario = :usuario"),
    @NamedQuery(name = "PlanillaAfp.findByFechaReg", query = "SELECT p FROM PlanillaAfp p WHERE p.fechaReg = :fechaReg")})
public class PlanillaAfp implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlanillaAfpPK planillaAfpPK;
    @Size(max = 20)
    @Column(name = "NUM_AFP")
    private String numAfp;
    @Size(max = 30)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 20)
    @Column(name = "AP_CASADA")
    private String apCasada;
    @Size(max = 30)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 30)
    @Column(name = "DOCUMENTO")
    private String documento;
    @Size(max = 15)
    @Column(name = "NUM_DOCUMENTO")
    private String numDocumento;
    @Size(max = 1)
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    @Size(max = 1)
    @Column(name = "PENSIONADO")
    private String pensionado;
    @Size(max = 3)
    @Column(name = "AFP")
    private String afp;
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DEVENGADO")
    private BigDecimal devengado;
    @Column(name = "HORAS")
    private String horas;
    @Column(name = "DIAS")
    private String dias;
    @Size(max = 1)
    @Column(name = "INGRESO")
    private String ingreso;
    @Size(max = 1)
    @Column(name = "RETIRO")
    private String retiro;
    @Size(max = 1)
    @Column(name = "LICENCIA")
    private String licencia;
    @Size(max = 1)
    @Column(name = "INCAPACIDAD")
    private String incapacidad;
    @Size(max = 1)
    @Column(name = "APRENDIZ")
    private String aprendiz;
    @Size(max = 1)
    @Column(name = "P_ANTICIPADA")
    private String pAnticipada;
    @Size(max = 1)
    @Column(name = "P_SIN_OBLIGACION")
    private String pSinObligacion;
    @Size(max = 1)
    @Column(name = "P_RIESGO")
    private String pRiesgo;
    @Size(max = 1)
    @Column(name = "DOCENTE")
    private String docente;
    @Size(max = 1)
    @Column(name = "C_VOLUNTARIA")
    private String cVoluntaria;
    @Size(max = 1)
    @Column(name = "CTV")
    private String ctv;
    @Size(max = 1)
    @Column(name = "COD_CENTRO")
    private String codCentro;
    @Size(max = 20)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;

    public PlanillaAfp() {
    }

    public PlanillaAfp(PlanillaAfpPK planillaAfpPK) {
        this.planillaAfpPK = planillaAfpPK;
    }

    public PlanillaAfp(short codCia, short codEmp, short anio, short mes) {
        this.planillaAfpPK = new PlanillaAfpPK(codCia, codEmp, anio, mes);
    }

    public PlanillaAfpPK getPlanillaAfpPK() {
        return planillaAfpPK;
    }

    public void setPlanillaAfpPK(PlanillaAfpPK planillaAfpPK) {
        this.planillaAfpPK = planillaAfpPK;
    }

    public String getNumAfp() {
        return numAfp;
    }

    public void setNumAfp(String numAfp) {
        this.numAfp = numAfp;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApCasada() {
        return apCasada;
    }

    public void setApCasada(String apCasada) {
        this.apCasada = apCasada;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getPensionado() {
        return pensionado;
    }

    public void setPensionado(String pensionado) {
        this.pensionado = pensionado;
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public BigDecimal getDevengado() {
        return devengado;
    }

    public void setDevengado(BigDecimal devengado) {
        this.devengado = devengado;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

   

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getRetiro() {
        return retiro;
    }

    public void setRetiro(String retiro) {
        this.retiro = retiro;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getIncapacidad() {
        return incapacidad;
    }

    public void setIncapacidad(String incapacidad) {
        this.incapacidad = incapacidad;
    }

    public String getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(String aprendiz) {
        this.aprendiz = aprendiz;
    }

    public String getPAnticipada() {
        return pAnticipada;
    }

    public void setPAnticipada(String pAnticipada) {
        this.pAnticipada = pAnticipada;
    }

    public String getPSinObligacion() {
        return pSinObligacion;
    }

    public void setPSinObligacion(String pSinObligacion) {
        this.pSinObligacion = pSinObligacion;
    }

    public String getPRiesgo() {
        return pRiesgo;
    }

    public void setPRiesgo(String pRiesgo) {
        this.pRiesgo = pRiesgo;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getCVoluntaria() {
        return cVoluntaria;
    }

    public void setCVoluntaria(String cVoluntaria) {
        this.cVoluntaria = cVoluntaria;
    }

    public String getCtv() {
        return ctv;
    }

    public void setCtv(String ctv) {
        this.ctv = ctv;
    }

    public String getCodCentro() {
        return codCentro;
    }

    public void setCodCentro(String codCentro) {
        this.codCentro = codCentro;
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
        hash += (planillaAfpPK != null ? planillaAfpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanillaAfp)) {
            return false;
        }
        PlanillaAfp other = (PlanillaAfp) object;
        if ((this.planillaAfpPK == null && other.planillaAfpPK != null) || (this.planillaAfpPK != null && !this.planillaAfpPK.equals(other.planillaAfpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.PlanillaAfp[ planillaAfpPK=" + planillaAfpPK + " ]";
    }
    
}
