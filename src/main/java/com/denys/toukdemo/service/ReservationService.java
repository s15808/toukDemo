package com.denys.toukdemo.service;

import com.denys.toukdemo.dto.Reservation;
import com.denys.toukdemo.dto.Room;

public interface ReservationService {

    Room getRoomWithReservedPlacesBySeansId(String seansId);
    void saveReservation(Reservation reservation, String confirmNo) throws RuntimeException;
    boolean confirmReservation(String confirmNo);
}
