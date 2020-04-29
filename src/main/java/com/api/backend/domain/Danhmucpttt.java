package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Danhmucpttt.
 */
@Entity
@Table(name = "danhmucpttt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Danhmucpttt implements Serializable {

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

    @Column(name = "mota")
    private String mota;

    @Column(name = "ma_byt")
    private String maByt;

    @ManyToOne
    @JsonIgnoreProperties("danhmucpttts")
    private Danhmucnhompttt danhmucnhompttt;

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

    public Danhmucpttt loai(Integer loai) {
        this.loai = loai;
        return this;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }

    public String getMa() {
        return ma;
    }

    public Danhmucpttt ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public Danhmucpttt ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public Danhmucpttt mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getMaByt() {
        return maByt;
    }

    public Danhmucpttt maByt(String maByt) {
        this.maByt = maByt;
        return this;
    }

    public void setMaByt(String maByt) {
        this.maByt = maByt;
    }

    public Danhmucnhompttt getDanhmucnhompttt() {
        return danhmucnhompttt;
    }

    public Danhmucpttt danhmucnhompttt(Danhmucnhompttt danhmucnhompttt) {
        this.danhmucnhompttt = danhmucnhompttt;
        return this;
    }

    public void setDanhmucnhompttt(Danhmucnhompttt danhmucnhompttt) {
        this.danhmucnhompttt = danhmucnhompttt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Danhmucpttt)) {
            return false;
        }
        return id != null && id.equals(((Danhmucpttt) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Danhmucpttt{" +
            "id=" + getId() +
            ", loai=" + getLoai() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", maByt='" + getMaByt() + "'" +
            "}";
    }
}
