package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Xa} entity.
 */
public class XaDTO implements Serializable {

    private Long id;

    private String ten;


    private Long huyenId;

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

    public Long getHuyenId() {
        return huyenId;
    }

    public void setHuyenId(Long huyenId) {
        this.huyenId = huyenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        XaDTO xaDTO = (XaDTO) o;
        if (xaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), xaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "XaDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", huyen=" + getHuyenId() +
            "}";
    }
}
