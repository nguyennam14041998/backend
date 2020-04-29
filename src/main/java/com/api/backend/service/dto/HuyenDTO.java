package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Huyen} entity.
 */
public class HuyenDTO implements Serializable {

    private Long id;

    private String ten;


    private Long tinhId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Long getTinhId() {
        return tinhId;
    }

    public void setTinhId(Long tinhId) {
        this.tinhId = tinhId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HuyenDTO huyenDTO = (HuyenDTO) o;
        if (huyenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), huyenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HuyenDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", tinh=" + getTinhId() +
            "}";
    }
}
