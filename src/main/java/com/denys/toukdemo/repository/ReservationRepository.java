package com.denys.toukdemo.repository;

import com.denys.toukdemo.dto.ReservationStatus;
import com.denys.toukdemo.entity.ReservationEntity;
import com.denys.toukdemo.entity.ReservationEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, ReservationEntityId> {

    List<ReservationEntity> findAllByReservationDttmLessThanEqualAndStatusNot(Timestamp reservationDttm, ReservationStatus status);
    List<ReservationEntity> findAllById_Id(String id);
}
