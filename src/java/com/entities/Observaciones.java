/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import com.entities.ResumenAsistencia;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "OBSERVACIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Observaciones.findAll", query = "SELECT o FROM Observaciones o"),
    @NamedQuery(name = "Observaciones.findByCodCia", query = "SELECT o FROM Observaciones o WHERE o.observacionesPK.codCia = :codCia"),
    @NamedQuery(name = "Observaciones.findById", query = "SELECT o FROM Observaciones o WHERE o.observacionesPK.id = :id"),
    @NamedQuery(name = "Observaciones.findByDescripcion", query = "SELECT o FROM Observaciones o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Observaciones.findByAfp", query = "SELECT o FROM Observaciones o WHERE o.afp = :afp"),
    @NamedQuery(name = "Observaciones.findByIsss", query = "SELECT o FROM Observaciones o WHERE o.isss = :isss")})
public class Observaciones implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "observaciones")
    private List<ResumenAsistencia> resumenAsistenciaList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ObservacionesPK observacionesPK;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "AFP")
    private Short afp;
    @Column(name = "ISSS")
    private Short isss;
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;    

    public Observaciones() {
    }

    public Observaciones(ObservacionesPK observacionesPK) {
        this.observacionesPK = observacionesPK;
    }

    public Observaciones(short codCia, short id) {
        this.observacionesPK = new ObservacionesPK(codCia, id);
    }

    public ObservacionesPK getObservacionesPK() {
        return observacionesPK;
    }

    public void setObservacionesPK(ObservacionesPK observacionesPK) {
        this.observacionesPK = observacionesPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Short getAfp() {
        return afp;
    }

    public void setAfp(Short afp) {
        this.afp = afp;
    }

    public Short getIsss() {
        return isss;
    }

    public void setIsss(Short isss) {
        this.isss = isss;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (observacionesPK != null ? observacionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Observaciones)) {
            return false;
        }
        Observaciones other = (Observaciones) object;
        if ((this.observacionesPK == null && other.observacionesPK != null) || (this.observacionesPK != null && !this.observacionesPK.equals(other.observacionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Observaciones[ observacionesPK=" + observacionesPK + " ]";
        }

    @XmlTransient
    public List<ResumenAsistencia> getResumenAsistenciaList() {
        return resumenAsistenciaList;
    }

    public void setResumenAsistenciaList(List<ResumenAsistencia> resumenAsistenciaList) {
        this.resumenAsistenciaList = resumenAsistenciaList;
    }
    
}
