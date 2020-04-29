package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Dmnhombenhly.
 */
@Entity
@Table(name = "dmnhombenhly")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dmnhombenhly implements Serializable {

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

    @OneToMany(mappedBy = "dmnhombenhly")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Dmbenhly> dmbenhlies = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("dmnhombenhlies")
    private Dmloaibenhly dmloaibenhly;

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

    public Dmnhombenhly ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public Dmnhombenhly ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public Dmnhombenhly mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Set<Dmbenhly> getDmbenhlies() {
        return dmbenhlies;
    }

    public Dmnhombenhly dmbenhlies(Set<Dmbenhly> dmbenhlies) {
        this.dmbenhlies = dmbenhlies;
        return this;
    }

    public Dmnhombenhly addDmbenhly(Dmbenhly dmbenhly) {
        this.dmbenhlies.add(dmbenhly);
        dmbenhly.setDmnhombenhly(this);
        return this;
    }

    public Dmnhombenhly removeDmbenhly(Dmbenhly dmbenhly) {
        this.dmbenhlies.remove(dmbenhly);
        dmbenhly.setDmnhombenhly(null);
        return this;
    }

    public void setDmbenhlies(Set<Dmbenhly> dmbenhlies) {
        this.dmbenhlies = dmbenhlies;
    }

    public Dmloaibenhly getDmloaibenhly() {
        return dmloaibenhly;
    }

    public Dmnhombenhly dmloaibenhly(Dmloaibenhly dmloaibenhly) {
        this.dmloaibenhly = dmloaibenhly;
        return this;
    }

    public void setDmloaibenhly(Dmloaibenhly dmloaibenhly) {
        this.dmloaibenhly = dmloaibenhly;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dmnhombenhly)) {
            return false;
        }
        return id != null && id.equals(((Dmnhombenhly) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dmnhombenhly{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            "}";
    }
}
