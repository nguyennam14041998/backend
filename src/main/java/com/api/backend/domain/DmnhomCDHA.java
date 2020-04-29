package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DmnhomCDHA.
 */
@Entity
@Table(name = "dmnhom_cdha")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DmnhomCDHA implements Serializable {

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

    @OneToMany(mappedBy = "dmnhomCDHA")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DmCDHA> dmCDHAS = new HashSet<>();

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

    public DmnhomCDHA ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public DmnhomCDHA ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getLoai() {
        return loai;
    }

    public DmnhomCDHA loai(Integer loai) {
        this.loai = loai;
        return this;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }

    public String getMaBH() {
        return maBH;
    }

    public DmnhomCDHA maBH(String maBH) {
        this.maBH = maBH;
        return this;
    }

    public void setMaBH(String maBH) {
        this.maBH = maBH;
    }

    public Set<DmCDHA> getDmCDHAS() {
        return dmCDHAS;
    }

    public DmnhomCDHA dmCDHAS(Set<DmCDHA> dmCDHAS) {
        this.dmCDHAS = dmCDHAS;
        return this;
    }

    public DmnhomCDHA addDmCDHA(DmCDHA dmCDHA) {
        this.dmCDHAS.add(dmCDHA);
        dmCDHA.setDmnhomCDHA(this);
        return this;
    }

    public DmnhomCDHA removeDmCDHA(DmCDHA dmCDHA) {
        this.dmCDHAS.remove(dmCDHA);
        dmCDHA.setDmnhomCDHA(null);
        return this;
    }

    public void setDmCDHAS(Set<DmCDHA> dmCDHAS) {
        this.dmCDHAS = dmCDHAS;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmnhomCDHA)) {
            return false;
        }
        return id != null && id.equals(((DmnhomCDHA) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DmnhomCDHA{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", loai=" + getLoai() +
            ", maBH='" + getMaBH() + "'" +
            "}";
    }
}
