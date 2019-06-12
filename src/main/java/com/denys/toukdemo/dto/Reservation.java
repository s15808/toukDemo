package com.denys.toukdemo.dto;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class Reservation {

    @Valid
    private User owner;
    private Seans seans;
    private List<ReservationUnit> units = new ArrayList<>();

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Seans getSeans() {
        return seans;
    }

    public void setSeans(Seans seans) {
        this.seans = seans;
    }

    public List<ReservationUnit> getUnits() {
        return units;
    }

    public void setUnits(List<ReservationUnit> units) {
        this.units = units;
    }

}
