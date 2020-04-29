package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Dmgiaiphaubenh} entity.
 */
public class DmgiaiphaubenhDTO implements Serializable {

    private Long id;

    private String ma;

    private String ten;

    private String mota;

    private Integer gioitinh;

    private String maBYT;

    private String manhomBH;


    private Long dmnhomgiaiphaubenhId;

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

    public Integer getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getMaBYT() {
        return maBYT;
    }

    public void setMaBYT(String maBYT) {
        this.maBYT = maBYT;
    }

    public String getManhomBH() {
        return manhomBH;
    }

    public void setManhomBH(String manhomBH) {
        this.manhomBH = manhomBH;
    }

    public Long getDmnhomgiaiphaubenhId() {
        return dmnhomgiaiphaubenhId;
    }

    public void setDmnhomgiaiphaubenhId(Long dmnhomgiaiphaubenhId) {
        this.dmnhomgiaiphaubenhId = dmnhomgiaiphaubenhId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DmgiaiphaubenhDTO dmgiaiphaubenhDTO = (DmgiaiphaubenhDTO) o;
        if (dmgiaiphaubenhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dmgiaiphaubenhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DmgiaiphaubenhDTO{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", gioitinh=" + getGioitinh() +
            ", maBYT='" + getMaBYT() + "'" +
            ", manhomBH='" + getManhomBH() + "'" +
            ", dmnhomgiaiphaubenh=" + getDmnhomgiaiphaubenhId() +
            "}";
    }
}
