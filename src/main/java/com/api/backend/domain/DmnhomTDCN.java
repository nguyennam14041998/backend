package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DmnhomTDCN.
 */
@Entity
@Table(name = "dmnhom_tdcn")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DmnhomTDCN implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "loai")
    private Integer loai;

    @Column(name = "ma_bh")
    private String maBH;

    @OneToMany(mappedBy = "dmnhomTDCN")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DmTDCN> dmTDCNS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public DmnhomTDCN ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public DmnhomTDCN ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getLoai() {
        return loai;
    }

    public DmnhomTDCN loai(Integer loai) {
        this.loai = loai;
        return this;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }

    public String getMaBH() {
        return maBH;
    }

    public DmnhomTDCN maBH(String maBH) {
        this.maBH = maBH;
        return this;
    }

    public void setMaBH(String maBH) {
        this.maBH = maBH;
    }

    public Set<DmTDCN> getDmTDCNS() {
        return dmTDCNS;
    }

    public DmnhomTDCN dmTDCNS(Set<DmTDCN> dmTDCNS) {
        this.dmTDCNS = dmTDCNS;
        return this;
    }

    public DmnhomTDCN addDmTDCN(DmTDCN dmTDCN) {
        this.dmTDCNS.add(dmTDCN);
        dmTDCN.setDmnhomTDCN(this);
        return this;
    }

    public DmnhomTDCN removeDmTDCN(DmTDCN dmTDCN) {
        this.dmTDCNS.remove(dmTDCN);
        dmTDCN.setDmnhomTDCN(null);
        return this;
    }

    public void setDmTDCNS(Set<DmTDCN> dmTDCNS) {
        this.dmTDCNS = dmTDCNS;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmnhomTDCN)) {
            return false;
        }
        return id != null && id.equals(((DmnhomTDCN) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DmnhomTDCN{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", loai=" + getLoai() +
            ", maBH='" + getMaBH() + "'" +
            "}";
    }
}
