package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Dmnhombenhly} entity.
 */
public class DmnhombenhlyDTO implements Serializable {

    private Long id;

    private String ma;

    private String ten;

    private String mota;


    private Long dmloaibenhlyId;

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

    public Long getDmloaibenhlyId() {
        return dmloaibenhlyId;
    }

    public void setDmloaibenhlyId(Long dmloaibenhlyId) {
        this.dmloaibenhlyId = dmloaibenhlyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DmnhombenhlyDTO dmnhombenhlyDTO = (DmnhombenhlyDTO) o;
        if (dmnhombenhlyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dmnhombenhlyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DmnhombenhlyDTO{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", dmloaibenhly=" + getDmloaibenhlyId() +
            "}";
    }
}
