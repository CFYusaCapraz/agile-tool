package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.Team;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, config = BaseEntityMapperConfig.class,
        unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {UserMapper.class})
public interface TeamMapper {

    TeamDto toDto(Team team, @Context CycleAvoidingMappingContext context);

    Team toEntity(TeamDto teamDto, @Context CycleAvoidingMappingContext context);
}
