package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.Retro;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseEntityMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {UserMapper.class, TeamMapper.class, RetroColumnMapper.class})
public interface RetroMapper {

    RetroMapper INSTANCE = Mappers.getMapper(RetroMapper.class);

    RetroDto toDto(Retro retro);

    @Mapping(target = "fromDto", ignore = true)
    Retro toEntity(RetroDto retroDto);
}
