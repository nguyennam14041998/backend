package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Dmnhomgiaiphaubenh.
 */
@Entity
@Table(name = "dmnhomgiaiphaubenh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dmnhomgiaiphaubenh implements Serializable {

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

    @OneToMany(mappedBy = "dmnhomgiaiphaubenh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Dmgiaiphaubenh> dmgiaiphaubenhs = new HashSet<>();

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

    public Dmnhomgiaiphaubenh ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public Dmnhomgiaiphaubenh ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getLoai() {
        return loai;
    }

    public Dmnhomgiaiphaubenh loai(Integer loai) {
        this.loai = loai;
        return this;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }

    public String getMaBH() {
        return maBH;
    }

    public Dmnhomgiaiphaubenh maBH(String maBH) {
        this.maBH = maBH;
        return this;
    }

    public void setMaBH(String maBH) {
        this.maBH = maBH;
    }

    public Set<Dmgiaiphaubenh> getDmgiaiphaubenhs() {
        return dmgiaiphaubenhs;
    }

    public Dmnhomgiaiphaubenh dmgiaiphaubenhs(Set<Dmgiaiphaubenh> dmgiaiphaubenhs) {
        this.dmgiaiphaubenhs = dmgiaiphaubenhs;
        return this;
    }

    public Dmnhomgiaiphaubenh addDmgiaiphaubenh(Dmgiaiphaubenh dmgiaiphaubenh) {
        this.dmgiaiphaubenhs.add(dmgiaiphaubenh);
        dmgiaiphaubenh.setDmnhomgiaiphaubenh(this);
        return this;
    }

    public Dmnhomgiaiphaubenh removeDmgiaiphaubenh(Dmgiaiphaubenh dmgiaiphaubenh) {
        this.dmgiaiphaubenhs.remove(dmgiaiphaubenh);
        dmgiaiphaubenh.setDmnhomgiaiphaubenh(null);
        return this;
    }

    public void setDmgiaiphaubenhs(Set<Dmgiaiphaubenh> dmgiaiphaubenhs) {
        this.dmgiaiphaubenhs = dmgiaiphaubenhs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dmnhomgiaiphaubenh)) {
            return false;
        }
        return id != null && id.equals(((Dmnhomgiaiphaubenh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dmnhomgiaiphaubenh{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", loai=" + getLoai() +
            ", maBH='" + getMaBH() + "'" +
            "}";
    }
}
