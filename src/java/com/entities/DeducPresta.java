
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "DEDUC_PRESTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeducPresta.findAll", query = "SELECT d FROM DeducPresta d"),
    @NamedQuery(name = "DeducPresta.findByCodCia", query = "SELECT d FROM DeducPresta d WHERE d.deducPrestaPK.codCia = :codCia"),
    @NamedQuery(name = "DeducPresta.findByCatDp", query = "SELECT d FROM DeducPresta d WHERE d.deducPrestaPK.codCia = :codCia and d.catDp.descripcion like :catDp "        ),
    @NamedQuery(name = "DeducPresta.findByCatNull", query = "SELECT d FROM DeducPresta d WHERE d.deducPrestaPK.codCia = :codCia and d.catDp is null "        ),
    @NamedQuery(name = "DeducPresta.findByTipoPla", query = "SELECT d FROM DeducPresta d WHERE d.deducPrestaPK.codCia = :codCia and d.tiposPlanilla.tiposPlanillaPK.codTipopla = :codTipopla  "        ),    
    @NamedQuery(name = "DeducPresta.findByAfp", query = "SELECT d FROM DeducPresta d WHERE d.deducPrestaPK.codCia = :codCia and d.afp='S'"        ),        
    @NamedQuery(name = "DeducPresta.findByIsss", query = "SELECT d FROM DeducPresta d WHERE d.deducPrestaPK.codCia = :codCia and d.isss='S'"        ),            
    
    @NamedQuery(name = "DeducPresta.findByCodDp", query = "SELECT d FROM DeducPresta d WHERE  d.deducPrestaPK.codCia = :codCia and d.deducPrestaPK.codDp = :codDp"),
    @NamedQuery(name = "DeducPresta.findByDescripcion", query = "SELECT d FROM DeducPresta d WHERE d.descripcion = :descripcion"),    
    @NamedQuery(name = "DeducPresta.findByLey", query = "SELECT d FROM DeducPresta d WHERE d.afp = :afp"),
    @NamedQuery(name = "DeducPresta.findByFactor", query = "SELECT d FROM DeducPresta d WHERE d.factor = :factor"),
    @NamedQuery(name = "DeducPresta.findByProceso", query = "SELECT d FROM DeducPresta d WHERE d.proceso = :proceso")})
public class DeducPresta implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deducPresta")
    private List<DetEmpleado> detEmpleadoList;    
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_CAT", referencedColumnName = "COD_CAT")})
    @ManyToOne(optional = false)
    private CatDp catDp;    
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA",insertable = false, updatable = false),
        @JoinColumn(name = "COD_DP", referencedColumnName = "COD_DP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)    
    private TiposPlanilla tiposPlanilla;    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DeducPrestaPK deducPrestaPK;
    @Size(max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "RENTA")
    private String renta;    
    @Size(max = 1)
    @Column(name = "NOGRAVADO")
    private String nogravado;     
    @Size(max = 1)
    @Column(name = "AFP")
    private String afp;
    @Size(max = 1)
    @Column(name = "ISSS")
    private String isss;    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FACTOR")
    private BigDecimal factor;
   @Column(name = "PRIORIDAD")
    private short prioridad;    
    @Column(name = "TOPE")
    private BigDecimal tope;    
    @Size(max = 100)
    @Column(name = "PROCESO")
    private String proceso;
    @Column(name = "SEGMENTAR")
    private String segmentar;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deducPresta")
    private List<PlanillaHoras> planillaHorasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deducPresta")
    private List<OtrosMovDp> otrosMovDpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deducPresta")
    private List<MovDp> movDpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deducPresta")
    private List<Prestamos> prestamosList;
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
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    
    
    public DeducPresta() {
    }

    public String getSegmentar() {
        return segmentar;
    }

    public void setSegmentar(String segmentar) {
        this.segmentar = segmentar;
    }

    
    public String getRenta() {
        return renta;
    }

    public String getIsss() {
        return isss;
    }

    public void setIsss(String isss) {
        this.isss = isss;
    }

    public String getNogravado() {
        return nogravado;
    }

    public void setNogravado(String nogravado) {
        this.nogravado = nogravado;
    }

    
    
    public void setRenta(String renta) {
        this.renta = renta;
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

    
    public short getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(short prioridad) {
        this.prioridad = prioridad;
    }

    
    public TiposPlanilla getTiposPlanilla() {
        return tiposPlanilla;
    }

    public void setTiposPlanilla(TiposPlanilla tiposPlanilla) {
        this.tiposPlanilla = tiposPlanilla;
    }

    public DeducPresta(DeducPrestaPK deducPrestaPK) {
	this.deducPrestaPK = deducPrestaPK;
    }

    public DeducPresta(short codCia, short codDp) {
	this.deducPrestaPK = new DeducPrestaPK(codCia, codDp);
    }

    public DeducPrestaPK getDeducPrestaPK() {
	return deducPrestaPK;
    }

    public void setDeducPrestaPK(DeducPrestaPK deducPrestaPK) {
	this.deducPrestaPK = deducPrestaPK;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public BigDecimal getTope() {
        return tope;
    }

    public void setTope(BigDecimal tope) {
        this.tope = tope;
    }



    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }



    public BigDecimal getFactor() {
	return factor;
    }

    public void setFactor(BigDecimal factor) {
	this.factor = factor;
    }

    public String getProceso() {
	return proceso;
    }

    public void setProceso(String proceso) {
	this.proceso = proceso;
    }

    @XmlTransient
    public List<PlanillaHoras> getPlanillaHorasList() {
	return planillaHorasList;
    }

    public void setPlanillaHorasList(List<PlanillaHoras> planillaHorasList) {
	this.planillaHorasList = planillaHorasList;
    }

    @XmlTransient
    public List<OtrosMovDp> getOtrosMovDpList() {
	return otrosMovDpList;
    }

    public void setOtrosMovDpList(List<OtrosMovDp> otrosMovDpList) {
	this.otrosMovDpList = otrosMovDpList;
    }

    @XmlTransient
    public List<MovDp> getMovDpList() {
	return movDpList;
    }

    public void setMovDpList(List<MovDp> movDpList) {
	this.movDpList = movDpList;
    }

    @XmlTransient
    public List<Prestamos> getPrestamosList() {
	return prestamosList;
    }

    public void setPrestamosList(List<Prestamos> prestamosList) {
	this.prestamosList = prestamosList;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (deducPrestaPK != null ? deducPrestaPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof DeducPresta)) {
	    return false;
	}
	DeducPresta other = (DeducPresta) object;
	if ((this.deducPrestaPK == null && other.deducPrestaPK != null) || (this.deducPrestaPK != null && !this.deducPrestaPK.equals(other.deducPrestaPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.DeducPresta[ deducPrestaPK=" + deducPrestaPK + " ]";
    }

    public CatDp getCatDp() {
	return catDp;
    }

    public void setCatDp(CatDp catDp) {
	this.catDp = catDp;
    }

    @XmlTransient
    public List<DetEmpleado> getDetEmpleadoList() {
        return detEmpleadoList;
    }

    public void setDetEmpleadoList(List<DetEmpleado> detEmpleadoList) {
        this.detEmpleadoList = detEmpleadoList;
    }    
}
