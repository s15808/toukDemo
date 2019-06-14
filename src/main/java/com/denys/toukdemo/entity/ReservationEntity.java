package com.denys.toukdemo.entity;

import com.denys.toukdemo.dto.ReservationStatus;
import com.denys.toukdemo.dto.TicketType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "reservation", schema = "public", catalog = "postgres")
public class ReservationEntity implements Serializable {
    private ReservationEntityId id;
    private Integer userId;
    private Integer seansId;
    private TicketType ticket;
    private ReservationStatus status;
    private Timestamp reservationDttm;
    private UsersEntity usersByUserId;
    private SeansEntity seansBySeansId;
    private String voucher;

    @EmbeddedId
    public ReservationEntityId getId() {
        return id;
    }

    public void setId(ReservationEntityId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "seans_id", nullable = false, insertable = false, updatable = false)
    public Integer getSeansId() {
        return seansId;
    }

    public void setSeansId(Integer seansId) {
        this.seansId = seansId;
    }

    @Basic
    @Column(name = "ticket", nullable = false, length = 7)
    @Enumerated(EnumType.STRING)
    public TicketType getTicket() {
        return ticket;
    }

    public void setTicket(TicketType ticket) {
        this.ticket = ticket;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 8)
    @Enumerated(EnumType.STRING)
    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Basic
    @Column(name = "reservation_dttm", nullable = false, insertable = false, updatable = false)
    public Timestamp getReservationDttm() {
        return reservationDttm;
    }

    public void setReservationDttm(Timestamp reservationDttm) {
        this.reservationDttm = reservationDttm;
    }

    @Basic
    @Column(name = "voucher", length = 10)
    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntity that = (ReservationEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (seansId != null ? !seansId.equals(that.seansId) : that.seansId != null) return false;
        if (ticket != null ? !ticket.equals(that.ticket) : that.ticket != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (seansId != null ? seansId.hashCode() : 0);
        result = 31 * result + (ticket != null ? ticket.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "seans_id", referencedColumnName = "id", nullable = false)
    public SeansEntity getSeansBySeansId() {
        return seansBySeansId;
    }

    public void setSeansBySeansId(SeansEntity seansBySeansId) {
        this.seansBySeansId = seansBySeansId;
    }
}
