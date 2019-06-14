package com.denys.toukdemo.service;


import com.denys.toukdemo.repository.ReservationRepository;
import com.denys.toukdemo.repository.SeansRepository;
import com.denys.toukdemo.dto.ReservationStatus;
import com.denys.toukdemo.entity.ReservationEntity;
import com.denys.toukdemo.entity.SeansEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Component
@EnableScheduling
public class ScheduledTaskService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    SeansRepository seansRepository;

    @Scheduled(cron="0/60 * * * * *")
    public void checkForNotActualReservationsBeforeMovie(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus15 = LocalDateTime.now().plusMinutes(15);
        List<SeansEntity> seansEntities = seansRepository.findAllByStartDttmIsBetweenOrderByMovieByMovieIdAscStartDttmAsc(Timestamp.valueOf(now), Timestamp.valueOf(nowPlus15));
        for (SeansEntity seansEntity : seansEntities) {
            Collection<ReservationEntity> reservationEntities = seansEntity.getReservationsById();
            for (ReservationEntity reservationEntity : reservationEntities) {
                if(reservationEntity.getStatus() == ReservationStatus.RESERVE )
                    reservationEntity.setStatus(ReservationStatus.CAMCEL);
            }
            reservationRepository.save(reservationEntities);
        }
    }

    @Scheduled(cron="0/30 * * * * *")
    public void checkForNotActualReservations(){
        LocalDateTime nowMinus15 = LocalDateTime.now().minusMinutes(15);
        List<ReservationEntity> reservationEntities = reservationRepository.findAllByReservationDttmLessThanEqualAndStatusNot(Timestamp.valueOf(nowMinus15), ReservationStatus.CAMCEL);
        for (ReservationEntity reservationEntity : reservationEntities) {
            if(reservationEntity.getStatus() == ReservationStatus.RESERVE )
                reservationEntity.setStatus(ReservationStatus.CAMCEL);
        }
        reservationRepository.save(reservationEntities);
    }
}
