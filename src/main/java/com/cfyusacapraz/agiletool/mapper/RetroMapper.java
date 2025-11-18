package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.Retro;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, config = BaseEntityMapperConfig.class,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {UserMapper.class, TeamMapper.class, RetroColumnMapper.class})
public interface RetroMapper {

    RetroDto toDto(Retro retro, @Context CycleAvoidingMappingContext context);

    Retro toEntity(RetroDto retroDto, @Context CycleAvoidingMappingContext context);
}
