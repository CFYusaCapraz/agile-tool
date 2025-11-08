package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.RetroItem;
import com.cfyusacapraz.agiletool.dto.RetroItemDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseEntityMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {UserMapper.class, RetroColumnMapper.class})
public interface RetroItemMapper {

    RetroItemMapper INSTANCE = Mappers.getMapper(RetroItemMapper.class);

    RetroItemDto toDto(RetroItem retroItem, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "fromDto", ignore = true)
    RetroItem toEntity(RetroItemDto retroItemDto, @Context CycleAvoidingMappingContext context);
}
