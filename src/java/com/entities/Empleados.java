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
@Table(name = "EMPLEADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByCodCia", query = "SELECT e FROM Empleados e WHERE e.empleadosPK.codCia = :codCia"),
    @NamedQuery(name = "Empleados.findByTipoPla", query = "SELECT e FROM Empleados e JOIN e.departamentos d  WHERE e.empleadosPK.codCia = :codCia and e.status like :status and d.codTipopla = :codTipopla"),
    @NamedQuery(name = "Empleados.findByVac", query = "SELECT e FROM Empleados e WHERE e.empleadosPK.codCia = :codCia and e.status like :status and e.vacaciones = :vacaciones "),    
    @NamedQuery(name = "Empleados.findByFiltros", query = "SELECT e FROM Empleados e WHERE e.empleadosPK.codCia like :codCia  and  e.apellidos like :apellidos and e.nombres like :nombres"),
    @NamedQuery(name = "Empleados.findByPk2", query = "SELECT e FROM Empleados e WHERE e.empleadosPK.codCia = :codCia and  e.empleadosPK.codEmp = :codEmp"),        
    @NamedQuery(name = "Empleados.findByPk3", query = "SELECT e FROM Empleados e WHERE e.empleadosPK.codCia = :codCia and  e.codEmpref = :codEmpref"),            
    @NamedQuery(name = "Empleados.findByDeptos", query = "SELECT e FROM Empleados e  WHERE  e.empleadosPK.codCia = :codCia and e.status like :status"	
	+ " and e.departamentos.departamentosPK.codDepto in :departamentos  "),    
    @NamedQuery(name = "Empleados.findByCodEmp", query = "SELECT e FROM Empleados e WHERE e.empleadosPK.codEmp = :codEmp"),
    @NamedQuery(name = "Empleados.findByApellidos", query = "SELECT e FROM Empleados e WHERE e.apellidos = :apellidos"),
    @NamedQuery(name = "Empleados.findByNombres", query = "SELECT e FROM Empleados e WHERE e.nombres = :nombres"),
    @NamedQuery(name = "Empleados.findByApCasada", query = "SELECT e FROM Empleados e WHERE e.apCasada = :apCasada"),
    @NamedQuery(name = "Empleados.findByDireccion", query = "SELECT e FROM Empleados e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empleados.findByTelefonos", query = "SELECT e FROM Empleados e WHERE e.telefonos = :telefonos"),
    @NamedQuery(name = "Empleados.findByFechaNac", query = "SELECT e FROM Empleados e WHERE e.fechaNac = :fechaNac"),
    @NamedQuery(name = "Empleados.findByNumIgss", query = "SELECT e FROM Empleados e WHERE e.numIgss = :numIgss"),
    @NamedQuery(name = "Empleados.findByNumIrtra", query = "SELECT e FROM Empleados e WHERE e.numIrtra = :numIrtra"),
    @NamedQuery(name = "Empleados.findByNumNit", query = "SELECT e FROM Empleados e WHERE e.numNit = :numNit"),
    @NamedQuery(name = "Empleados.findByFecIngreso", query = "SELECT e FROM Empleados e WHERE e.fecIngreso = :fecIngreso"),
    @NamedQuery(name = "Empleados.findByFecSalida", query = "SELECT e FROM Empleados e WHERE e.fecSalida = :fecSalida"),
    @NamedQuery(name = "Empleados.findByMotSalida", query = "SELECT e FROM Empleados e WHERE e.motSalida = :motSalida"),
    @NamedQuery(name = "Empleados.findByObservacion", query = "SELECT e FROM Empleados e WHERE e.observacion = :observacion"),
    @NamedQuery(name = "Empleados.findByStatus", query = "SELECT e FROM Empleados e WHERE  e.empleadosPK.codCia = :codCia and  e.status like :status"),
    @NamedQuery(name = "Empleados.findByTipoContra", query = "SELECT e FROM Empleados e WHERE e.tipoContra = :tipoContra"),
    @NamedQuery(name = "Empleados.findBySalario", query = "SELECT e FROM Empleados e WHERE e.salario = :salario"),
    @NamedQuery(name = "Empleados.findByPromedio", query = "SELECT e FROM Empleados e WHERE e.promedio = :promedio"),
    @NamedQuery(name = "Empleados.findByBonificacion", query = "SELECT e FROM Empleados e WHERE e.bonificacion = :bonificacion"),
    @NamedQuery(name = "Empleados.findBySalarioBase", query = "SELECT e FROM Empleados e WHERE e.salarioBase = :salarioBase"),
    @NamedQuery(name = "Empleados.findByAvisarA", query = "SELECT e FROM Empleados e WHERE e.avisarA = :avisarA"),
    @NamedQuery(name = "Empleados.findByCedula", query = "SELECT e FROM Empleados e WHERE e.cedula = :cedula"),
    
    @NamedQuery(name = "Empleados.findByCtaBancaria", query = "SELECT e FROM Empleados e WHERE e.ctaBancaria = :ctaBancaria"),
    @NamedQuery(name = "Empleados.findByComSprod", query = "SELECT e FROM Empleados e WHERE e.comSprod = :comSprod"),
    @NamedQuery(name = "Empleados.findByComSobra", query = "SELECT e FROM Empleados e WHERE e.comSobra = :comSobra"),
    @NamedQuery(name = "Empleados.findByValIsr", query = "SELECT e FROM Empleados e WHERE e.valIsr = :valIsr"),
    @NamedQuery(name = "Empleados.findBySexo", query = "SELECT e FROM Empleados e WHERE e.sexo = :sexo"),
    @NamedQuery(name = "Empleados.findByTipoCta", query = "SELECT e FROM Empleados e WHERE e.tipoCta = :tipoCta"),
    @NamedQuery(name = "Empleados.findByNupAfp", query = "SELECT e FROM Empleados e WHERE e.nupAfp = :nupAfp"),
    @NamedQuery(name = "Empleados.findByCodAfp", query = "SELECT e FROM Empleados e WHERE e.codAfp = :codAfp"),
    @NamedQuery(name = "Empleados.findByNumHijos", query = "SELECT e FROM Empleados e WHERE e.numHijos = :numHijos"),
    @NamedQuery(name = "Empleados.findByEstadoCivil", query = "SELECT e FROM Empleados e WHERE e.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Empleados.findByCodEmpref", query = "SELECT e FROM Empleados e WHERE e.codEmpref = :codEmpref"),
    @NamedQuery(name = "Empleados.findByNombreIsss", query = "SELECT e FROM Empleados e WHERE e.nombreIsss = :nombreIsss"),
    @NamedQuery(name = "Empleados.findByCodIsss", query = "SELECT e FROM Empleados e WHERE e.codIsss = :codIsss"),
    @NamedQuery(name = "Empleados.findByComision", query = "SELECT e FROM Empleados e WHERE e.comision = :comision"),
    @NamedQuery(name = "Empleados.findByFechaIngresoHist", query = "SELECT e FROM Empleados e WHERE e.fechaIngresoHist = :fechaIngresoHist"),
    @NamedQuery(name = "Empleados.findByMarcacionTarjeta", query = "SELECT e FROM Empleados e WHERE e.marcacionTarjeta = :marcacionTarjeta"),
    @NamedQuery(name = "Empleados.findByUsuario", query = "SELECT e FROM Empleados e WHERE e.userName like :usuario and e.empleadosPK.codCia = :codCia"),
    @NamedQuery(name = "Empleados.findByFechaReg", query = "SELECT e FROM Empleados e WHERE e.fechaReg = :fechaReg"),
    @NamedQuery(name = "Empleados.findByBeneficiario", query = "SELECT e FROM Empleados e WHERE e.beneficiario = :beneficiario"),
    @NamedQuery(name = "Empleados.findByNombreNit", query = "SELECT e FROM Empleados e WHERE e.nombreNit like :nombreNit"),
    @NamedQuery(name = "Empleados.findByVacaciones", query = "SELECT e FROM Empleados e WHERE e.vacaciones = :vacaciones"),
    @NamedQuery(name = "Empleados.findByPreId", query = "SELECT e FROM Empleados e WHERE e.preId = :preId"),
    @NamedQuery(name = "Empleados.findByCodDepartamento", query = "SELECT e FROM Empleados e WHERE e.codDepartamento = :codDepartamento"),
    @NamedQuery(name = "Empleados.findByCodZona", query = "SELECT e FROM Empleados e WHERE e.codZona = :codZona"),
    @NamedQuery(name = "Empleados.findByCodMuni", query = "SELECT e FROM Empleados e WHERE e.codMuni = :codMuni"),
    @NamedQuery(name = "Empleados.findByCodSeguridad", query = "SELECT e FROM Empleados e WHERE e.codSeguridad = :codSeguridad"),
    @NamedQuery(name = "Empleados.findByFExtDoc", query = "SELECT e FROM Empleados e WHERE e.fExtDoc = :fExtDoc"),
    @NamedQuery(name = "Empleados.findByCodPais", query = "SELECT e FROM Empleados e WHERE e.codPais = :codPais"),
    @NamedQuery(name = "Empleados.findByFecRegistro", query = "SELECT e FROM Empleados e WHERE e.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Empleados.findByCodBanco", query = "SELECT e FROM Empleados e WHERE e.codBanco = :codBanco"),
    @NamedQuery(name = "Empleados.findByCodArea", query = "SELECT e FROM Empleados e WHERE e.codArea = :codArea"),
    @NamedQuery(name = "Empleados.findByPassword", query = "SELECT e FROM Empleados e WHERE e.password = :password"),
    @NamedQuery(name = "Empleados.findByPuesto", query = "SELECT e FROM Empleados e WHERE e.empleadosPK.codCia = :codCia and e.status like :status" 
                     + " and e.puestos.puestosPK.codPuesto = :puesto")
})
public class Empleados implements Serializable {
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleados")
    private List<DetEmpleado> detEmpleadoList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmpleadosPK empleadosPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 20)
    @Column(name = "AP_CASADA")
    private String apCasada;
    @Size(max = 120)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 30)
    @Column(name = "TELEFONOS")
    private String telefonos;
    @Column(name = "FECHA_NAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNac;
    @Size(max = 12)
    @Column(name = "NUM_IGSS")
    private String numIgss;
    @Size(max = 12)
    @Column(name = "NUM_IRTRA")
    private String numIrtra;
    @Size(max = 20)
    @Column(name = "NUM_NIT")
    private String numNit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FEC_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecIngreso;
    @Column(name = "FEC_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecSalida;
    @Size(max = 250)
    @Column(name = "MOT_SALIDA")
    private String motSalida;
    @Size(max = 250)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_CONTRA")
    private String tipoContra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALARIO")
    private BigDecimal salario;
    @Column(name = "PROMEDIO")
    private BigDecimal promedio;
    @Column(name = "BONIFICACION")
    private BigDecimal bonificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALARIO_BASE")
    private BigDecimal salarioBase;
    @Size(max = 250)
    @Column(name = "AVISAR_A")
    private String avisarA;
    @Size(max = 15)
    @Column(name = "CEDULA")
    private String cedula;    
    @Size(max = 15)
    @Column(name = "CTA_BANCARIA")
    private String ctaBancaria;
    @Size(max = 1)
    @Column(name = "COM_SPROD")
    private String comSprod;
    @Size(max = 1)
    @Column(name = "COM_SOBRA")
    private String comSobra;
    @Column(name = "VAL_ISR")
    private BigDecimal valIsr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEXO")
    private short sexo;
    @Size(max = 2)
    @Column(name = "TIPO_CTA")
    private String tipoCta;
    @Size(max = 20)
    @Column(name = "NUP_AFP")
    private String nupAfp;
    @Column(name = "COD_AFP")
    private Short codAfp;
    @Column(name = "NUM_HIJOS")
    private Short numHijos;
    @Size(max = 1)
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    @Size(max = 6)
    @Column(name = "COD_EMPREF")
    private String codEmpref;
    @Size(max = 100)
    @Column(name = "NOMBRE_ISSS")
    private String nombreIsss;
    @Column(name = "COD_ISSS")
    private Short codIsss;
    @Size(max = 1)
    @Column(name = "COMISION")
    private String comision;
    @Column(name = "FECHA_INGRESO_HIST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngresoHist;
    @Column(name = "MARCACION_TARJETA")
    private Character marcacionTarjeta;
    @Size(max = 20)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @Size(max = 60)
    @Column(name = "BENEFICIARIO")
    private String beneficiario;
    @Size(max = 100)
    @Column(name = "NOMBRE_NIT")
    private String nombreNit;
    @Column(name = "VACACIONES")
    private BigInteger vacaciones;
    @Column(name = "PRE_ID")
    private BigInteger preId;
    @Column(name = "COD_DEPARTAMENTO")
    private BigInteger codDepartamento;
    @Column(name = "COD_ZONA")
    private BigInteger codZona;
    @Column(name = "COD_MUNI")
    private BigInteger codMuni;
    @Column(name = "COD_SEGURIDAD")
    private Short codSeguridad;
    @Column(name = "F_EXT_DOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fExtDoc;
    @Column(name = "COD_PAIS")
    private Short codPais;
    @Column(name = "FEC_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Size(max = 5)
    @Column(name = "COD_BANCO")
    private String codBanco;
    @Column(name = "COD_AREA")
    private BigInteger codArea;
    @Size(max = 250)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 100)
    @Column(name = "USER_NAME")
    private String userName;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleados")
    private List<PlanillaHoras> planillaHorasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleados")
    private List<OtrosMovDp> otrosMovDpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleados")
    private List<MovDp> movDpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleados")
    private List<Prestamos> prestamosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleados")
    private List<ResumenAsistencia> resumenAsistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleados")
    private List<Planilla> planillaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleados")
    private List<PlanillaIsss> planillaIsssList;    
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_PUESTO", referencedColumnName = "COD_PUESTO")})
    @ManyToOne(optional = false)
    private Puestos puestos;
    @JoinColumn(name = "COD_PROFESION", referencedColumnName = "COD_PROFESION")
    @ManyToOne
    private Profesiones codProfesion;
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_DEPTO", referencedColumnName = "COD_DEPTO")})
    @ManyToOne(optional = false)
    private Departamentos departamentos;

    public Empleados() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public Empleados(EmpleadosPK empleadosPK) {
	this.empleadosPK = empleadosPK;
    }

    public Empleados(EmpleadosPK empleadosPK, String apellidos, String nombres, Date fecIngreso, String status, String tipoContra, BigDecimal salario, BigDecimal salarioBase, short sexo) {
	this.empleadosPK = empleadosPK;
	this.apellidos = apellidos;
	this.nombres = nombres;
	this.fecIngreso = fecIngreso;
	this.status = status;
	this.tipoContra = tipoContra;
	this.salario = salario;
	this.salarioBase = salarioBase;	
	this.sexo = sexo;
    }

    public Empleados(short codCia, int codEmp) {
	this.empleadosPK = new EmpleadosPK(codCia, codEmp);
    }

    public List<PlanillaIsss> getPlanillaIsssList() {
        return planillaIsssList;
    }

    public void setPlanillaIsssList(List<PlanillaIsss> planillaIsssList) {
        this.planillaIsssList = planillaIsssList;
    }

    
    public EmpleadosPK getEmpleadosPK() {
	return empleadosPK;
    }

    public void setEmpleadosPK(EmpleadosPK empleadosPK) {
	this.empleadosPK = empleadosPK;
    }

    public String getApellidos() {
	return apellidos;
    }

    public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
    }

    public String getNombres() {
	return nombres;
    }

    public void setNombres(String nombres) {
	this.nombres = nombres;
    }

    public String getApCasada() {
	return apCasada;
    }

    public void setApCasada(String apCasada) {
	this.apCasada = apCasada;
    }

    public String getDireccion() {
	return direccion;
    }

    public void setDireccion(String direccion) {
	this.direccion = direccion;
    }

    public String getTelefonos() {
	return telefonos;
    }

    public void setTelefonos(String telefonos) {
	this.telefonos = telefonos;
    }

    public Date getFechaNac() {
	return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
	this.fechaNac = fechaNac;
    }

    public String getNumIgss() {
	return numIgss;
    }

    public void setNumIgss(String numIgss) {
	this.numIgss = numIgss;
    }

    public String getNumIrtra() {
	return numIrtra;
    }

    public void setNumIrtra(String numIrtra) {
	this.numIrtra = numIrtra;
    }

    public String getNumNit() {
	return numNit;
    }

    public void setNumNit(String numNit) {
	this.numNit = numNit;
    }

    public Date getFecIngreso() {
	return fecIngreso;
    }

    public void setFecIngreso(Date fecIngreso) {
	this.fecIngreso = fecIngreso;
    }

    public Date getFecSalida() {
	return fecSalida;
    }

    public void setFecSalida(Date fecSalida) {
	this.fecSalida = fecSalida;
    }

    public String getMotSalida() {
	return motSalida;
    }

    public void setMotSalida(String motSalida) {
	this.motSalida = motSalida;
    }

    public String getObservacion() {
	return observacion;
    }

    public void setObservacion(String observacion) {
	this.observacion = observacion;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getTipoContra() {
	return tipoContra;
    }

    public void setTipoContra(String tipoContra) {
	this.tipoContra = tipoContra;
    }

    public BigDecimal getSalario() {
	return salario;
    }

    public void setSalario(BigDecimal salario) {
	this.salario = salario;
    }

    public BigDecimal getPromedio() {
	return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
	this.promedio = promedio;
    }

    public BigDecimal getBonificacion() {
	return bonificacion;
    }

    public void setBonificacion(BigDecimal bonificacion) {
	this.bonificacion = bonificacion;
    }

    public BigDecimal getSalarioBase() {
	return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
	this.salarioBase = salarioBase;
    }

    public String getAvisarA() {
	return avisarA;
    }

    public void setAvisarA(String avisarA) {
	this.avisarA = avisarA;
    }

    public String getCedula() {
	return cedula;
    }

    public void setCedula(String cedula) {
	this.cedula = cedula;
    }

  

    public String getCtaBancaria() {
	return ctaBancaria;
    }

    public void setCtaBancaria(String ctaBancaria) {
	this.ctaBancaria = ctaBancaria;
    }

    public String getComSprod() {
	return comSprod;
    }

    public void setComSprod(String comSprod) {
	this.comSprod = comSprod;
    }

    public String getComSobra() {
	return comSobra;
    }

    public void setComSobra(String comSobra) {
	this.comSobra = comSobra;
    }

    public BigDecimal getValIsr() {
	return valIsr;
    }

    public void setValIsr(BigDecimal valIsr) {
	this.valIsr = valIsr;
    }

    public short getSexo() {
	return sexo;
    }

    public void setSexo(short sexo) {
	this.sexo = sexo;
    }

    public String getTipoCta() {
	return tipoCta;
    }

    public void setTipoCta(String tipoCta) {
	this.tipoCta = tipoCta;
    }

    public String getNupAfp() {
	return nupAfp;
    }

    public void setNupAfp(String nupAfp) {
	this.nupAfp = nupAfp;
    }

    public Short getCodAfp() {
	return codAfp;
    }

    public void setCodAfp(Short codAfp) {
	this.codAfp = codAfp;
    }

    public Short getNumHijos() {
	return numHijos;
    }

    public void setNumHijos(Short numHijos) {
	this.numHijos = numHijos;
    }

    public String getEstadoCivil() {
	return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
	this.estadoCivil = estadoCivil;
    }

    public String getCodEmpref() {
	return codEmpref;
    }

    public void setCodEmpref(String codEmpref) {
	this.codEmpref = codEmpref;
    }

    public String getNombreIsss() {
	return nombreIsss;
    }

    public void setNombreIsss(String nombreIsss) {
	this.nombreIsss = nombreIsss;
    }

    public Short getCodIsss() {
	return codIsss;
    }

    public void setCodIsss(Short codIsss) {
	this.codIsss = codIsss;
    }

    public String getComision() {
	return comision;
    }

    public void setComision(String comision) {
	this.comision = comision;
    }

    public Date getFechaIngresoHist() {
	return fechaIngresoHist;
    }

    public void setFechaIngresoHist(Date fechaIngresoHist) {
	this.fechaIngresoHist = fechaIngresoHist;
    }

    public Character getMarcacionTarjeta() {
	return marcacionTarjeta;
    }

    public void setMarcacionTarjeta(Character marcacionTarjeta) {
	this.marcacionTarjeta = marcacionTarjeta;
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

    public String getBeneficiario() {
	return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
	this.beneficiario = beneficiario;
    }

    public String getNombreNit() {
	return nombreNit;
    }

    public void setNombreNit(String nombreNit) {
	this.nombreNit = nombreNit;
    }

    public BigInteger getVacaciones() {
	return vacaciones;
    }

    public void setVacaciones(BigInteger vacaciones) {
	this.vacaciones = vacaciones;
    }

    public BigInteger getPreId() {
	return preId;
    }

    public void setPreId(BigInteger preId) {
	this.preId = preId;
    }

    public BigInteger getCodDepartamento() {
	return codDepartamento;
    }

    public void setCodDepartamento(BigInteger codDepartamento) {
	this.codDepartamento = codDepartamento;
    }

    public BigInteger getCodZona() {
	return codZona;
    }

    public void setCodZona(BigInteger codZona) {
	this.codZona = codZona;
    }

    public BigInteger getCodMuni() {
	return codMuni;
    }

    public void setCodMuni(BigInteger codMuni) {
	this.codMuni = codMuni;
    }

    public Short getCodSeguridad() {
	return codSeguridad;
    }

    public void setCodSeguridad(Short codSeguridad) {
	this.codSeguridad = codSeguridad;
    }

    public Date getFExtDoc() {
	return fExtDoc;
    }

    public void setFExtDoc(Date fExtDoc) {
	this.fExtDoc = fExtDoc;
    }

    public Short getCodPais() {
	return codPais;
    }

    public void setCodPais(Short codPais) {
	this.codPais = codPais;
    }

    public Date getFecRegistro() {
	return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
	this.fecRegistro = fecRegistro;
    }

    public String getCodBanco() {
	return codBanco;
    }

    public void setCodBanco(String codBanco) {
	this.codBanco = codBanco;
    }

    public BigInteger getCodArea() {
	return codArea;
    }

    public void setCodArea(BigInteger codArea) {
	this.codArea = codArea;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
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

    @XmlTransient
    public List<ResumenAsistencia> getResumenAsistenciaList() {
	return resumenAsistenciaList;
    }

    public void setResumenAsistenciaList(List<ResumenAsistencia> resumenAsistenciaList) {
	this.resumenAsistenciaList = resumenAsistenciaList;
    }

    @XmlTransient
    public List<Planilla> getPlanillaList() {
	return planillaList;
    }

    public void setPlanillaList(List<Planilla> planillaList) {
	this.planillaList = planillaList;
    }

    public Puestos getPuestos() {
	return puestos;
    }

    public void setPuestos(Puestos puestos) {
	this.puestos = puestos;
    }

    public Profesiones getCodProfesion() {
	return codProfesion;
    }

    public void setCodProfesion(Profesiones codProfesion) {
	this.codProfesion = codProfesion;
    }

    public Departamentos getDepartamentos() {
	return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
	this.departamentos = departamentos;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (empleadosPK != null ? empleadosPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Empleados)) {
	    return false;
	}
	Empleados other = (Empleados) object;
	if ((this.empleadosPK == null && other.empleadosPK != null) || (this.empleadosPK != null && !this.empleadosPK.equals(other.empleadosPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.Empleados[ empleadosPK=" + empleadosPK + " ]";
    }
    @XmlTransient
    public List<DetEmpleado> getDetEmpleadoList() {
        return detEmpleadoList;
    }

    public void setDetEmpleadoList(List<DetEmpleado> detEmpleadoList) {
        this.detEmpleadoList = detEmpleadoList;
    }
        
}
