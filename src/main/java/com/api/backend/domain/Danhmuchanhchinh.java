package com.api.backend.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Danhmuchanhchinh.
 */
@Entity
@Table(name = "danhmuchanhchinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Danhmuchanhchinh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "tendaydu")
    private String tendaydu;

    @Column(name = "tenviettat")
    private String tenviettat;

    @Column(name = "tags")
    private String tags;

    @Column(name = "diadanhcha")
    private Integer diadanhcha;

    @Column(name = "cap")
    private Integer cap;

    @Column(name = "thanhthi")
    private Integer thanhthi;

    @Column(name = "hoatdong")
    private Integer hoatdong;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public Danhmuchanhchinh ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTendaydu() {
        return tendaydu;
    }

    public Danhmuchanhchinh tendaydu(String tendaydu) {
        this.tendaydu = tendaydu;
        return this;
    }

    public void setTendaydu(String tendaydu) {
        this.tendaydu = tendaydu;
    }

    public String getTenviettat() {
        return tenviettat;
    }

    public Danhmuchanhchinh tenviettat(String tenviettat) {
        this.tenviettat = tenviettat;
        return this;
    }

    public void setTenviettat(String tenviettat) {
        this.tenviettat = tenviettat;
    }

    public String getTags() {
        return tags;
    }

    public Danhmuchanhchinh tags(String tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getDiadanhcha() {
        return diadanhcha;
    }

    public Danhmuchanhchinh diadanhcha(Integer diadanhcha) {
        this.diadanhcha = diadanhcha;
        return this;
    }

    public void setDiadanhcha(Integer diadanhcha) {
        this.diadanhcha = diadanhcha;
    }

    public Integer getCap() {
        return cap;
    }

    public Danhmuchanhchinh cap(Integer cap) {
        this.cap = cap;
        return this;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public Integer getThanhthi() {
        return thanhthi;
    }

    public Danhmuchanhchinh thanhthi(Integer thanhthi) {
        this.thanhthi = thanhthi;
        return this;
    }

    public void setThanhthi(Integer thanhthi) {
        this.thanhthi = thanhthi;
    }

    public Integer getHoatdong() {
        return hoatdong;
    }

    public Danhmuchanhchinh hoatdong(Integer hoatdong) {
        this.hoatdong = hoatdong;
        return this;
    }

    public void setHoatdong(Integer hoatdong) {
        this.hoatdong = hoatdong;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Danhmuchanhchinh)) {
            return false;
        }
        return id != null && id.equals(((Danhmuchanhchinh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Danhmuchanhchinh{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", tendaydu='" + getTendaydu() + "'" +
            ", tenviettat='" + getTenviettat() + "'" +
            ", tags='" + getTags() + "'" +
            ", diadanhcha=" + getDiadanhcha() +
            ", cap=" + getCap() +
            ", thanhthi=" + getThanhthi() +
            ", hoatdong=" + getHoatdong() +
            "}";
    }
}
