package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Cosokhambenh.
 */
@Entity
@Table(name = "cosokhambenh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cosokhambenh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ma_kcbbd")
    private String maKCBBD;

    @Column(name = "hang")
    private String hang;

    @Column(name = "tuyen")
    private String tuyen;

    @Column(name = "loai")
    private String loai;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "ghichu")
    private String ghichu;

    @ManyToOne
    @JsonIgnoreProperties("cosokhambenhs")
    private Tinh tinh;

    @ManyToOne
    @JsonIgnoreProperties("cosokhambenhs")
    private Huyen huyen;

    @ManyToOne
    @JsonIgnoreProperties("cosokhambenhs")
    private Xa xa;

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

    public Cosokhambenh ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public Cosokhambenh ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMaKCBBD() {
        return maKCBBD;
    }

    public Cosokhambenh maKCBBD(String maKCBBD) {
        this.maKCBBD = maKCBBD;
        return this;
    }

    public void setMaKCBBD(String maKCBBD) {
        this.maKCBBD = maKCBBD;
    }

    public String getHang() {
        return hang;
    }

    public Cosokhambenh hang(String hang) {
        this.hang = hang;
        return this;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getTuyen() {
        return tuyen;
    }

    public Cosokhambenh tuyen(String tuyen) {
        this.tuyen = tuyen;
        return this;
    }

    public void setTuyen(String tuyen) {
        this.tuyen = tuyen;
    }

    public String getLoai() {
        return loai;
    }

    public Cosokhambenh loai(String loai) {
        this.loai = loai;
        return this;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getDiachi() {
        return diachi;
    }

    public Cosokhambenh diachi(String diachi) {
        this.diachi = diachi;
        return this;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public Cosokhambenh ghichu(String ghichu) {
        this.ghichu = ghichu;
        return this;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Tinh getTinh() {
        return tinh;
    }

    public Cosokhambenh tinh(Tinh tinh) {
        this.tinh = tinh;
        return this;
    }

    public void setTinh(Tinh tinh) {
        this.tinh = tinh;
    }

    public Huyen getHuyen() {
        return huyen;
    }

    public Cosokhambenh huyen(Huyen huyen) {
        this.huyen = huyen;
        return this;
    }

    public void setHuyen(Huyen huyen) {
        this.huyen = huyen;
    }

    public Xa getXa() {
        return xa;
    }

    public Cosokhambenh xa(Xa xa) {
        this.xa = xa;
        return this;
    }

    public void setXa(Xa xa) {
        this.xa = xa;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cosokhambenh)) {
            return false;
        }
        return id != null && id.equals(((Cosokhambenh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cosokhambenh{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", maKCBBD='" + getMaKCBBD() + "'" +
            ", hang='" + getHang() + "'" +
            ", tuyen='" + getTuyen() + "'" +
            ", loai='" + getLoai() + "'" +
            ", diachi='" + getDiachi() + "'" +
            ", ghichu='" + getGhichu() + "'" +
            "}";
    }
}
