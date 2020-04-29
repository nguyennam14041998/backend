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
 * Criteria class for the {@link com.api.backend.domain.DanToc} entity. This class is used
 * in {@link com.api.backend.web.rest.DanTocResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dan-tocs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DanTocCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maDanToc;

    private StringFilter tenDanToc;

    private StringFilter moTa;

    private StringFilter maBHYT;

    private StringFilter maBHXH;

    public DanTocCriteria(){
    }

    public DanTocCriteria(DanTocCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maDanToc = other.maDanToc == null ? null : other.maDanToc.copy();
        this.tenDanToc = other.tenDanToc == null ? null : other.tenDanToc.copy();
        this.moTa = other.moTa == null ? null : other.moTa.copy();
        this.maBHYT = other.maBHYT == null ? null : other.maBHYT.copy();
        this.maBHXH = other.maBHXH == null ? null : other.maBHXH.copy();
    }

    @Override
    public DanTocCriteria copy() {
        return new DanTocCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaDanToc() {
        return maDanToc;
    }

    public void setMaDanToc(StringFilter maDanToc) {
        this.maDanToc = maDanToc;
    }

    public StringFilter getTenDanToc() {
        return tenDanToc;
    }

    public void setTenDanToc(StringFilter tenDanToc) {
        this.tenDanToc = tenDanToc;
    }

    public StringFilter getMoTa() {
        return moTa;
    }

    public void setMoTa(StringFilter moTa) {
        this.moTa = moTa;
    }

    public StringFilter getMaBHYT() {
        return maBHYT;
    }

    public void setMaBHYT(StringFilter maBHYT) {
        this.maBHYT = maBHYT;
    }

    public StringFilter getMaBHXH() {
        return maBHXH;
    }

    public void setMaBHXH(StringFilter maBHXH) {
        this.maBHXH = maBHXH;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DanTocCriteria that = (DanTocCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maDanToc, that.maDanToc) &&
            Objects.equals(tenDanToc, that.tenDanToc) &&
            Objects.equals(moTa, that.moTa) &&
            Objects.equals(maBHYT, that.maBHYT) &&
            Objects.equals(maBHXH, that.maBHXH);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maDanToc,
        tenDanToc,
        moTa,
        maBHYT,
        maBHXH
        );
    }

    @Override
    public String toString() {
        return "DanTocCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maDanToc != null ? "maDanToc=" + maDanToc + ", " : "") +
                (tenDanToc != null ? "tenDanToc=" + tenDanToc + ", " : "") +
                (moTa != null ? "moTa=" + moTa + ", " : "") +
                (maBHYT != null ? "maBHYT=" + maBHYT + ", " : "") +
                (maBHXH != null ? "maBHXH=" + maBHXH + ", " : "") +
            "}";
    }

}
