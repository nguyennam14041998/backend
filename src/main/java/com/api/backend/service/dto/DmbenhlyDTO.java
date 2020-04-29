package com.api.backend.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Dmbenhly} entity.
 */
public class DmbenhlyDTO implements Serializable {

    private Long id;

    private String iCD;

    private String tenICD10;

    private String tentienganh;

    private String tenthuonggoi;

    private String iCDcha;

    private LocalDate ngayAD;

    private Integer trangthai;


    private Long dmloaibenhlyId;

    private Long dmnhombenhlyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getiCD() {
        return iCD;
    }

    public void setiCD(String iCD) {
        this.iCD = iCD;
    }

    public String getTenICD10() {
        return tenICD10;
    }

    public void setTenICD10(String tenICD10) {
        this.tenICD10 = tenICD10;
    }

    public String getTentienganh() {
        return tentienganh;
    }

    public void setTentienganh(String tentienganh) {
        this.tentienganh = tentienganh;
    }

    public String getTenthuonggoi() {
        return tenthuonggoi;
    }

    public void setTenthuonggoi(String tenthuonggoi) {
        this.tenthuonggoi = tenthuonggoi;
    }

    public String getiCDcha() {
        return iCDcha;
    }

    public void setiCDcha(String iCDcha) {
        this.iCDcha = iCDcha;
    }

    public LocalDate getNgayAD() {
        return ngayAD;
    }

    public void setNgayAD(LocalDate ngayAD) {
        this.ngayAD = ngayAD;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public Long getDmloaibenhlyId() {
        return dmloaibenhlyId;
    }

    public void setDmloaibenhlyId(Long dmloaibenhlyId) {
        this.dmloaibenhlyId = dmloaibenhlyId;
    }

    public Long getDmnhombenhlyId() {
        return dmnhombenhlyId;
    }

    public void setDmnhombenhlyId(Long dmnhombenhlyId) {
        this.dmnhombenhlyId = dmnhombenhlyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DmbenhlyDTO dmbenhlyDTO = (DmbenhlyDTO) o;
        if (dmbenhlyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dmbenhlyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DmbenhlyDTO{" +
            "id=" + getId() +
            ", iCD='" + getiCD() + "'" +
            ", tenICD10='" + getTenICD10() + "'" +
            ", tentienganh='" + getTentienganh() + "'" +
            ", tenthuonggoi='" + getTenthuonggoi() + "'" +
            ", iCDcha='" + getiCDcha() + "'" +
            ", ngayAD='" + getNgayAD() + "'" +
            ", trangthai=" + getTrangthai() +
            ", dmloaibenhly=" + getDmloaibenhlyId() +
            ", dmnhombenhly=" + getDmnhombenhlyId() +
            "}";
    }
}
