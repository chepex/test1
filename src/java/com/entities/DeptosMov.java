/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "DEPTOS_MOV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeptosMov.findAll", query = "SELECT d FROM DeptosMov d"),
    @NamedQuery(name = "DeptosMov.findByCodCia", query = "SELECT d FROM DeptosMov d WHERE d.deptosMovPK.codCia = :codCia"),
    @NamedQuery(name = "DeptosMov.findBySumaResta", query = "SELECT d FROM DeptosMov d WHERE d.deptosMovPK.codCia = :codCia and d.catDp.sumaResta = :sumaREsta"),
    @NamedQuery(name = "DeptosMov.findByCodDepto", query = "SELECT d FROM DeptosMov d WHERE d.deptosMovPK.codDepto = :codDepto"),
    @NamedQuery(name = "DeptosMov.findByCodCat", query = "SELECT d FROM DeptosMov d WHERE  d.deptosMovPK.codDepto = :codDepto and d.deptosMovPK.codCia  = :codCia and d.deptosMovPK.codCat = :codCat"),
    @NamedQuery(name = "DeptosMov.findByBcta1", query = "SELECT d FROM DeptosMov d WHERE d.bcta1 = :bcta1"),
    @NamedQuery(name = "DeptosMov.findByBcta2", query = "SELECT d FROM DeptosMov d WHERE d.bcta2 = :bcta2"),
    @NamedQuery(name = "DeptosMov.findByBcta3", query = "SELECT d FROM DeptosMov d WHERE d.bcta3 = :bcta3"),
    @NamedQuery(name = "DeptosMov.findByBcta4", query = "SELECT d FROM DeptosMov d WHERE d.bcta4 = :bcta4"),
    @NamedQuery(name = "DeptosMov.findByBcta5", query = "SELECT d FROM DeptosMov d WHERE d.bcta5 = :bcta5")})
public class DeptosMov implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DeptosMovPK deptosMovPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BCTA_1")
    private short bcta1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BCTA_2")
    private short bcta2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BCTA_3")
    private short bcta3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BCTA_4")
    private short bcta4;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BCTA_5")
    private short bcta5;
    @NotNull
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_DEPTO", referencedColumnName = "COD_DEPTO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Departamentos departamentos;
    @JoinColumns({
        @JoinColumn(name = "COD_CIA", referencedColumnName = "COD_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "COD_CAT", referencedColumnName = "COD_CAT", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CatDp catDp;

    public DeptosMov() {
    }

    public DeptosMov(DeptosMovPK deptosMovPK) {
        this.deptosMovPK = deptosMovPK;
    }

    public DeptosMov(DeptosMovPK deptosMovPK, short bcta1, short bcta2, short bcta3, short bcta4, short bcta5) {
        this.deptosMovPK = deptosMovPK;
        this.bcta1 = bcta1;
        this.bcta2 = bcta2;
        this.bcta3 = bcta3;
        this.bcta4 = bcta4;
        this.bcta5 = bcta5;
    }

    public DeptosMov(short codCia, short codDepto, short codCat) {
        this.deptosMovPK = new DeptosMovPK(codCia, codDepto, codCat);
    }

    public DeptosMovPK getDeptosMovPK() {
        return deptosMovPK;
    }

    public void setDeptosMovPK(DeptosMovPK deptosMovPK) {
        this.deptosMovPK = deptosMovPK;
    }

    public short getBcta1() {
        return bcta1;
    }

    public void setBcta1(short bcta1) {
        this.bcta1 = bcta1;
    }

    public short getBcta2() {
        return bcta2;
    }

    public void setBcta2(short bcta2) {
        this.bcta2 = bcta2;
    }

    public short getBcta3() {
        return bcta3;
    }

    public void setBcta3(short bcta3) {
        this.bcta3 = bcta3;
    }

    public short getBcta4() {
        return bcta4;
    }

    public void setBcta4(short bcta4) {
        this.bcta4 = bcta4;
    }

    public short getBcta5() {
        return bcta5;
    }

    public void setBcta5(short bcta5) {
        this.bcta5 = bcta5;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    public CatDp getCatDp() {
        return catDp;
    }

    public void setCatDp(CatDp catDp) {
        this.catDp = catDp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptosMovPK != null ? deptosMovPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeptosMov)) {
            return false;
        }
        DeptosMov other = (DeptosMov) object;
        if ((this.deptosMovPK == null && other.deptosMovPK != null) || (this.deptosMovPK != null && !this.deptosMovPK.equals(other.deptosMovPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.DeptosMov[ deptosMovPK=" + deptosMovPK + " ]";
    }
    
}
