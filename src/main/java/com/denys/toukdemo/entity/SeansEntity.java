package com.denys.toukdemo.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "seans", schema = "public", catalog = "postgres")
public class SeansEntity {
    private Integer movieId;
    private Integer roomId;
    private Integer id;
    private Timestamp startDttm;
    private Collection<ReservationEntity> reservationsById;
    private MovieEntity movieByMovieId;
    private RoomEntity roomByRoomId;

    @Basic
    @GeneratedValue
    @Column(name = "movie_id", nullable = false, insertable = false, updatable = false)
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "room_id", nullable = false, insertable = false, updatable = false)
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_dttm", nullable = false)
    public Timestamp getStartDttm() {
        return startDttm;
    }

    public void setStartDttm(Timestamp startDttm) {
        this.startDttm = startDttm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeansEntity that = (SeansEntity) o;

        if (movieId != null ? !movieId.equals(that.movieId) : that.movieId != null) return false;
        if (roomId != null ? !roomId.equals(that.roomId) : that.roomId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (startDttm != null ? !startDttm.equals(that.startDttm) : that.startDttm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieId != null ? movieId.hashCode() : 0;
        result = 31 * result + (roomId != null ? roomId.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (startDttm != null ? startDttm.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "seansBySeansId", fetch = FetchType.EAGER)
    public Collection<ReservationEntity> getReservationsById() {
        return reservationsById;
    }

    public void setReservationsById(Collection<ReservationEntity> reservationsById) {
        this.reservationsById = reservationsById;
    }

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    @OrderBy("title")
    public MovieEntity getMovieByMovieId() {
        return movieByMovieId;
    }

    public void setMovieByMovieId(MovieEntity movieByMovieId) {
        this.movieByMovieId = movieByMovieId;
    }

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    public RoomEntity getRoomByRoomId() {
        return roomByRoomId;
    }

    public void setRoomByRoomId(RoomEntity roomByRoomId) {
        this.roomByRoomId = roomByRoomId;
    }
}
