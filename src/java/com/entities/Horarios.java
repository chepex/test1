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
@Table(name = "HORARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horarios.findAll", query = "SELECT h FROM Horarios h"),
    @NamedQuery(name = "Horarios.findByCodCia", query = "SELECT h FROM Horarios h WHERE h.horariosPK.codCia = :codCia"),
    @NamedQuery(name = "Horarios.findByCodHorario", query = "SELECT h FROM Horarios h WHERE h.horariosPK.codHorario = :codHorario"),
    @NamedQuery(name = "Horarios.findByCodDepto", query = "SELECT h FROM Horarios h WHERE h.horariosPK.codDepto = :codDepto"),
    @NamedQuery(name = "Horarios.findByEntrada", query = "SELECT h FROM Horarios h WHERE h.entrada = :entrada"),
    @NamedQuery(name = "Horarios.findBySalida", query = "SELECT h FROM Horarios h WHERE h.salida = :salida"),
    @NamedQuery(name = "Horarios.findByAlmuerzoEntrada", query = "SELECT h FROM Horarios h WHERE h.almuerzoEntrada = :almuerzoEntrada"),
    @NamedQuery(name = "Horarios.findByAlmuerzoSalida", query = "SELECT h FROM Horarios h WHERE h.almuerzoSalida = :almuerzoSalida"),
    @NamedQuery(name = "Horarios.findByUsuario", query = "SELECT h FROM Horarios h WHERE h.usuario = :usuario"),
    @NamedQuery(name = "Horarios.findByFechaReg", query = "SELECT h FROM Horarios h WHERE h.fechaReg = :fechaReg")})
public class Horarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorariosPK horariosPK;
    @Column(name = "ENTRADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;
    @Column(name = "SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date salida;
    @Column(name = "ALMUERZO_ENTRADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date almuerzoEntrada;
    @Column(name = "ALMUERZO_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date almuerzoSalida;
    @Size(max = 20)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_DEPTO", referencedColumnName = "COD_DEPTO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Departamentos departamentos;

    public Horarios() {
    }

    public Horarios(HorariosPK horariosPK) {
        this.horariosPK = horariosPK;
    }

    public Horarios(short codCia, short codHorario, short codDepto) {
        this.horariosPK = new HorariosPK(codCia, codHorario, codDepto);
    }

    public HorariosPK getHorariosPK() {
        return horariosPK;
    }

    public void setHorariosPK(HorariosPK horariosPK) {
        this.horariosPK = horariosPK;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public Date getAlmuerzoEntrada() {
        return almuerzoEntrada;
    }

    public void setAlmuerzoEntrada(Date almuerzoEntrada) {
        this.almuerzoEntrada = almuerzoEntrada;
    }

    public Date getAlmuerzoSalida() {
        return almuerzoSalida;
    }

    public void setAlmuerzoSalida(Date almuerzoSalida) {
        this.almuerzoSalida = almuerzoSalida;
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

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horariosPK != null ? horariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horarios)) {
            return false;
        }
        Horarios other = (Horarios) object;
        if ((this.horariosPK == null && other.horariosPK != null) || (this.horariosPK != null && !this.horariosPK.equals(other.horariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Horarios[ horariosPK=" + horariosPK + " ]";
    }
    
}
