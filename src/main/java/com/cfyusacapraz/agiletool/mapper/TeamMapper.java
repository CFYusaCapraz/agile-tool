package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.Team;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseEntityMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {UserMapper.class})
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamDto toDto(Team team);

    Team toEntity(TeamDto teamDto);
}
