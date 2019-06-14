package com.denys.toukdemo.mapper;

import com.denys.toukdemo.dto.ReservationUnit;
import com.denys.toukdemo.dto.Seans;
import com.denys.toukdemo.dto.User;
import com.denys.toukdemo.entity.ReservationEntity;
import com.denys.toukdemo.entity.ReservationEntityId;
import com.denys.toukdemo.entity.SeansEntity;
import com.denys.toukdemo.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "unit"),
            @Mapping(target = "usersByUserId", ignore = true),
            @Mapping(target = "seansBySeansId", ignore = true),
            @Mapping(target = "status", constant = "RESERVE"),
    })
    ReservationEntity map(ReservationUnit unit);
    @Mappings({
            @Mapping(target = "rowNumer", source = "row"),
    })
    ReservationEntityId mapId(ReservationUnit unit);
    @Mappings({
            @Mapping(target = "reservationsById", ignore = true),
    })
    UsersEntity createUser(User from);
    @Mappings({
            @Mapping(target = "movieByMovieId", ignore = true),
            @Mapping(target = "roomByRoomId", ignore = true),
            @Mapping(target = "startDttm", ignore = true),
    })
    SeansEntity createSeans(Seans from);

}
