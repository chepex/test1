/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import com.entities.util.JsfUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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

/**
 *resumenAsistenciaPK.secuencia
 * @author mmixco
 */
@Entity
@Table(name = "RESUMEN_ASISTENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResumenAsistencia.delByFiltro", query = "DELETE  FROM ResumenAsistencia r WHERE r.resumenAsistenciaPK.codCia = :codCia and r.resumenAsistenciaPK.secuencia = :secuencia"),
    @NamedQuery(name = "ResumenAsistencia.findAll", query = "SELECT r FROM ResumenAsistencia r"),
    @NamedQuery(name = "ResumenAsistencia.findByCodCia", query = "SELECT r FROM ResumenAsistencia r WHERE r.resumenAsistenciaPK.codCia = :codCia"),
    @NamedQuery(name = "ResumenAsistencia.findByFiltro", query = "SELECT r FROM ResumenAsistencia r WHERE r.resumenAsistenciaPK.codCia = :codCia and r.resumenAsistenciaPK.secuencia = :secuencia"),
    @NamedQuery(name = "ResumenAsistencia.findByEmp", query = "SELECT r FROM ResumenAsistencia r WHERE r.resumenAsistenciaPK.codCia = :codCia and r.resumenAsistenciaPK.secuencia = :secuencia and r.resumenAsistenciaPK.codEmp = :codEmp"),
    @NamedQuery(name = "ResumenAsistencia.findBySecuencia", query = "SELECT r FROM ResumenAsistencia  r WHERE r.resumenAsistenciaPK.codCia = :codCia and  r.resumenAsistenciaPK.secuencia = :secuencia"),
    @NamedQuery(name = "ResumenAsistencia.findByCodEmp", query = "SELECT r FROM ResumenAsistencia r WHERE r.resumenAsistenciaPK.codEmp = :codEmp"),
    @NamedQuery(name = "ResumenAsistencia.findByDias", query = "SELECT r FROM ResumenAsistencia r WHERE r.dias = :dias")
    })
public class ResumenAsistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResumenAsistenciaPK resumenAsistenciaPK;
    @Column(name = "DIAS")
    private String dias;    
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
        @JoinColumn(name = "OBSERVACION", referencedColumnName = "ID")})

    @ManyToOne(optional = false)
    private Observaciones observaciones;    
    
    
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "resumenAsistencia")
    private List<Planilla> planilla;  
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    
    
    public ResumenAsistencia() {
    }

    public List<Planilla> getPlanilla() {
        return planilla;
    }

    public void setPlanilla(List<Planilla> planilla) {
        this.planilla = planilla;
    }

    
    public ResumenAsistencia(ResumenAsistenciaPK resumenAsistenciaPK) {
	this.resumenAsistenciaPK = resumenAsistenciaPK;
    }

    public ResumenAsistencia(short codCia, long secuencia, int codEmp) {
	this.resumenAsistenciaPK = new ResumenAsistenciaPK(codCia, secuencia, codEmp);
    }

    public ResumenAsistenciaPK getResumenAsistenciaPK() {
	return resumenAsistenciaPK;
    }

    public void setResumenAsistenciaPK(ResumenAsistenciaPK resumenAsistenciaPK) {
        
            try { 
                this.resumenAsistenciaPK = resumenAsistenciaPK;	
            }catch (Exception ex) {
                System.out.println(ex );
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
	
    }

    public Observaciones getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(Observaciones observaciones) {
        this.observaciones = observaciones;
    }

    
    public String getDias() {
	return dias;
    }

    public void setDias(String dias) {
	this.dias = dias;
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

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (resumenAsistenciaPK != null ? resumenAsistenciaPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof ResumenAsistencia)) {
	    return false;
	}
	ResumenAsistencia other = (ResumenAsistencia) object;
	if ((this.resumenAsistenciaPK == null && other.resumenAsistenciaPK != null) || (this.resumenAsistenciaPK != null && !this.resumenAsistenciaPK.equals(other.resumenAsistenciaPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.ResumenAsistencia[ resumenAsistenciaPK=" + resumenAsistenciaPK + " ]";
    }
    
}
