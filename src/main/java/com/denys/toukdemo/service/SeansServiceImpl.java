package com.denys.toukdemo.service;

import com.denys.toukdemo.repository.SeansRepository;
import com.denys.toukdemo.dto.Seans;
import com.denys.toukdemo.entity.SeansEntity;
import com.denys.toukdemo.mapper.SeansMapper;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Component
public class SeansServiceImpl implements SeansService {

    @Autowired
    SeansRepository seansRepository;

    public List<Seans> getSeansesByStartDatetime(Date date) {
        java.sql.Timestamp startDttm = new java.sql.Timestamp(date.getTime());
        java.sql.Timestamp endDttm = new java.sql.Timestamp(calcEndOfDay(date).getTime());
        List<SeansEntity> seansEntities = seansRepository.findAllByStartDttmIsBetweenOrderByMovieByMovieIdAscStartDttmAsc(startDttm, endDttm);

        return SeansMapper.INSTANCE.mapSeanses(seansEntities);
    }

    private Date calcEndOfDay(Date date) {
        LocalDateTime endDt = new LocalDateTime(date.getTime())
                                    .withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return endDt.toDate();
    }
}
