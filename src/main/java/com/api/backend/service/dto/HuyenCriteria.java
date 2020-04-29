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
 * Criteria class for the {@link com.api.backend.domain.Huyen} entity. This class is used
 * in {@link com.api.backend.web.rest.HuyenResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /huyens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class HuyenCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ten;

    private LongFilter xaId;

    private LongFilter cosokhambenhId;

    private LongFilter tinhId;

    public HuyenCriteria(){
    }

    public HuyenCriteria(HuyenCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.xaId = other.xaId == null ? null : other.xaId.copy();
        this.cosokhambenhId = other.cosokhambenhId == null ? null : other.cosokhambenhId.copy();
        this.tinhId = other.tinhId == null ? null : other.tinhId.copy();
    }

    @Override
    public HuyenCriteria copy() {
        return new HuyenCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTen() {
        return ten;
    }

    public void setTen(StringFilter ten) {
        this.ten = ten;
    }

    public LongFilter getXaId() {
        return xaId;
    }

    public void setXaId(LongFilter xaId) {
        this.xaId = xaId;
    }

    public LongFilter getCosokhambenhId() {
        return cosokhambenhId;
    }

    public void setCosokhambenhId(LongFilter cosokhambenhId) {
        this.cosokhambenhId = cosokhambenhId;
    }

    public LongFilter getTinhId() {
        return tinhId;
    }

    public void setTinhId(LongFilter tinhId) {
        this.tinhId = tinhId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final HuyenCriteria that = (HuyenCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(xaId, that.xaId) &&
            Objects.equals(cosokhambenhId, that.cosokhambenhId) &&
            Objects.equals(tinhId, that.tinhId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ten,
        xaId,
        cosokhambenhId,
        tinhId
        );
    }

    @Override
    public String toString() {
        return "HuyenCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (xaId != null ? "xaId=" + xaId + ", " : "") +
                (cosokhambenhId != null ? "cosokhambenhId=" + cosokhambenhId + ", " : "") +
                (tinhId != null ? "tinhId=" + tinhId + ", " : "") +
            "}";
    }

}
