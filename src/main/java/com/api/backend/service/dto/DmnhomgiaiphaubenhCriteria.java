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
 * Criteria class for the {@link com.api.backend.domain.Dmnhomgiaiphaubenh} entity. This class is used
 * in {@link com.api.backend.web.rest.DmnhomgiaiphaubenhResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dmnhomgiaiphaubenhs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DmnhomgiaiphaubenhCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ma;

    private StringFilter ten;

    private IntegerFilter loai;

    private StringFilter maBH;

    private LongFilter dmgiaiphaubenhId;

    public DmnhomgiaiphaubenhCriteria(){
    }

    public DmnhomgiaiphaubenhCriteria(DmnhomgiaiphaubenhCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ma = other.ma == null ? null : other.ma.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.loai = other.loai == null ? null : other.loai.copy();
        this.maBH = other.maBH == null ? null : other.maBH.copy();
        this.dmgiaiphaubenhId = other.dmgiaiphaubenhId == null ? null : other.dmgiaiphaubenhId.copy();
    }

    @Override
    public DmnhomgiaiphaubenhCriteria copy() {
        return new DmnhomgiaiphaubenhCriteria(this);
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

    public IntegerFilter getLoai() {
        return loai;
    }

    public void setLoai(IntegerFilter loai) {
        this.loai = loai;
    }

    public StringFilter getMaBH() {
        return maBH;
    }

    public void setMaBH(StringFilter maBH) {
        this.maBH = maBH;
    }

    public LongFilter getDmgiaiphaubenhId() {
        return dmgiaiphaubenhId;
    }

    public void setDmgiaiphaubenhId(LongFilter dmgiaiphaubenhId) {
        this.dmgiaiphaubenhId = dmgiaiphaubenhId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DmnhomgiaiphaubenhCriteria that = (DmnhomgiaiphaubenhCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ma, that.ma) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(loai, that.loai) &&
            Objects.equals(maBH, that.maBH) &&
            Objects.equals(dmgiaiphaubenhId, that.dmgiaiphaubenhId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ma,
        ten,
        loai,
        maBH,
        dmgiaiphaubenhId
        );
    }

    @Override
    public String toString() {
        return "DmnhomgiaiphaubenhCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ma != null ? "ma=" + ma + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (loai != null ? "loai=" + loai + ", " : "") +
                (maBH != null ? "maBH=" + maBH + ", " : "") +
                (dmgiaiphaubenhId != null ? "dmgiaiphaubenhId=" + dmgiaiphaubenhId + ", " : "") +
            "}";
    }

}
