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
 * Criteria class for the {@link com.api.backend.domain.Danhmucnghenghiep} entity. This class is used
 * in {@link com.api.backend.web.rest.DanhmucnghenghiepResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danhmucnghenghieps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DanhmucnghenghiepCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ma;

    private StringFilter ten;

    private StringFilter mota;

    private StringFilter bYT;

    private IntegerFilter sudung;

    public DanhmucnghenghiepCriteria(){
    }

    public DanhmucnghenghiepCriteria(DanhmucnghenghiepCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ma = other.ma == null ? null : other.ma.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.mota = other.mota == null ? null : other.mota.copy();
        this.bYT = other.bYT == null ? null : other.bYT.copy();
        this.sudung = other.sudung == null ? null : other.sudung.copy();
    }

    @Override
    public DanhmucnghenghiepCriteria copy() {
        return new DanhmucnghenghiepCriteria(this);
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

    public StringFilter getbYT() {
        return bYT;
    }

    public void setbYT(StringFilter bYT) {
        this.bYT = bYT;
    }

    public IntegerFilter getSudung() {
        return sudung;
    }

    public void setSudung(IntegerFilter sudung) {
        this.sudung = sudung;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DanhmucnghenghiepCriteria that = (DanhmucnghenghiepCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ma, that.ma) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(mota, that.mota) &&
            Objects.equals(bYT, that.bYT) &&
            Objects.equals(sudung, that.sudung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ma,
        ten,
        mota,
        bYT,
        sudung
        );
    }

    @Override
    public String toString() {
        return "DanhmucnghenghiepCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ma != null ? "ma=" + ma + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (mota != null ? "mota=" + mota + ", " : "") +
                (bYT != null ? "bYT=" + bYT + ", " : "") +
                (sudung != null ? "sudung=" + sudung + ", " : "") +
            "}";
    }

}
