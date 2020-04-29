package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tinh.
 */
@Entity
@Table(name = "tinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tinh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "tinh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Huyen> huyens = new HashSet<>();

    @OneToMany(mappedBy = "tinh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cosokhambenh> cosokhambenhs = new HashSet<>();

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

    public Tinh ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Set<Huyen> getHuyens() {
        return huyens;
    }

    public Tinh huyens(Set<Huyen> huyens) {
        this.huyens = huyens;
        return this;
    }

    public Tinh addHuyen(Huyen huyen) {
        this.huyens.add(huyen);
        huyen.setTinh(this);
        return this;
    }

    public Tinh removeHuyen(Huyen huyen) {
        this.huyens.remove(huyen);
        huyen.setTinh(null);
        return this;
    }

    public void setHuyens(Set<Huyen> huyens) {
        this.huyens = huyens;
    }

    public Set<Cosokhambenh> getCosokhambenhs() {
        return cosokhambenhs;
    }

    public Tinh cosokhambenhs(Set<Cosokhambenh> cosokhambenhs) {
        this.cosokhambenhs = cosokhambenhs;
        return this;
    }

    public Tinh addCosokhambenh(Cosokhambenh cosokhambenh) {
        this.cosokhambenhs.add(cosokhambenh);
        cosokhambenh.setTinh(this);
        return this;
    }

    public Tinh removeCosokhambenh(Cosokhambenh cosokhambenh) {
        this.cosokhambenhs.remove(cosokhambenh);
        cosokhambenh.setTinh(null);
        return this;
    }

    public void setCosokhambenhs(Set<Cosokhambenh> cosokhambenhs) {
        this.cosokhambenhs = cosokhambenhs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tinh)) {
            return false;
        }
        return id != null && id.equals(((Tinh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tinh{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            "}";
    }
}
