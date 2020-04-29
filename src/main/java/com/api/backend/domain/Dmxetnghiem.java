package com.api.backend.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Dmxetnghiem.
 */
@Entity
@Table(name = "dmxetnghiem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dmxetnghiem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "cha")
    private Integer cha;

    @Column(name = "mota")
    private String mota;

    @Column(name = "gioitinh")
    private Integer gioitinh;

    @Column(name = "canduoi")
    private String canduoi;

    @Column(name = "cantren")
    private String cantren;

    @Column(name = "donvido")
    private String donvido;

    @Column(name = "ma_byt")
    private String maByt;

    @Column(name = "manhom_bh")
    private String manhomBH;

    @ManyToOne
    @JsonIgnoreProperties("dmxetnghiems")
    private Dmnhomxetnghiem dmnhomxetnghiem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public Dmxetnghiem ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public Dmxetnghiem ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getCha() {
        return cha;
    }

    public Dmxetnghiem cha(Integer cha) {
        this.cha = cha;
        return this;
    }

    public void setCha(Integer cha) {
        this.cha = cha;
    }

    public String getMota() {
        return mota;
    }

    public Dmxetnghiem mota(String mota) {
        this.mota = mota;
        return this;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Integer getGioitinh() {
        return gioitinh;
    }

    public Dmxetnghiem gioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
        return this;
    }

    public void setGioitinh(Integer gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getCanduoi() {
        return canduoi;
    }

    public Dmxetnghiem canduoi(String canduoi) {
        this.canduoi = canduoi;
        return this;
    }

    public void setCanduoi(String canduoi) {
        this.canduoi = canduoi;
    }

    public String getCantren() {
        return cantren;
    }

    public Dmxetnghiem cantren(String cantren) {
        this.cantren = cantren;
        return this;
    }

    public void setCantren(String cantren) {
        this.cantren = cantren;
    }

    public String getDonvido() {
        return donvido;
    }

    public Dmxetnghiem donvido(String donvido) {
        this.donvido = donvido;
        return this;
    }

    public void setDonvido(String donvido) {
        this.donvido = donvido;
    }

    public String getMaByt() {
        return maByt;
    }

    public Dmxetnghiem maByt(String maByt) {
        this.maByt = maByt;
        return this;
    }

    public void setMaByt(String maByt) {
        this.maByt = maByt;
    }

    public String getManhomBH() {
        return manhomBH;
    }

    public Dmxetnghiem manhomBH(String manhomBH) {
        this.manhomBH = manhomBH;
        return this;
    }

    public void setManhomBH(String manhomBH) {
        this.manhomBH = manhomBH;
    }

    public Dmnhomxetnghiem getDmnhomxetnghiem() {
        return dmnhomxetnghiem;
    }

    public Dmxetnghiem dmnhomxetnghiem(Dmnhomxetnghiem dmnhomxetnghiem) {
        this.dmnhomxetnghiem = dmnhomxetnghiem;
        return this;
    }

    public void setDmnhomxetnghiem(Dmnhomxetnghiem dmnhomxetnghiem) {
        this.dmnhomxetnghiem = dmnhomxetnghiem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dmxetnghiem)) {
            return false;
        }
        return id != null && id.equals(((Dmxetnghiem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dmxetnghiem{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", cha=" + getCha() +
            ", mota='" + getMota() + "'" +
            ", gioitinh=" + getGioitinh() +
            ", canduoi='" + getCanduoi() + "'" +
            ", cantren='" + getCantren() + "'" +
            ", donvido='" + getDonvido() + "'" +
            ", maByt='" + getMaByt() + "'" +
            ", manhomBH='" + getManhomBH() + "'" +
            "}";
    }
}
