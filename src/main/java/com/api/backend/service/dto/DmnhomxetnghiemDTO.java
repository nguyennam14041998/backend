package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Dmnhomxetnghiem} entity.
 */
public class DmnhomxetnghiemDTO implements Serializable {

    private Long id;

    private String ma;

    private String ten;

    private Integer loai;

    private String mabh;


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

    public Integer getLoai() {
        return loai;
    }

    public void setLoai(Integer loai) {
        this.loai = loai;
    }

    public String getMabh() {
        return mabh;
    }

    public void setMabh(String mabh) {
        this.mabh = mabh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DmnhomxetnghiemDTO dmnhomxetnghiemDTO = (DmnhomxetnghiemDTO) o;
        if (dmnhomxetnghiemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dmnhomxetnghiemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DmnhomxetnghiemDTO{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", loai=" + getLoai() +
            ", mabh='" + getMabh() + "'" +
            "}";
    }
}
