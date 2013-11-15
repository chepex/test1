/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

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
@Table(name = "CAT_DP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatDp.findAll", query = "SELECT c FROM CatDp c"),
    @NamedQuery(name = "CatDp.findByCodCia", query = "SELECT c FROM CatDp c WHERE c.catDpPK.codCia = :codCia"),
    @NamedQuery(name = "CatDp.findByCodCat", query = "SELECT c FROM CatDp c WHERE c.catDpPK.codCat = :codCat"),
    @NamedQuery(name = "CatDp.findByDescripcion", query = "SELECT c FROM CatDp c WHERE c.descripcion = :descripcion")})
public class CatDp implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CatDpPK catDpPK;
    @Size(max = 150)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catDp")
    private List<DeducPresta> deducPrestaList;
    @Column(name = "USUARIO")
    private String usuario;    
    @Column(name = "FECHA_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReg;

    public CatDp() {
    }

    public CatDp(CatDpPK catDpPK) {
	this.catDpPK = catDpPK;
    }

    public CatDp(short codCia, short codCat) {
	this.catDpPK = new CatDpPK(codCia, codCat);
    }

    public CatDpPK getCatDpPK() {
	return catDpPK;
    }

    public void setCatDpPK(CatDpPK catDpPK) {
	this.catDpPK = catDpPK;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    @XmlTransient
    public List<DeducPresta> getDeducPrestaList() {
	return deducPrestaList;
    }

    public void setDeducPrestaList(List<DeducPresta> deducPrestaList) {
	this.deducPrestaList = deducPrestaList;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (catDpPK != null ? catDpPK.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof CatDp)) {
	    return false;
	}
	CatDp other = (CatDp) object;
	if ((this.catDpPK == null && other.catDpPK != null) || (this.catDpPK != null && !this.catDpPK.equals(other.catDpPK))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "com.entities.CatDp[ catDpPK=" + catDpPK + " ]";
    }
    
}
