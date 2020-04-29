package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Danhmucpttt} entity.
 */
public class DanhmucptttDTO implements Serializable {

    private Long id;

    private Integer loai;

    private String ma;

    private String ten;

    private String mota;

    private String maByt;


    private Long danhmucnhomptttId;
    private String tennhom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoai() {
        return loai;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
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

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getMaByt() {
        return maByt;
    }

    public void setMaByt(String maByt) {
        this.maByt = maByt;
    }

    public Long getDanhmucnhomptttId() {
        return danhmucnhomptttId;
    }

    public void setDanhmucnhomptttId(Long danhmucnhomptttId) {
        this.danhmucnhomptttId = danhmucnhomptttId;
    }
    public void setTennhom(String tennhom) {
        this.tennhom = tennhom;
    }

    public String getTennhom() {
        return tennhom;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanhmucptttDTO danhmucptttDTO = (DanhmucptttDTO) o;
        if (danhmucptttDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhmucptttDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhmucptttDTO{" +
            "id=" + getId() +
            ", loai=" + getLoai() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", maByt='" + getMaByt() + "'" +
            ", danhmucnhompttt=" + getDanhmucnhomptttId() +
            "}";
    }
}
