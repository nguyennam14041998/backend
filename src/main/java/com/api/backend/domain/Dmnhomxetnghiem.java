package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Dmnhomxetnghiem.
 */
@Entity
@Table(name = "dmnhomxetnghiem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dmnhomxetnghiem implements Serializable {

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

    @Column(name = "mabh")
    private String mabh;

    @OneToMany(mappedBy = "dmnhomxetnghiem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Dmxetnghiem> dmxetnghiems = new HashSet<>();

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

    public Dmnhomxetnghiem ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public Dmnhomxetnghiem ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getLoai() {
        return loai;
    }

    public Dmnhomxetnghiem loai(Integer loai) {
        this.loai = loai;
        return this;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }

    public String getMabh() {
        return mabh;
    }

    public Dmnhomxetnghiem mabh(String mabh) {
        this.mabh = mabh;
        return this;
    }

    public void setMabh(String mabh) {
        this.mabh = mabh;
    }

    public Set<Dmxetnghiem> getDmxetnghiems() {
        return dmxetnghiems;
    }

    public Dmnhomxetnghiem dmxetnghiems(Set<Dmxetnghiem> dmxetnghiems) {
        this.dmxetnghiems = dmxetnghiems;
        return this;
    }

    public Dmnhomxetnghiem addDmxetnghiem(Dmxetnghiem dmxetnghiem) {
        this.dmxetnghiems.add(dmxetnghiem);
        dmxetnghiem.setDmnhomxetnghiem(this);
        return this;
    }

    public Dmnhomxetnghiem removeDmxetnghiem(Dmxetnghiem dmxetnghiem) {
        this.dmxetnghiems.remove(dmxetnghiem);
        dmxetnghiem.setDmnhomxetnghiem(null);
        return this;
    }

    public void setDmxetnghiems(Set<Dmxetnghiem> dmxetnghiems) {
        this.dmxetnghiems = dmxetnghiems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dmnhomxetnghiem)) {
            return false;
        }
        return id != null && id.equals(((Dmnhomxetnghiem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dmnhomxetnghiem{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", loai=" + getLoai() +
            ", mabh='" + getMabh() + "'" +
            "}";
    }
}
