package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Huyen.
 */
@Entity
@Table(name = "huyen")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Huyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "huyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Xa> xas = new HashSet<>();

    @OneToMany(mappedBy = "huyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cosokhambenh> cosokhambenhs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("huyens")
    private Tinh tinh;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public Huyen ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Set<Xa> getXas() {
        return xas;
    }

    public Huyen xas(Set<Xa> xas) {
        this.xas = xas;
        return this;
    }

    public Huyen addXa(Xa xa) {
        this.xas.add(xa);
        xa.setHuyen(this);
        return this;
    }

    public Huyen removeXa(Xa xa) {
        this.xas.remove(xa);
        xa.setHuyen(null);
        return this;
    }

    public void setXas(Set<Xa> xas) {
        this.xas = xas;
    }

    public Set<Cosokhambenh> getCosokhambenhs() {
        return cosokhambenhs;
    }

    public Huyen cosokhambenhs(Set<Cosokhambenh> cosokhambenhs) {
        this.cosokhambenhs = cosokhambenhs;
        return this;
    }

    public Huyen addCosokhambenh(Cosokhambenh cosokhambenh) {
        this.cosokhambenhs.add(cosokhambenh);
        cosokhambenh.setHuyen(this);
        return this;
    }

    public Huyen removeCosokhambenh(Cosokhambenh cosokhambenh) {
        this.cosokhambenhs.remove(cosokhambenh);
        cosokhambenh.setHuyen(null);
        return this;
    }

    public void setCosokhambenhs(Set<Cosokhambenh> cosokhambenhs) {
        this.cosokhambenhs = cosokhambenhs;
    }

    public Tinh getTinh() {
        return tinh;
    }

    public Huyen tinh(Tinh tinh) {
        this.tinh = tinh;
        return this;
    }

    public void setTinh(Tinh tinh) {
        this.tinh = tinh;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Huyen)) {
            return false;
        }
        return id != null && id.equals(((Huyen) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Huyen{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            "}";
    }
}
