package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.RetroColumn;
import com.cfyusacapraz.agiletool.dto.RetroColumnDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseEntityMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {RetroItemMapper.class, RetroMapper.class})
public interface RetroColumnMapper {

    RetroColumnMapper INSTANCE = Mappers.getMapper(RetroColumnMapper.class);

    RetroColumnDto toDto(RetroColumn retroColumn, @Context CycleAvoidingMappingContext context);

    RetroColumn toEntity(RetroColumnDto retroColumnDto, @Context CycleAvoidingMappingContext context);
}
