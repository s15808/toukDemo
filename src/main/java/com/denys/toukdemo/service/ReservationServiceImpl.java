package com.denys.toukdemo.service;

import com.denys.toukdemo.entity.*;
import com.denys.toukdemo.repository.ReservationRepository;
import com.denys.toukdemo.repository.SeansRepository;
import com.denys.toukdemo.repository.UserRepository;
import com.denys.toukdemo.dto.Reservation;
import com.denys.toukdemo.dto.ReservationStatus;
import com.denys.toukdemo.dto.ReservationUnit;
import com.denys.toukdemo.dto.Room;
import com.denys.toukdemo.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Component
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    SeansRepository seansRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Room getRoomWithReservedPlacesBySeansId(String seansId) {
        SeansEntity seans = seansRepository.findSeansEntityByIdEquals(Integer.parseInt(seansId));
        Collection<ReservationEntity> reservationEntities = seans.getReservationsById();
        RoomEntity roomEntity = seans.getRoomByRoomId();
        Room room = new Room(roomEntity.getRoomType());
        for (ReservationEntity reservationEntity : reservationEntities) {
            if(reservationEntity.getStatus() != ReservationStatus.CAMCEL) {
                Integer row = reservationEntity.getId().getRowNumer();
                Integer place = reservationEntity.getId().getPlace();
                room.setPlaceReserve(row, place);
            }
        }
        return room;
    }

    @Override
    public void saveReservation(Reservation reservation, String confirmNo) throws RuntimeException {
        SeansEntity seansEntity = getReservationOrThrowException(reservation);
        List<ReservationEntity> reservationEntities = createReservationEntityList(reservation, confirmNo);
        createOrUpdateUsersEntity(reservation, reservationEntities);
        updateSeansEntity(seansEntity, reservationEntities);
        reservationRepository.save(reservationEntities);
    }

    @Override
    public boolean confirmReservation(String confirmNo) {
        boolean result = true;
        List<ReservationEntity> reservedEntities = reservationRepository.findAllById_Id(confirmNo);
        for (ReservationEntity reservedEntity : reservedEntities) {
            LocalDateTime reservedTime = reservedEntity.getReservationDttm().toLocalDateTime();
            if(tooLatePlus15Minutes(reservedTime)) {
                reservedEntity.setStatus(ReservationStatus.CAMCEL);
                result = false;
            }
            else
                reservedEntity.setStatus(ReservationStatus.CONFIRM);
        }
        reservationRepository.save(reservedEntities);

        return result;
    }

    private List<ReservationEntity> createReservationEntityList(Reservation reservation, String confirmNo){
        List<ReservationEntity> resultList = new ArrayList<>();
        for (ReservationUnit unit : reservation.getUnits()) {
            ReservationEntity reservationEntity = ReservationMapper.INSTANCE.map(unit);
            reservationEntity.getId().setId(confirmNo);
            resultList.add(reservationEntity);
        }
        return resultList;
    }

    private void createOrUpdateUsersEntity(Reservation reservation, List<ReservationEntity> reservationEntities){
        String name = reservation.getOwner().getName();
        String surname = reservation.getOwner().getSurname();
        UsersEntity usersEntity = userRepository.findUsersEntityByNameAndSurname(name, surname);
        if(usersEntity == null) {
            usersEntity = ReservationMapper.INSTANCE.createUser(reservation.getOwner());
            userRepository.save(usersEntity);
        }
        for (ReservationEntity reservationEntity : reservationEntities) {
            reservationEntity.setUsersByUserId(usersEntity);
        }
    }

    private SeansEntity getReservationOrThrowException(Reservation reservation) throws RuntimeException {
        Integer seansId = reservation.getSeans().getId();
        SeansEntity seansEntity = seansRepository.findSeansEntityByIdEquals(seansId);
        LocalDateTime strtSeansDttm = seansEntity.getStartDttm().toLocalDateTime();
        if(tooLateMinus15Minutes(strtSeansDttm))
            throw new RuntimeException("Too late for reservation");
        else
            return seansEntity;
    }

    private void updateSeansEntity(SeansEntity seansEntity, List<ReservationEntity> reservationEntities){
        for (ReservationEntity reservationEntity : reservationEntities) {
            reservationEntity.setSeansBySeansId(seansEntity);
        }
    }

    private boolean tooLatePlus15Minutes(LocalDateTime time){
        LocalDateTime maxTime = time.plusMinutes(15);
        return checkTime(maxTime);
    }

    private boolean tooLateMinus15Minutes(LocalDateTime time){
        LocalDateTime maxTime = time.minusMinutes(15);
        return checkTime(maxTime);
    }

    private boolean checkTime(LocalDateTime maxTime){
        LocalDateTime now = LocalDateTime.now();
        return now.compareTo(maxTime) >= 0;
    }
}
