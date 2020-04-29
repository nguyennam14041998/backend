package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Danhmucnghenghiep.
 */
@Entity
@Table(name = "danhmucnghenghiep")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Danhmucnghenghiep implements Serializable {

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

    @Column(name = "b_yt")
    private String bYT;

    @Column(name = "sudung")
    private Integer sudung;

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

    public Danhmucnghenghiep ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public Danhmucnghenghiep ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public Danhmucnghenghiep mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getbYT() {
        return bYT;
    }

    public Danhmucnghenghiep bYT(String bYT) {
        this.bYT = bYT;
        return this;
    }

    public void setbYT(String bYT) {
        this.bYT = bYT;
    }

    public Integer getSudung() {
        return sudung;
    }

    public Danhmucnghenghiep sudung(Integer sudung) {
        this.sudung = sudung;
        return this;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Danhmucnghenghiep)) {
            return false;
        }
        return id != null && id.equals(((Danhmucnghenghiep) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Danhmucnghenghiep{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", bYT='" + getbYT() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
