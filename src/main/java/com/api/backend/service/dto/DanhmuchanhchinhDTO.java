package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Danhmuchanhchinh} entity.
 */
public class DanhmuchanhchinhDTO implements Serializable {

    private Long id;

    private String ten;

    private String tendaydu;

    private String tenviettat;

    private String tags;

    private Integer diadanhcha;

    private Integer cap;

    private Integer thanhthi;

    private Integer hoatdong;


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

    public String getTendaydu() {
        return tendaydu;
    }

    public void setTendaydu(String tendaydu) {
        this.tendaydu = tendaydu;
    }

    public String getTenviettat() {
        return tenviettat;
    }

    public void setTenviettat(String tenviettat) {
        this.tenviettat = tenviettat;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getDiadanhcha() {
        return diadanhcha;
    }

    public void setDiadanhcha(Integer diadanhcha) {
        this.diadanhcha = diadanhcha;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public Integer getThanhthi() {
        return thanhthi;
    }

    public void setThanhthi(Integer thanhthi) {
        this.thanhthi = thanhthi;
    }

    public Integer getHoatdong() {
        return hoatdong;
    }

    public void setHoatdong(Integer hoatdong) {
        this.hoatdong = hoatdong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanhmuchanhchinhDTO danhmuchanhchinhDTO = (DanhmuchanhchinhDTO) o;
        if (danhmuchanhchinhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhmuchanhchinhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhmuchanhchinhDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", tendaydu='" + getTendaydu() + "'" +
            ", tenviettat='" + getTenviettat() + "'" +
            ", tags='" + getTags() + "'" +
            ", diadanhcha=" + getDiadanhcha() +
            ", cap=" + getCap() +
            ", thanhthi=" + getThanhthi() +
            ", hoatdong=" + getHoatdong() +
            "}";
    }
}
