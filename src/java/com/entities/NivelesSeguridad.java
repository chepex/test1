/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
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
 * @author dromero
 */
@Entity
@Table(name = "NIVELES_SEGURIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelesSeguridad.findAll", query = "SELECT n FROM NivelesSeguridad n"),
    @NamedQuery(name = "NivelesSeguridad.findByCodSeguridad", query = "SELECT n FROM NivelesSeguridad n WHERE n.nivelesSeguridadPK.codSeguridad = :codSeguridad"),
    @NamedQuery(name = "NivelesSeguridad.findByColor", query = "SELECT n FROM NivelesSeguridad n WHERE n.color = :color"),
    @NamedQuery(name = "NivelesSeguridad.findByDescripcion", query = "SELECT n FROM NivelesSeguridad n WHERE n.descripcion = :descripcion"),
    @NamedQuery(name = "NivelesSeguridad.findByBackgroundColor", query = "SELECT n FROM NivelesSeguridad n WHERE n.backgroundColor = :backgroundColor"),
    @NamedQuery(name = "NivelesSeguridad.findByCodCia", query = "SELECT n FROM NivelesSeguridad n WHERE n.nivelesSeguridadPK.codCia = :codCia")})
public class NivelesSeguridad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NivelesSeguridadPK nivelesSeguridadPK;
    @Size(max = 25)
    @Column(name = "COLOR")
    private String color;
    @Size(max = 75)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "BACKGROUND_COLOR")
    private String backgroundColor;

    public NivelesSeguridad() {
    }

    public NivelesSeguridad(NivelesSeguridadPK nivelesSeguridadPK) {
        this.nivelesSeguridadPK = nivelesSeguridadPK;
    }

    public NivelesSeguridad(short codSeguridad, short codCia) {
        this.nivelesSeguridadPK = new NivelesSeguridadPK(codSeguridad, codCia);
    }

    public NivelesSeguridadPK getNivelesSeguridadPK() {
        return nivelesSeguridadPK;
    }

    public void setNivelesSeguridadPK(NivelesSeguridadPK nivelesSeguridadPK) {
        this.nivelesSeguridadPK = nivelesSeguridadPK;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nivelesSeguridadPK != null ? nivelesSeguridadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelesSeguridad)) {
            return false;
        }
        NivelesSeguridad other = (NivelesSeguridad) object;
        if ((this.nivelesSeguridadPK == null && other.nivelesSeguridadPK != null) || (this.nivelesSeguridadPK != null && !this.nivelesSeguridadPK.equals(other.nivelesSeguridadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.NivelesSeguridad[ nivelesSeguridadPK=" + nivelesSeguridadPK + " ]";
    }
    
}
