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
 * Criteria class for the {@link com.api.backend.domain.Xa} entity. This class is used
 * in {@link com.api.backend.web.rest.XaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /xas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class XaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ten;

    private LongFilter cosokhambenhId;

    private LongFilter huyenId;

    public XaCriteria(){
    }

    public XaCriteria(XaCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.cosokhambenhId = other.cosokhambenhId == null ? null : other.cosokhambenhId.copy();
        this.huyenId = other.huyenId == null ? null : other.huyenId.copy();
    }

    @Override
    public XaCriteria copy() {
        return new XaCriteria(this);
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

    public LongFilter getCosokhambenhId() {
        return cosokhambenhId;
    }

    public void setCosokhambenhId(LongFilter cosokhambenhId) {
        this.cosokhambenhId = cosokhambenhId;
    }

    public LongFilter getHuyenId() {
        return huyenId;
    }

    public void setHuyenId(LongFilter huyenId) {
        this.huyenId = huyenId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final XaCriteria that = (XaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(cosokhambenhId, that.cosokhambenhId) &&
            Objects.equals(huyenId, that.huyenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ten,
        cosokhambenhId,
        huyenId
        );
    }

    @Override
    public String toString() {
        return "XaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (cosokhambenhId != null ? "cosokhambenhId=" + cosokhambenhId + ", " : "") +
                (huyenId != null ? "huyenId=" + huyenId + ", " : "") +
            "}";
    }

}
