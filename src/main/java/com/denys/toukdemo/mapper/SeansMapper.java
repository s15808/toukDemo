package com.denys.toukdemo.mapper;

import com.denys.toukdemo.dto.Movie;
import com.denys.toukdemo.dto.Room;
import com.denys.toukdemo.dto.Seans;
import com.denys.toukdemo.entity.MovieEntity;
import com.denys.toukdemo.entity.RoomEntity;
import com.denys.toukdemo.entity.SeansEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SeansMapper {

    SeansMapper INSTANCE = Mappers.getMapper(SeansMapper.class);

    @Mappings({
            @Mapping(target = "movie", source = "movieByMovieId"),
            @Mapping(target = "room", source = "roomByRoomId")
    })
    Seans map(SeansEntity from);

    @Mappings({
            @Mapping(target = "type", source = "roomType"),
            @Mapping(target = "seanses", ignore = true)
    })
    Room map(RoomEntity from);

    @Mappings({
            @Mapping(target = "seanses", ignore = true)
    })
    Movie map(MovieEntity from);

    List<Seans> mapSeanses(List<SeansEntity> from);
}
