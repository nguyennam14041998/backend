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

/**
 * Criteria class for the {@link com.api.backend.domain.DmCDHA} entity. This class is used
 * in {@link com.api.backend.web.rest.DmCDHAResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dm-cdhas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DmCDHACriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ma;

    private StringFilter ten;

    private StringFilter mota;

    private IntegerFilter gioitinh;

    private StringFilter maBYT;

    private StringFilter manhomBH;

    private LongFilter dmnhomCDHAId;

    public DmCDHACriteria(){
    }

    public DmCDHACriteria(DmCDHACriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ma = other.ma == null ? null : other.ma.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.mota = other.mota == null ? null : other.mota.copy();
        this.gioitinh = other.gioitinh == null ? null : other.gioitinh.copy();
        this.maBYT = other.maBYT == null ? null : other.maBYT.copy();
        this.manhomBH = other.manhomBH == null ? null : other.manhomBH.copy();
        this.dmnhomCDHAId = other.dmnhomCDHAId == null ? null : other.dmnhomCDHAId.copy();
    }

    @Override
    public DmCDHACriteria copy() {
        return new DmCDHACriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMa() {
        return ma;
    }

    public void setMa(StringFilter ma) {
        this.ma = ma;
    }

    public StringFilter getTen() {
        return ten;
    }

    public void setTen(StringFilter ten) {
        this.ten = ten;
    }

    public StringFilter getMota() {
        return mota;
    }

    public void setMota(StringFilter mota) {
        this.mota = mota;
    }

    public IntegerFilter getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(IntegerFilter gioitinh) {
        this.gioitinh = gioitinh;
    }

    public StringFilter getMaBYT() {
        return maBYT;
    }

    public void setMaBYT(StringFilter maBYT) {
        this.maBYT = maBYT;
    }

    public StringFilter getManhomBH() {
        return manhomBH;
    }

    public void setManhomBH(StringFilter manhomBH) {
        this.manhomBH = manhomBH;
    }

    public LongFilter getDmnhomCDHAId() {
        return dmnhomCDHAId;
    }

    public void setDmnhomCDHAId(LongFilter dmnhomCDHAId) {
        this.dmnhomCDHAId = dmnhomCDHAId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DmCDHACriteria that = (DmCDHACriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ma, that.ma) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(mota, that.mota) &&
            Objects.equals(gioitinh, that.gioitinh) &&
            Objects.equals(maBYT, that.maBYT) &&
            Objects.equals(manhomBH, that.manhomBH) &&
            Objects.equals(dmnhomCDHAId, that.dmnhomCDHAId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ma,
        ten,
        mota,
        gioitinh,
        maBYT,
        manhomBH,
        dmnhomCDHAId
        );
    }

    @Override
    public String toString() {
        return "DmCDHACriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ma != null ? "ma=" + ma + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (mota != null ? "mota=" + mota + ", " : "") +
                (gioitinh != null ? "gioitinh=" + gioitinh + ", " : "") +
                (maBYT != null ? "maBYT=" + maBYT + ", " : "") +
                (manhomBH != null ? "manhomBH=" + manhomBH + ", " : "") +
                (dmnhomCDHAId != null ? "dmnhomCDHAId=" + dmnhomCDHAId + ", " : "") +
            "}";
    }

}
