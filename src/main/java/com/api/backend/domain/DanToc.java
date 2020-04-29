package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DanToc.
 */
@Entity
@Table(name = "dan_toc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DanToc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ma_dan_toc", nullable = false)
    private String maDanToc;

    @NotNull
    @Column(name = "ten_dan_toc", nullable = false)
    private String tenDanToc;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ma_bhyt")
    private String maBHYT;

    @Column(name = "ma_bhxh")
    private String maBHXH;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDanToc() {
        return maDanToc;
    }

    public DanToc maDanToc(String maDanToc) {
        this.maDanToc = maDanToc;
        return this;
    }

    public void setMaDanToc(String maDanToc) {
        this.maDanToc = maDanToc;
    }

    public String getTenDanToc() {
        return tenDanToc;
    }

    public DanToc tenDanToc(String tenDanToc) {
        this.tenDanToc = tenDanToc;
        return this;
    }

    public void setTenDanToc(String tenDanToc) {
        this.tenDanToc = tenDanToc;
    }

    public String getMoTa() {
        return moTa;
    }

    public DanToc moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMaBHYT() {
        return maBHYT;
    }

    public DanToc maBHYT(String maBHYT) {
        this.maBHYT = maBHYT;
        return this;
    }

    public void setMaBHYT(String maBHYT) {
        this.maBHYT = maBHYT;
    }

    public String getMaBHXH() {
        return maBHXH;
    }

    public DanToc maBHXH(String maBHXH) {
        this.maBHXH = maBHXH;
        return this;
    }

    public void setMaBHXH(String maBHXH) {
        this.maBHXH = maBHXH;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanToc)) {
            return false;
        }
        return id != null && id.equals(((DanToc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DanToc{" +
            "id=" + getId() +
            ", maDanToc='" + getMaDanToc() + "'" +
            ", tenDanToc='" + getTenDanToc() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", maBHYT='" + getMaBHYT() + "'" +
            ", maBHXH='" + getMaBHXH() + "'" +
            "}";
    }
}
