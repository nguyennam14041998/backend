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
 * Criteria class for the {@link com.api.backend.domain.Danhmuchanhchinh} entity. This class is used
 * in {@link com.api.backend.web.rest.DanhmuchanhchinhResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danhmuchanhchinhs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DanhmuchanhchinhCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ten;

    private StringFilter tendaydu;

    private StringFilter tenviettat;

    private StringFilter tags;

    private IntegerFilter diadanhcha;

    private IntegerFilter cap;

    private IntegerFilter thanhthi;

    private IntegerFilter hoatdong;

    public DanhmuchanhchinhCriteria(){
    }

    public DanhmuchanhchinhCriteria(DanhmuchanhchinhCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.tendaydu = other.tendaydu == null ? null : other.tendaydu.copy();
        this.tenviettat = other.tenviettat == null ? null : other.tenviettat.copy();
        this.tags = other.tags == null ? null : other.tags.copy();
        this.diadanhcha = other.diadanhcha == null ? null : other.diadanhcha.copy();
        this.cap = other.cap == null ? null : other.cap.copy();
        this.thanhthi = other.thanhthi == null ? null : other.thanhthi.copy();
        this.hoatdong = other.hoatdong == null ? null : other.hoatdong.copy();
    }

    @Override
    public DanhmuchanhchinhCriteria copy() {
        return new DanhmuchanhchinhCriteria(this);
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

    public StringFilter getTendaydu() {
        return tendaydu;
    }

    public void setTendaydu(StringFilter tendaydu) {
        this.tendaydu = tendaydu;
    }

    public StringFilter getTenviettat() {
        return tenviettat;
    }

    public void setTenviettat(StringFilter tenviettat) {
        this.tenviettat = tenviettat;
    }

    public StringFilter getTags() {
        return tags;
    }

    public void setTags(StringFilter tags) {
        this.tags = tags;
    }

    public IntegerFilter getDiadanhcha() {
        return diadanhcha;
    }

    public void setDiadanhcha(IntegerFilter diadanhcha) {
        this.diadanhcha = diadanhcha;
    }

    public IntegerFilter getCap() {
        return cap;
    }

    public void setCap(IntegerFilter cap) {
        this.cap = cap;
    }

    public IntegerFilter getThanhthi() {
        return thanhthi;
    }

    public void setThanhthi(IntegerFilter thanhthi) {
        this.thanhthi = thanhthi;
    }

    public IntegerFilter getHoatdong() {
        return hoatdong;
    }

    public void setHoatdong(IntegerFilter hoatdong) {
        this.hoatdong = hoatdong;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DanhmuchanhchinhCriteria that = (DanhmuchanhchinhCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(tendaydu, that.tendaydu) &&
            Objects.equals(tenviettat, that.tenviettat) &&
            Objects.equals(tags, that.tags) &&
            Objects.equals(diadanhcha, that.diadanhcha) &&
            Objects.equals(cap, that.cap) &&
            Objects.equals(thanhthi, that.thanhthi) &&
            Objects.equals(hoatdong, that.hoatdong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ten,
        tendaydu,
        tenviettat,
        tags,
        diadanhcha,
        cap,
        thanhthi,
        hoatdong
        );
    }

    @Override
    public String toString() {
        return "DanhmuchanhchinhCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (tendaydu != null ? "tendaydu=" + tendaydu + ", " : "") +
                (tenviettat != null ? "tenviettat=" + tenviettat + ", " : "") +
                (tags != null ? "tags=" + tags + ", " : "") +
                (diadanhcha != null ? "diadanhcha=" + diadanhcha + ", " : "") +
                (cap != null ? "cap=" + cap + ", " : "") +
                (thanhthi != null ? "thanhthi=" + thanhthi + ", " : "") +
                (hoatdong != null ? "hoatdong=" + hoatdong + ", " : "") +
            "}";
    }

}
