package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Danhmucnhompttt} entity.
 */
public class DanhmucnhomptttDTO implements Serializable {

    private Long id;

    private Integer loai;

    private String ma;

    private String ten;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanhmucnhomptttDTO danhmucnhomptttDTO = (DanhmucnhomptttDTO) o;
        if (danhmucnhomptttDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhmucnhomptttDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhmucnhomptttDTO{" +
            "id=" + getId() +
            ", loai=" + getLoai() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            "}";
    }
}
