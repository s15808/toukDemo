package com.denys.toukdemo.dto;

import java.util.Date;
import java.util.List;

public class Movie {

    private String title;
    private String description;
    private Date premierStart;
    private Date premierEnd;
    private List<Seans> seanses;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPremierStart() {
        return premierStart;
    }

    public void setPremierStart(Date premierStart) {
        this.premierStart = premierStart;
    }

    public Date getPremierEnd() {
        return premierEnd;
    }

    public void setPremierEnd(Date premierEnd) {
        this.premierEnd = premierEnd;
    }

    public List<Seans> getSeanses() {
        return seanses;
    }

    public void setSeanses(List<Seans> seanses) {
        this.seanses = seanses;
    }
}
