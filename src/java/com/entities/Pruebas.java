/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "PRUEBAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pruebas.findAll", query = "SELECT p FROM Pruebas p"),
    @NamedQuery(name = "Pruebas.findByV1", query = "SELECT p FROM Pruebas p WHERE p.v1 = :v1"),
    @NamedQuery(name = "Pruebas.findByV2", query = "SELECT p FROM Pruebas p WHERE p.v2 = :v2"),
    @NamedQuery(name = "Pruebas.findByV3", query = "SELECT p FROM Pruebas p WHERE p.v3 = :v3"),
    @NamedQuery(name = "Pruebas.findByV4", query = "SELECT p FROM Pruebas p WHERE p.v4 = :v4"),
    @NamedQuery(name = "Pruebas.findByV5", query = "SELECT p FROM Pruebas p WHERE p.v5 = :v5")})
public class Pruebas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "V1")
    private String v1;
    @Size(max = 250)
    @Column(name = "V2")
    private String v2;
    @Size(max = 250)
    @Column(name = "V3")
    private String v3;
    @Size(max = 250)
    @Column(name = "V4")
    private String v4;
    @Size(max = 250)
    @Column(name = "V5")
    private String v5;

    public Pruebas() {
    }

    public Pruebas(String v1) {
        this.v1 = v1;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4;
    }

    public String getV5() {
        return v5;
    }

    public void setV5(String v5) {
        this.v5 = v5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (v1 != null ? v1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pruebas)) {
            return false;
        }
        Pruebas other = (Pruebas) object;
        if ((this.v1 == null && other.v1 != null) || (this.v1 != null && !this.v1.equals(other.v1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Pruebas[ v1=" + v1 + " ]";
    }
    
}
