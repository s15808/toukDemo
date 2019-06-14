package com.denys.toukdemo.dto;

public enum RoomType {

    LARGE(10, 10),
    MEDIUM(7, 7),
    SMALL(5, 5);

    Integer rowNumber;
    Integer placeNumber;

    RoomType(Integer rowNumber, Integer placeNumber) {
        this.rowNumber = rowNumber;
        this.placeNumber = placeNumber;
    }
}
