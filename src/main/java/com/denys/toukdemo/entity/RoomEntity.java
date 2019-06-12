package com.denys.toukdemo.entity;

import com.denys.toukdemo.dto.RoomType;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "room", schema = "public", catalog = "postgres")
public class RoomEntity {
    private Integer id;
    private RoomType roomType;
    private String name;
    private Collection<SeansEntity> seansById;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "room_type", nullable = false, length = 6)
    @Enumerated(EnumType.STRING)
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (roomType != null ? !roomType.equals(that.roomType) : that.roomType != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roomByRoomId")
    public Collection<SeansEntity> getSeansById() {
        return seansById;
    }

    public void setSeansById(Collection<SeansEntity> seansById) {
        this.seansById = seansById;
    }
}
