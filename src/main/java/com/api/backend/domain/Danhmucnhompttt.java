package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Danhmucnhompttt.
 */
@Entity
@Table(name = "danhmucnhompttt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Danhmucnhompttt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loai")
    private Integer loai;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @OneToMany(mappedBy = "danhmucnhompttt")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Danhmucpttt> danhmucpttts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoai() {
        return loai;
    }

    public Danhmucnhompttt loai(Integer loai) {
        this.loai = loai;
        return this;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }

    public String getMa() {
        return ma;
    }

    public Danhmucnhompttt ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public Danhmucnhompttt ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Set<Danhmucpttt> getDanhmucpttts() {
        return danhmucpttts;
    }

    public Danhmucnhompttt danhmucpttts(Set<Danhmucpttt> danhmucpttts) {
        this.danhmucpttts = danhmucpttts;
        return this;
    }

    public Danhmucnhompttt addDanhmucpttt(Danhmucpttt danhmucpttt) {
        this.danhmucpttts.add(danhmucpttt);
        danhmucpttt.setDanhmucnhompttt(this);
        return this;
    }

    public Danhmucnhompttt removeDanhmucpttt(Danhmucpttt danhmucpttt) {
        this.danhmucpttts.remove(danhmucpttt);
        danhmucpttt.setDanhmucnhompttt(null);
        return this;
    }

    public void setDanhmucpttts(Set<Danhmucpttt> danhmucpttts) {
        this.danhmucpttts = danhmucpttts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Danhmucnhompttt)) {
            return false;
        }
        return id != null && id.equals(((Danhmucnhompttt) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Danhmucnhompttt{" +
            "id=" + getId() +
            ", loai=" + getLoai() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            "}";
    }
}
