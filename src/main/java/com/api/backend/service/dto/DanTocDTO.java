package com.api.backend.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.DanToc} entity.
 */
public class DanTocDTO implements Serializable {

    private Long id;

    @NotNull
    private String maDanToc;

    @NotNull
    private String tenDanToc;

    private String moTa;

    private String maBHYT;

    private String maBHXH;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDanToc() {
        return maDanToc;
    }

    public void setMaDanToc(String maDanToc) {
        this.maDanToc = maDanToc;
    }

    public String getTenDanToc() {
        return tenDanToc;
    }

    public void setTenDanToc(String tenDanToc) {
        this.tenDanToc = tenDanToc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMaBHYT() {
        return maBHYT;
    }

    public void setMaBHYT(String maBHYT) {
        this.maBHYT = maBHYT;
    }

    public String getMaBHXH() {
        return maBHXH;
    }

    public void setMaBHXH(String maBHXH) {
        this.maBHXH = maBHXH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanTocDTO danTocDTO = (DanTocDTO) o;
        if (danTocDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danTocDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanTocDTO{" +
            "id=" + getId() +
            ", maDanToc='" + getMaDanToc() + "'" +
            ", tenDanToc='" + getTenDanToc() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", maBHYT='" + getMaBHYT() + "'" +
            ", maBHXH='" + getMaBHXH() + "'" +
            "}";
    }
}
