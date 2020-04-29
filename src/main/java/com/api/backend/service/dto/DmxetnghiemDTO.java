package com.api.backend.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.api.backend.domain.Dmxetnghiem} entity.
 */
public class DmxetnghiemDTO implements Serializable {

    private Long id;

    private String ma;

    private String ten;

    private Integer cha;

    private String mota;

    private Integer gioitinh;

    private String canduoi;

    private String cantren;

    private String donvido;

    private String maByt;

    private String manhomBH;


    private Long dmnhomxetnghiemId;
    private String tenNhom;

    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }

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

    public Integer getCha() {
        return cha;
    }

    public void setCha(Integer cha) {
        this.cha = cha;
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

    public String getCanduoi() {
        return canduoi;
    }

    public void setCanduoi(String canduoi) {
        this.canduoi = canduoi;
    }

    public String getCantren() {
        return cantren;
    }

    public void setCantren(String cantren) {
        this.cantren = cantren;
    }

    public String getDonvido() {
        return donvido;
    }

    public void setDonvido(String donvido) {
        this.donvido = donvido;
    }

    public String getMaByt() {
        return maByt;
    }

    public void setMaByt(String maByt) {
        this.maByt = maByt;
    }

    public String getManhomBH() {
        return manhomBH;
    }

    public void setManhomBH(String manhomBH) {
        this.manhomBH = manhomBH;
    }

    public Long getDmnhomxetnghiemId() {
        return dmnhomxetnghiemId;
    }

    public void setDmnhomxetnghiemId(Long dmnhomxetnghiemId) {
        this.dmnhomxetnghiemId = dmnhomxetnghiemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DmxetnghiemDTO dmxetnghiemDTO = (DmxetnghiemDTO) o;
        if (dmxetnghiemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dmxetnghiemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DmxetnghiemDTO{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", cha=" + getCha() +
            ", mota='" + getMota() + "'" +
            ", gioitinh=" + getGioitinh() +
            ", canduoi='" + getCanduoi() + "'" +
            ", cantren='" + getCantren() + "'" +
            ", donvido='" + getDonvido() + "'" +
            ", maByt='" + getMaByt() + "'" +
            ", manhomBH='" + getManhomBH() + "'" +
            ", dmnhomxetnghiem=" + getDmnhomxetnghiemId() +
            "}";
    }
}
