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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "SEQUENCE_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SequenceTable.findAll", query = "SELECT s FROM SequenceTable s"),
    @NamedQuery(name = "SequenceTable.findBySeqName", query = "SELECT s FROM SequenceTable s WHERE s.sequenceTablePK.seqName = :seqName and  s.sequenceTablePK.codCia = :codCia"),
    @NamedQuery(name = "SequenceTable.findBySeqCount", query = "SELECT s FROM SequenceTable s WHERE s.seqCount = :seqCount"),
    @NamedQuery(name = "SequenceTable.findByCodCia", query = "SELECT s FROM SequenceTable s WHERE s.sequenceTablePK.codCia = :codCia")})
public class SequenceTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SequenceTablePK sequenceTablePK;
    @Column(name = "SEQ_COUNT")
    private Long seqCount;

    public SequenceTable() {
    }

    public SequenceTable(SequenceTablePK sequenceTablePK) {
        this.sequenceTablePK = sequenceTablePK;
    }

    public SequenceTable(String seqName, short codCia) {
        this.sequenceTablePK = new SequenceTablePK(seqName, codCia);
    }

    public SequenceTablePK getSequenceTablePK() {
        return sequenceTablePK;
    }

    public void setSequenceTablePK(SequenceTablePK sequenceTablePK) {
        this.sequenceTablePK = sequenceTablePK;
    }

    public Long getSeqCount() {
        return seqCount;
    }

    public void setSeqCount(Long seqCount) {
        this.seqCount = seqCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sequenceTablePK != null ? sequenceTablePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SequenceTable)) {
            return false;
        }
        SequenceTable other = (SequenceTable) object;
        if ((this.sequenceTablePK == null && other.sequenceTablePK != null) || (this.sequenceTablePK != null && !this.sequenceTablePK.equals(other.sequenceTablePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.SequenceTable[ sequenceTablePK=" + sequenceTablePK + " ]";
    }
    
}
