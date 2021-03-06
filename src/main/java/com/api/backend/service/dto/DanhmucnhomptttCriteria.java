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
 * Criteria class for the {@link com.api.backend.domain.Danhmucnhompttt} entity. This class is used
 * in {@link com.api.backend.web.rest.DanhmucnhomptttResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danhmucnhompttts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DanhmucnhomptttCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter loai;

    private StringFilter ma;

    private StringFilter ten;

    private LongFilter danhmucptttId;

    public DanhmucnhomptttCriteria(){
    }

    public DanhmucnhomptttCriteria(DanhmucnhomptttCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.loai = other.loai == null ? null : other.loai.copy();
        this.ma = other.ma == null ? null : other.ma.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.danhmucptttId = other.danhmucptttId == null ? null : other.danhmucptttId.copy();
    }

    @Override
    public DanhmucnhomptttCriteria copy() {
        return new DanhmucnhomptttCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getLoai() {
        return loai;
    }

    public void setLoai(IntegerFilter loai) {
        this.loai = loai;
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

    public LongFilter getDanhmucptttId() {
        return danhmucptttId;
    }

    public void setDanhmucptttId(LongFilter danhmucptttId) {
        this.danhmucptttId = danhmucptttId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DanhmucnhomptttCriteria that = (DanhmucnhomptttCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(loai, that.loai) &&
            Objects.equals(ma, that.ma) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(danhmucptttId, that.danhmucptttId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        loai,
        ma,
        ten,
        danhmucptttId
        );
    }

    @Override
    public String toString() {
        return "DanhmucnhomptttCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (loai != null ? "loai=" + loai + ", " : "") +
                (ma != null ? "ma=" + ma + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (danhmucptttId != null ? "danhmucptttId=" + danhmucptttId + ", " : "") +
            "}";
    }

}
