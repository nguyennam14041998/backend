package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Dmbenhly.
 */
@Entity
@Table(name = "dmbenhly")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dmbenhly implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "i_cd")
    private String iCD;

    @Column(name = "ten_icd_10")
    private String tenICD10;

    @Column(name = "tentienganh")
    private String tentienganh;

    @Column(name = "tenthuonggoi")
    private String tenthuonggoi;

    @Column(name = "i_c_dcha")
    private String iCDcha;

    @Column(name = "ngay_ad")
    private LocalDate ngayAD;

    @Column(name = "trangthai")
    private Integer trangthai;

    @ManyToOne
    @JsonIgnoreProperties("dmbenhlies")
    private Dmloaibenhly dmloaibenhly;

    @ManyToOne
    @JsonIgnoreProperties("dmbenhlies")
    private Dmnhombenhly dmnhombenhly;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getiCD() {
        return iCD;
    }

    public Dmbenhly iCD(String iCD) {
        this.iCD = iCD;
        return this;
    }

    public void setiCD(String iCD) {
        this.iCD = iCD;
    }

    public String getTenICD10() {
        return tenICD10;
    }

    public Dmbenhly tenICD10(String tenICD10) {
        this.tenICD10 = tenICD10;
        return this;
    }

    public void setTenICD10(String tenICD10) {
        this.tenICD10 = tenICD10;
    }

    public String getTentienganh() {
        return tentienganh;
    }

    public Dmbenhly tentienganh(String tentienganh) {
        this.tentienganh = tentienganh;
        return this;
    }

    public void setTentienganh(String tentienganh) {
        this.tentienganh = tentienganh;
    }

    public String getTenthuonggoi() {
        return tenthuonggoi;
    }

    public Dmbenhly tenthuonggoi(String tenthuonggoi) {
        this.tenthuonggoi = tenthuonggoi;
        return this;
    }

    public void setTenthuonggoi(String tenthuonggoi) {
        this.tenthuonggoi = tenthuonggoi;
    }

    public String getiCDcha() {
        return iCDcha;
    }

    public Dmbenhly iCDcha(String iCDcha) {
        this.iCDcha = iCDcha;
        return this;
    }

    public void setiCDcha(String iCDcha) {
        this.iCDcha = iCDcha;
    }

    public LocalDate getNgayAD() {
        return ngayAD;
    }

    public Dmbenhly ngayAD(LocalDate ngayAD) {
        this.ngayAD = ngayAD;
        return this;
    }

    public void setNgayAD(LocalDate ngayAD) {
        this.ngayAD = ngayAD;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public Dmbenhly trangthai(Integer trangthai) {
        this.trangthai = trangthai;
        return this;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public Dmloaibenhly getDmloaibenhly() {
        return dmloaibenhly;
    }

    public Dmbenhly dmloaibenhly(Dmloaibenhly dmloaibenhly) {
        this.dmloaibenhly = dmloaibenhly;
        return this;
    }

    public void setDmloaibenhly(Dmloaibenhly dmloaibenhly) {
        this.dmloaibenhly = dmloaibenhly;
    }

    public Dmnhombenhly getDmnhombenhly() {
        return dmnhombenhly;
    }

    public Dmbenhly dmnhombenhly(Dmnhombenhly dmnhombenhly) {
        this.dmnhombenhly = dmnhombenhly;
        return this;
    }

    public void setDmnhombenhly(Dmnhombenhly dmnhombenhly) {
        this.dmnhombenhly = dmnhombenhly;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dmbenhly)) {
            return false;
        }
        return id != null && id.equals(((Dmbenhly) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dmbenhly{" +
            "id=" + getId() +
            ", iCD='" + getiCD() + "'" +
            ", tenICD10='" + getTenICD10() + "'" +
            ", tentienganh='" + getTentienganh() + "'" +
            ", tenthuonggoi='" + getTenthuonggoi() + "'" +
            ", iCDcha='" + getiCDcha() + "'" +
            ", ngayAD='" + getNgayAD() + "'" +
            ", trangthai=" + getTrangthai() +
            "}";
    }
}
