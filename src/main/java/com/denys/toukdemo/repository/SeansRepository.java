package com.denys.toukdemo.repository;

import com.denys.toukdemo.entity.SeansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface SeansRepository extends JpaRepository<SeansEntity, Long> {

    List<SeansEntity> findAllByStartDttmIsBetweenOrderByMovieByMovieIdAscStartDttmAsc(Timestamp startDttm, Timestamp endDttm);
    SeansEntity findSeansEntityByIdEquals(Integer seansId);
}
