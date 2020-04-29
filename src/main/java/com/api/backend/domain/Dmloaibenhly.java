package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Dmloaibenhly.
 */
@Entity
@Table(name = "dmloaibenhly")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dmloaibenhly implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "mota")
    private String mota;

    @Column(name = "chuong")
    private String chuong;

    @OneToMany(mappedBy = "dmloaibenhly")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Dmnhombenhly> dmnhombenhlies = new HashSet<>();

    @OneToMany(mappedBy = "dmloaibenhly")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Dmbenhly> dmbenhlies = new HashSet<>();

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

    public Dmloaibenhly ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public Dmloaibenhly ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public Dmloaibenhly mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getChuong() {
        return chuong;
    }

    public Dmloaibenhly chuong(String chuong) {
        this.chuong = chuong;
        return this;
    }

    public void setChuong(String chuong) {
        this.chuong = chuong;
    }

    public Set<Dmnhombenhly> getDmnhombenhlies() {
        return dmnhombenhlies;
    }

    public Dmloaibenhly dmnhombenhlies(Set<Dmnhombenhly> dmnhombenhlies) {
        this.dmnhombenhlies = dmnhombenhlies;
        return this;
    }

    public Dmloaibenhly addDmnhombenhly(Dmnhombenhly dmnhombenhly) {
        this.dmnhombenhlies.add(dmnhombenhly);
        dmnhombenhly.setDmloaibenhly(this);
        return this;
    }

    public Dmloaibenhly removeDmnhombenhly(Dmnhombenhly dmnhombenhly) {
        this.dmnhombenhlies.remove(dmnhombenhly);
        dmnhombenhly.setDmloaibenhly(null);
        return this;
    }

    public void setDmnhombenhlies(Set<Dmnhombenhly> dmnhombenhlies) {
        this.dmnhombenhlies = dmnhombenhlies;
    }

    public Set<Dmbenhly> getDmbenhlies() {
        return dmbenhlies;
    }

    public Dmloaibenhly dmbenhlies(Set<Dmbenhly> dmbenhlies) {
        this.dmbenhlies = dmbenhlies;
        return this;
    }

    public Dmloaibenhly addDmbenhly(Dmbenhly dmbenhly) {
        this.dmbenhlies.add(dmbenhly);
        dmbenhly.setDmloaibenhly(this);
        return this;
    }

    public Dmloaibenhly removeDmbenhly(Dmbenhly dmbenhly) {
        this.dmbenhlies.remove(dmbenhly);
        dmbenhly.setDmloaibenhly(null);
        return this;
    }

    public void setDmbenhlies(Set<Dmbenhly> dmbenhlies) {
        this.dmbenhlies = dmbenhlies;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dmloaibenhly)) {
            return false;
        }
        return id != null && id.equals(((Dmloaibenhly) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dmloaibenhly{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", chuong='" + getChuong() + "'" +
            "}";
    }
}
