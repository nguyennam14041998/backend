package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Xa.
 */
@Entity
@Table(name = "xa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Xa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "xa")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cosokhambenh> cosokhambenhs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("xas")
    private Huyen huyen;

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

    public Xa ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Set<Cosokhambenh> getCosokhambenhs() {
        return cosokhambenhs;
    }

    public Xa cosokhambenhs(Set<Cosokhambenh> cosokhambenhs) {
        this.cosokhambenhs = cosokhambenhs;
        return this;
    }

    public Xa addCosokhambenh(Cosokhambenh cosokhambenh) {
        this.cosokhambenhs.add(cosokhambenh);
        cosokhambenh.setXa(this);
        return this;
    }

    public Xa removeCosokhambenh(Cosokhambenh cosokhambenh) {
        this.cosokhambenhs.remove(cosokhambenh);
        cosokhambenh.setXa(null);
        return this;
    }

    public void setCosokhambenhs(Set<Cosokhambenh> cosokhambenhs) {
        this.cosokhambenhs = cosokhambenhs;
    }

    public Huyen getHuyen() {
        return huyen;
    }

    public Xa huyen(Huyen huyen) {
        this.huyen = huyen;
        return this;
    }

    public void setHuyen(Huyen huyen) {
        this.huyen = huyen;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Xa)) {
            return false;
        }
        return id != null && id.equals(((Xa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Xa{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            "}";
    }
}
