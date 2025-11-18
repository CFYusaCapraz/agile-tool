package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.RetroColumn;
import com.cfyusacapraz.agiletool.dto.RetroColumnDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, config = BaseEntityMapperConfig.class,
        unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {RetroItemMapper.class, RetroMapper.class})
public interface RetroColumnMapper {

    RetroColumnDto toDto(RetroColumn retroColumn, @Context CycleAvoidingMappingContext context);

    RetroColumn toEntity(RetroColumnDto retroColumnDto, @Context CycleAvoidingMappingContext context);
}
