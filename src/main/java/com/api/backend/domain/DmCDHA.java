package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DmCDHA.
 */
@Entity
@Table(name = "dm_cdha")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DmCDHA implements Serializable {

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
    @JsonIgnoreProperties("dmCDHAS")
    private DmnhomCDHA dmnhomCDHA;

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

    public DmCDHA ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public DmCDHA ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public DmCDHA mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Integer getGioitinh() {
        return gioitinh;
    }

    public DmCDHA gioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
        return this;
    }

    public void setGioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getMaBYT() {
        return maBYT;
    }

    public DmCDHA maBYT(String maBYT) {
        this.maBYT = maBYT;
        return this;
    }

    public void setMaBYT(String maBYT) {
        this.maBYT = maBYT;
    }

    public String getManhomBH() {
        return manhomBH;
    }

    public DmCDHA manhomBH(String manhomBH) {
        this.manhomBH = manhomBH;
        return this;
    }

    public void setManhomBH(String manhomBH) {
        this.manhomBH = manhomBH;
    }

    public DmnhomCDHA getDmnhomCDHA() {
        return dmnhomCDHA;
    }

    public DmCDHA dmnhomCDHA(DmnhomCDHA dmnhomCDHA) {
        this.dmnhomCDHA = dmnhomCDHA;
        return this;
    }

    public void setDmnhomCDHA(DmnhomCDHA dmnhomCDHA) {
        this.dmnhomCDHA = dmnhomCDHA;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmCDHA)) {
            return false;
        }
        return id != null && id.equals(((DmCDHA) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DmCDHA{" +
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
