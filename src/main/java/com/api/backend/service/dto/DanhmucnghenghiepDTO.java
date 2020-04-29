package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Danhmucnghenghiep} entity.
 */
public class DanhmucnghenghiepDTO implements Serializable {

    private Long id;

    private String ma;

    private String ten;

    private String mota;

    private String bYT;

    private Integer sudung;


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

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getbYT() {
        return bYT;
    }

    public void setbYT(String bYT) {
        this.bYT = bYT;
    }

    public Integer getSudung() {
        return sudung;
    }

    public void setSudung(Integer sudung) {
        this.sudung = sudung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanhmucnghenghiepDTO danhmucnghenghiepDTO = (DanhmucnghenghiepDTO) o;
        if (danhmucnghenghiepDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhmucnghenghiepDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhmucnghenghiepDTO{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", bYT='" + getbYT() + "'" +
            ", sudung=" + getSudung() +
            "}";
    }
}
