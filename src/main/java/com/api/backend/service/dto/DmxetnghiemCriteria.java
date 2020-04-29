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
 * Criteria class for the {@link com.api.backend.domain.Dmxetnghiem} entity. This class is used
 * in {@link com.api.backend.web.rest.DmxetnghiemResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dmxetnghiems?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DmxetnghiemCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ma;

    private StringFilter ten;

    private IntegerFilter cha;

    private StringFilter mota;

    private IntegerFilter gioitinh;

    private StringFilter canduoi;

    private StringFilter cantren;

    private StringFilter donvido;

    private StringFilter maByt;

    private StringFilter manhomBH;

    private LongFilter dmnhomxetnghiemId;

    public DmxetnghiemCriteria(){
    }

    public DmxetnghiemCriteria(DmxetnghiemCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ma = other.ma == null ? null : other.ma.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.cha = other.cha == null ? null : other.cha.copy();
        this.mota = other.mota == null ? null : other.mota.copy();
        this.gioitinh = other.gioitinh == null ? null : other.gioitinh.copy();
        this.canduoi = other.canduoi == null ? null : other.canduoi.copy();
        this.cantren = other.cantren == null ? null : other.cantren.copy();
        this.donvido = other.donvido == null ? null : other.donvido.copy();
        this.maByt = other.maByt == null ? null : other.maByt.copy();
        this.manhomBH = other.manhomBH == null ? null : other.manhomBH.copy();
        this.dmnhomxetnghiemId = other.dmnhomxetnghiemId == null ? null : other.dmnhomxetnghiemId.copy();
    }

    @Override
    public DmxetnghiemCriteria copy() {
        return new DmxetnghiemCriteria(this);
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

    public IntegerFilter getCha() {
        return cha;
    }

    public void setCha(IntegerFilter cha) {
        this.cha = cha;
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

    public StringFilter getCanduoi() {
        return canduoi;
    }

    public void setCanduoi(StringFilter canduoi) {
        this.canduoi = canduoi;
    }

    public StringFilter getCantren() {
        return cantren;
    }

    public void setCantren(StringFilter cantren) {
        this.cantren = cantren;
    }

    public StringFilter getDonvido() {
        return donvido;
    }

    public void setDonvido(StringFilter donvido) {
        this.donvido = donvido;
    }

    public StringFilter getMaByt() {
        return maByt;
    }

    public void setMaByt(StringFilter maByt) {
        this.maByt = maByt;
    }

    public StringFilter getManhomBH() {
        return manhomBH;
    }

    public void setManhomBH(StringFilter manhomBH) {
        this.manhomBH = manhomBH;
    }

    public LongFilter getDmnhomxetnghiemId() {
        return dmnhomxetnghiemId;
    }

    public void setDmnhomxetnghiemId(LongFilter dmnhomxetnghiemId) {
        this.dmnhomxetnghiemId = dmnhomxetnghiemId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DmxetnghiemCriteria that = (DmxetnghiemCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ma, that.ma) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(cha, that.cha) &&
            Objects.equals(mota, that.mota) &&
            Objects.equals(gioitinh, that.gioitinh) &&
            Objects.equals(canduoi, that.canduoi) &&
            Objects.equals(cantren, that.cantren) &&
            Objects.equals(donvido, that.donvido) &&
            Objects.equals(maByt, that.maByt) &&
            Objects.equals(manhomBH, that.manhomBH) &&
            Objects.equals(dmnhomxetnghiemId, that.dmnhomxetnghiemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ma,
        ten,
        cha,
        mota,
        gioitinh,
        canduoi,
        cantren,
        donvido,
        maByt,
        manhomBH,
        dmnhomxetnghiemId
        );
    }

    @Override
    public String toString() {
        return "DmxetnghiemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ma != null ? "ma=" + ma + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (cha != null ? "cha=" + cha + ", " : "") +
                (mota != null ? "mota=" + mota + ", " : "") +
                (gioitinh != null ? "gioitinh=" + gioitinh + ", " : "") +
                (canduoi != null ? "canduoi=" + canduoi + ", " : "") +
                (cantren != null ? "cantren=" + cantren + ", " : "") +
                (donvido != null ? "donvido=" + donvido + ", " : "") +
                (maByt != null ? "maByt=" + maByt + ", " : "") +
                (manhomBH != null ? "manhomBH=" + manhomBH + ", " : "") +
                (dmnhomxetnghiemId != null ? "dmnhomxetnghiemId=" + dmnhomxetnghiemId + ", " : "") +
            "}";
    }

}
