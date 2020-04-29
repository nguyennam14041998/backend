package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Tinh} entity.
 */
public class TinhDTO implements Serializable {

    private Long id;

    private String ten;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TinhDTO tinhDTO = (TinhDTO) o;
        if (tinhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tinhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TinhDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            "}";
    }
}
