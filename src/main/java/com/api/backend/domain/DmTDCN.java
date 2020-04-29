package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DmTDCN.
 */
@Entity
@Table(name = "dm_tdcn")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DmTDCN implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "mota")
    private String mota;

    @Column(name = "gioitinh")
    private Integer gioitinh;

    @Column(name = "ma_byt")
    private String maBYT;

    @Column(name = "manhom_bh")
    private String manhomBH;

    @ManyToOne
    @JsonIgnoreProperties("dmTDCNS")
    private DmnhomTDCN dmnhomTDCN;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public DmTDCN ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public DmTDCN ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public DmTDCN mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Integer getGioitinh() {
        return gioitinh;
    }

    public DmTDCN gioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
        return this;
    }

    public void setGioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getMaBYT() {
        return maBYT;
    }

    public DmTDCN maBYT(String maBYT) {
        this.maBYT = maBYT;
        return this;
    }

    public void setMaBYT(String maBYT) {
        this.maBYT = maBYT;
    }

    public String getManhomBH() {
        return manhomBH;
    }

    public DmTDCN manhomBH(String manhomBH) {
        this.manhomBH = manhomBH;
        return this;
    }

    public void setManhomBH(String manhomBH) {
        this.manhomBH = manhomBH;
    }

    public DmnhomTDCN getDmnhomTDCN() {
        return dmnhomTDCN;
    }

    public DmTDCN dmnhomTDCN(DmnhomTDCN dmnhomTDCN) {
        this.dmnhomTDCN = dmnhomTDCN;
        return this;
    }

    public void setDmnhomTDCN(DmnhomTDCN dmnhomTDCN) {
        this.dmnhomTDCN = dmnhomTDCN;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmTDCN)) {
            return false;
        }
        return id != null && id.equals(((DmTDCN) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DmTDCN{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", mota='" + getMota() + "'" +
            ", gioitinh=" + getGioitinh() +
            ", maBYT='" + getMaBYT() + "'" +
            ", manhomBH='" + getManhomBH() + "'" +
            "}";
    }
}
