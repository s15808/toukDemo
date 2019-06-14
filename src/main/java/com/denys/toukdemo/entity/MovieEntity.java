package com.denys.toukdemo.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "movie", schema = "public", catalog = "postgres")
public class MovieEntity {
    private Integer id;
    private String title;
    private String description;
    private Date premierStart;
    private Date premierEnd;
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
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "premier_start", nullable = false)
    public Date getPremierStart() {
        return premierStart;
    }

    public void setPremierStart(Date premierStart) {
        this.premierStart = premierStart;
    }

    @Basic
    @Column(name = "premier_end", nullable = false)
    public Date getPremierEnd() {
        return premierEnd;
    }

    public void setPremierEnd(Date premierEnd) {
        this.premierEnd = premierEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieEntity that = (MovieEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (premierStart != null ? !premierStart.equals(that.premierStart) : that.premierStart != null) return false;
        if (premierEnd != null ? !premierEnd.equals(that.premierEnd) : that.premierEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (premierStart != null ? premierStart.hashCode() : 0);
        result = 31 * result + (premierEnd != null ? premierEnd.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "movieByMovieId")
    public Collection<SeansEntity> getSeansById() {
        return seansById;
    }

    public void setSeansById(Collection<SeansEntity> seansById) {
        this.seansById = seansById;
    }
}
