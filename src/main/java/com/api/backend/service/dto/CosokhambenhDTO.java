package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Cosokhambenh} entity.
 */
public class CosokhambenhDTO implements Serializable {

    private Long id;

    private String ma;

    private String ten;

    private String maKCBBD;

    private String hang;

    private String tuyen;

    private String loai;

    private String diachi;

    private String ghichu;


    private Long tinhId;

    private Long huyenId;

    private Long xaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMaKCBBD() {
        return maKCBBD;
    }

    public void setMaKCBBD(String maKCBBD) {
        this.maKCBBD = maKCBBD;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getTuyen() {
        return tuyen;
    }

    public void setTuyen(String tuyen) {
        this.tuyen = tuyen;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Long getTinhId() {
        return tinhId;
    }

    public void setTinhId(Long tinhId) {
        this.tinhId = tinhId;
    }

    public Long getHuyenId() {
        return huyenId;
    }

    public void setHuyenId(Long huyenId) {
        this.huyenId = huyenId;
    }

    public Long getXaId() {
        return xaId;
    }

    public void setXaId(Long xaId) {
        this.xaId = xaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CosokhambenhDTO cosokhambenhDTO = (CosokhambenhDTO) o;
        if (cosokhambenhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cosokhambenhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CosokhambenhDTO{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", maKCBBD='" + getMaKCBBD() + "'" +
            ", hang='" + getHang() + "'" +
            ", tuyen='" + getTuyen() + "'" +
            ", loai='" + getLoai() + "'" +
            ", diachi='" + getDiachi() + "'" +
            ", ghichu='" + getGhichu() + "'" +
            ", tinh=" + getTinhId() +
            ", huyen=" + getHuyenId() +
            ", xa=" + getXaId() +
            "}";
    }
}
