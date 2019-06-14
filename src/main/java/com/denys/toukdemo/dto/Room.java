package com.denys.toukdemo.dto;

import java.util.*;

public class Room {

    private RoomType type;
    private String name;
    private List<Seans> seanses;
    private boolean[][] reserved;

    public Room() {
    }

    public Room(RoomType type) {
        this.type = type;
        this.reserved = new boolean[type.rowNumber][type.placeNumber];
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seans> getSeanses() {
        return seanses;
    }

    public void setSeanses(List<Seans> seanses) {
        this.seanses = seanses;
    }

    public void setPlaceReserve(Integer row, Integer place){
        this.reserved[row][place] = true;
    }

    public boolean[][] getReserved() {
        return reserved;
    }


}
