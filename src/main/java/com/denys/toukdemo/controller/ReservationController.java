package com.denys.toukdemo.controller;

import com.denys.toukdemo.dto.Reservation;
import com.denys.toukdemo.dto.ReservationUnit;
import com.denys.toukdemo.dto.Room;
import com.denys.toukdemo.dto.User;
import com.denys.toukdemo.helper.RoomHelper;
import com.denys.toukdemo.service.MailService;
import com.denys.toukdemo.service.ReservationService;
import com.denys.toukdemo.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class ReservationController {

    private static final Integer VOUCHER_DISCOUNT_GRADE = 2;

    @Autowired
    ReservationService reservationService;

    @Autowired
    MailService mailService;

    @RequestMapping(value = "/particularscreening", method = RequestMethod.GET)
    public Room getParticularScreeningBySeans(@RequestParam String seansId){
        return reservationService.getRoomWithReservedPlacesBySeansId(seansId);
    }

    @RequestMapping(value = "/reserveseats", method = RequestMethod.POST)
    public String reserveSeats(@RequestBody Reservation reservation){
        if(!UserValidator.isValid(reservation.getOwner()))
            return "Invalid user data";

        Room room = getParticularScreeningBySeans(reservation.getSeans().getId().toString());
        RoomHelper roomHelper = new RoomHelper(room);
        if(roomHelper.hasSinglePlaceBetweenTwoAlreadyReserved(reservation.getUnits()))
            return "Sorry, you can`t left over place between two already reserved";

        UUID confirmNo = UUID.randomUUID();
        try {
            reservationService.saveReservation(reservation, confirmNo.toString());
        } catch (RuntimeException e){
            return "Sorry, it`s too late for booking for this movie";
        }

        String confirmUrl = mailService.sendMailAndGetConfirmUrl(reservation.getOwner().getEmail(), confirmNo.toString());

        Double totalAmount = calculateTotalAmount(reservation);

        String expirationDate = LocalDateTime.now().plusMinutes(15).toString();

        return "Thanks, total amount is "+ totalAmount +" pln, reservation expires: " + expirationDate
                + ". Please check your email for confirmation link or go to link " + confirmUrl;
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public String confirmation(@RequestParam("no") String no){
        boolean confirm = reservationService.confirmReservation(no);
        if(confirm)
            return "Thanks for confirmation!";
        else
            return "Sorry, unfortunately your confirmation was canceled due time expiration";
    }

    private Double calculateTotalAmount(Reservation reservation){
        Double totalAmount = 0d;
        for (ReservationUnit unit : reservation.getUnits()) {
            Double price = unit.getTicket().getPrice();
            if(unit.getVoucher() != null)
                price = price/VOUCHER_DISCOUNT_GRADE;
            totalAmount += price;
        }
        return totalAmount;
    }
}
