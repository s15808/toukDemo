package com.denys.toukdemo.dto;

public enum TicketType {

    ADULT(25.0),
    STUDENT(18.0),
    CHILD(12.5);

    Double price;

    TicketType(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
}
