package com.api.backend.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.api.backend.domain.Dmbenhly} entity. This class is used
 * in {@link com.api.backend.web.rest.DmbenhlyResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dmbenhlies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DmbenhlyCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter iCD;

    private StringFilter tenICD10;

    private StringFilter tentienganh;

    private StringFilter tenthuonggoi;

    private StringFilter iCDcha;

    private LocalDateFilter ngayAD;

    private IntegerFilter trangthai;

    private LongFilter dmloaibenhlyId;

    private LongFilter dmnhombenhlyId;

    public DmbenhlyCriteria(){
    }

    public DmbenhlyCriteria(DmbenhlyCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.iCD = other.iCD == null ? null : other.iCD.copy();
        this.tenICD10 = other.tenICD10 == null ? null : other.tenICD10.copy();
        this.tentienganh = other.tentienganh == null ? null : other.tentienganh.copy();
        this.tenthuonggoi = other.tenthuonggoi == null ? null : other.tenthuonggoi.copy();
        this.iCDcha = other.iCDcha == null ? null : other.iCDcha.copy();
        this.ngayAD = other.ngayAD == null ? null : other.ngayAD.copy();
        this.trangthai = other.trangthai == null ? null : other.trangthai.copy();
        this.dmloaibenhlyId = other.dmloaibenhlyId == null ? null : other.dmloaibenhlyId.copy();
        this.dmnhombenhlyId = other.dmnhombenhlyId == null ? null : other.dmnhombenhlyId.copy();
    }

    @Override
    public DmbenhlyCriteria copy() {
        return new DmbenhlyCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getiCD() {
        return iCD;
    }

    public void setiCD(StringFilter iCD) {
        this.iCD = iCD;
    }

    public StringFilter getTenICD10() {
        return tenICD10;
    }

    public void setTenICD10(StringFilter tenICD10) {
        this.tenICD10 = tenICD10;
    }

    public StringFilter getTentienganh() {
        return tentienganh;
    }

    public void setTentienganh(StringFilter tentienganh) {
        this.tentienganh = tentienganh;
    }

    public StringFilter getTenthuonggoi() {
        return tenthuonggoi;
    }

    public void setTenthuonggoi(StringFilter tenthuonggoi) {
        this.tenthuonggoi = tenthuonggoi;
    }

    public StringFilter getiCDcha() {
        return iCDcha;
    }

    public void setiCDcha(StringFilter iCDcha) {
        this.iCDcha = iCDcha;
    }

    public LocalDateFilter getNgayAD() {
        return ngayAD;
    }

    public void setNgayAD(LocalDateFilter ngayAD) {
        this.ngayAD = ngayAD;
    }

    public IntegerFilter getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(IntegerFilter trangthai) {
        this.trangthai = trangthai;
    }

    public LongFilter getDmloaibenhlyId() {
        return dmloaibenhlyId;
    }

    public void setDmloaibenhlyId(LongFilter dmloaibenhlyId) {
        this.dmloaibenhlyId = dmloaibenhlyId;
    }

    public LongFilter getDmnhombenhlyId() {
        return dmnhombenhlyId;
    }

    public void setDmnhombenhlyId(LongFilter dmnhombenhlyId) {
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
        final DmbenhlyCriteria that = (DmbenhlyCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(iCD, that.iCD) &&
            Objects.equals(tenICD10, that.tenICD10) &&
            Objects.equals(tentienganh, that.tentienganh) &&
            Objects.equals(tenthuonggoi, that.tenthuonggoi) &&
            Objects.equals(iCDcha, that.iCDcha) &&
            Objects.equals(ngayAD, that.ngayAD) &&
            Objects.equals(trangthai, that.trangthai) &&
            Objects.equals(dmloaibenhlyId, that.dmloaibenhlyId) &&
            Objects.equals(dmnhombenhlyId, that.dmnhombenhlyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        iCD,
        tenICD10,
        tentienganh,
        tenthuonggoi,
        iCDcha,
        ngayAD,
        trangthai,
        dmloaibenhlyId,
        dmnhombenhlyId
        );
    }

    @Override
    public String toString() {
        return "DmbenhlyCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (iCD != null ? "iCD=" + iCD + ", " : "") +
                (tenICD10 != null ? "tenICD10=" + tenICD10 + ", " : "") +
                (tentienganh != null ? "tentienganh=" + tentienganh + ", " : "") +
                (tenthuonggoi != null ? "tenthuonggoi=" + tenthuonggoi + ", " : "") +
                (iCDcha != null ? "iCDcha=" + iCDcha + ", " : "") +
                (ngayAD != null ? "ngayAD=" + ngayAD + ", " : "") +
                (trangthai != null ? "trangthai=" + trangthai + ", " : "") +
                (dmloaibenhlyId != null ? "dmloaibenhlyId=" + dmloaibenhlyId + ", " : "") +
                (dmnhombenhlyId != null ? "dmnhombenhlyId=" + dmnhombenhlyId + ", " : "") +
            "}";
    }

}
