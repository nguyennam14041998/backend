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
 * Criteria class for the {@link com.api.backend.domain.Cosokhambenh} entity. This class is used
 * in {@link com.api.backend.web.rest.CosokhambenhResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cosokhambenhs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CosokhambenhCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ma;

    private StringFilter ten;

    private StringFilter maKCBBD;

    private StringFilter hang;

    private StringFilter tuyen;

    private StringFilter loai;

    private StringFilter diachi;

    private StringFilter ghichu;

    private LongFilter tinhId;

    private LongFilter huyenId;

    private LongFilter xaId;

    public CosokhambenhCriteria(){
    }

    public CosokhambenhCriteria(CosokhambenhCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ma = other.ma == null ? null : other.ma.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.maKCBBD = other.maKCBBD == null ? null : other.maKCBBD.copy();
        this.hang = other.hang == null ? null : other.hang.copy();
        this.tuyen = other.tuyen == null ? null : other.tuyen.copy();
        this.loai = other.loai == null ? null : other.loai.copy();
        this.diachi = other.diachi == null ? null : other.diachi.copy();
        this.ghichu = other.ghichu == null ? null : other.ghichu.copy();
        this.tinhId = other.tinhId == null ? null : other.tinhId.copy();
        this.huyenId = other.huyenId == null ? null : other.huyenId.copy();
        this.xaId = other.xaId == null ? null : other.xaId.copy();
    }

    @Override
    public CosokhambenhCriteria copy() {
        return new CosokhambenhCriteria(this);
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

    public StringFilter getMaKCBBD() {
        return maKCBBD;
    }

    public void setMaKCBBD(StringFilter maKCBBD) {
        this.maKCBBD = maKCBBD;
    }

    public StringFilter getHang() {
        return hang;
    }

    public void setHang(StringFilter hang) {
        this.hang = hang;
    }

    public StringFilter getTuyen() {
        return tuyen;
    }

    public void setTuyen(StringFilter tuyen) {
        this.tuyen = tuyen;
    }

    public StringFilter getLoai() {
        return loai;
    }

    public void setLoai(StringFilter loai) {
        this.loai = loai;
    }

    public StringFilter getDiachi() {
        return diachi;
    }

    public void setDiachi(StringFilter diachi) {
        this.diachi = diachi;
    }

    public StringFilter getGhichu() {
        return ghichu;
    }

    public void setGhichu(StringFilter ghichu) {
        this.ghichu = ghichu;
    }

    public LongFilter getTinhId() {
        return tinhId;
    }

    public void setTinhId(LongFilter tinhId) {
        this.tinhId = tinhId;
    }

    public LongFilter getHuyenId() {
        return huyenId;
    }

    public void setHuyenId(LongFilter huyenId) {
        this.huyenId = huyenId;
    }

    public LongFilter getXaId() {
        return xaId;
    }

    public void setXaId(LongFilter xaId) {
        this.xaId = xaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CosokhambenhCriteria that = (CosokhambenhCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ma, that.ma) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(maKCBBD, that.maKCBBD) &&
            Objects.equals(hang, that.hang) &&
            Objects.equals(tuyen, that.tuyen) &&
            Objects.equals(loai, that.loai) &&
            Objects.equals(diachi, that.diachi) &&
            Objects.equals(ghichu, that.ghichu) &&
            Objects.equals(tinhId, that.tinhId) &&
            Objects.equals(huyenId, that.huyenId) &&
            Objects.equals(xaId, that.xaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ma,
        ten,
        maKCBBD,
        hang,
        tuyen,
        loai,
        diachi,
        ghichu,
        tinhId,
        huyenId,
        xaId
        );
    }

    @Override
    public String toString() {
        return "CosokhambenhCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ma != null ? "ma=" + ma + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (maKCBBD != null ? "maKCBBD=" + maKCBBD + ", " : "") +
                (hang != null ? "hang=" + hang + ", " : "") +
                (tuyen != null ? "tuyen=" + tuyen + ", " : "") +
                (loai != null ? "loai=" + loai + ", " : "") +
                (diachi != null ? "diachi=" + diachi + ", " : "") +
                (ghichu != null ? "ghichu=" + ghichu + ", " : "") +
                (tinhId != null ? "tinhId=" + tinhId + ", " : "") +
                (huyenId != null ? "huyenId=" + huyenId + ", " : "") +
                (xaId != null ? "xaId=" + xaId + ", " : "") +
            "}";
    }

}
