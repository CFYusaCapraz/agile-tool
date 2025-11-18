package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.RetroItem;
import com.cfyusacapraz.agiletool.dto.RetroItemDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, config = BaseEntityMapperConfig.class,
        unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {UserMapper.class, RetroColumnMapper.class})
public interface RetroItemMapper {

    RetroItemDto toDto(RetroItem retroItem, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "isVisible", source = "visible")
    RetroItem toEntity(RetroItemDto retroItemDto, @Context CycleAvoidingMappingContext context);
}
