package com.denys.toukdemo.dto;

public class ReservationUnit {

    private Integer row;
    private Integer place;
    private TicketType ticket;
    private String voucher;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public TicketType getTicket() {
        return ticket;
    }

    public void setTicket(TicketType ticket) {
        this.ticket = ticket;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }
}
