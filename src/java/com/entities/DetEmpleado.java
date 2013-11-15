/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "DET_EMPLEADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetEmpleado.findAll", query = "SELECT d FROM DetEmpleado d"),
    @NamedQuery(name = "DetEmpleado.findByCodCia", query = "SELECT d FROM DetEmpleado d WHERE d.detEmpleadoPK.codCia = :codCia"),
    @NamedQuery(name = "DetEmpleado.findByCodEmp", query = "SELECT d FROM DetEmpleado d WHERE d.detEmpleadoPK.codEmp = :codEmp and d.detEmpleadoPK.codCia = :codCia"),
    @NamedQuery(name = "DetEmpleado.findByAfp", query = "SELECT d FROM DetEmpleado d WHERE d.detEmpleadoPK.codEmp = :codEmp and d.deducPresta.afp = :afp and d.detEmpleadoPK.codCia = :codCia"),    
    @NamedQuery(name = "DetEmpleado.findByCodDp", query = "SELECT d FROM DetEmpleado d WHERE d.detEmpleadoPK.codDp = :codDp"),
    @NamedQuery(name = "DetEmpleado.findByUsuario", query = "SELECT d FROM DetEmpleado d WHERE d.usuario = :usuario"),
    @NamedQuery(name = "DetEmpleado.findByFechaReg", query = "SELECT d FROM DetEmpleado d WHERE d.fechaReg = :fechaReg")})
public class DetEmpleado implements Serializable {
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_DP", referencedColumnName = "COD_DP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private DeducPresta deducPresta;    
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_EMP", referencedColumnName = "COD_EMP", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Empleados empleados;    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetEmpleadoPK detEmpleadoPK;
    @Size(max = 20)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;

    public DetEmpleado() {
    }

    public DetEmpleado(DetEmpleadoPK detEmpleadoPK) {
        this.detEmpleadoPK = detEmpleadoPK;
    }

    public DetEmpleado(short codCia, int codEmp, short codDp) {
        this.detEmpleadoPK = new DetEmpleadoPK(codCia, codEmp, codDp);
    }

    public DetEmpleadoPK getDetEmpleadoPK() {
        return detEmpleadoPK;
    }

    public void setDetEmpleadoPK(DetEmpleadoPK detEmpleadoPK) {
        this.detEmpleadoPK = detEmpleadoPK;
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
        hash += (detEmpleadoPK != null ? detEmpleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetEmpleado)) {
            return false;
        }
        DetEmpleado other = (DetEmpleado) object;
        if ((this.detEmpleadoPK == null && other.detEmpleadoPK != null) || (this.detEmpleadoPK != null && !this.detEmpleadoPK.equals(other.detEmpleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.DetEmpleado[ detEmpleadoPK=" + detEmpleadoPK + " ]";
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public DeducPresta getDeducPresta() {
        return deducPresta;
    }

    public void setDeducPresta(DeducPresta deducPresta) {
        this.deducPresta = deducPresta;
    }    
}
