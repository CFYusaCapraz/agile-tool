package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.Retro;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseEntityMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {UserMapper.class, TeamMapper.class, RetroColumnMapper.class})
public interface RetroMapper {

    RetroMapper INSTANCE = Mappers.getMapper(RetroMapper.class);

    RetroDto toDto(Retro retro, @Context CycleAvoidingMappingContext context);

    Retro toEntity(RetroDto retroDto, @Context CycleAvoidingMappingContext context);
}
