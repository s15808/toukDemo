package com.denys.toukdemo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReservationEntityId  implements Serializable {

    private String id;
    private Integer rowNumer;
    private Integer place;

    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "row_numer", nullable = false)
    public Integer getRowNumer() {
        return rowNumer;
    }

    public void setRowNumer(Integer rowNumer) {
        this.rowNumer = rowNumer;
    }

    @Basic
    @Column(name = "place", nullable = false)
    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntityId that = (ReservationEntityId) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (rowNumer != null ? !rowNumer.equals(that.rowNumer) : that.rowNumer != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (rowNumer != null ? rowNumer.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        return result;
    }
}
