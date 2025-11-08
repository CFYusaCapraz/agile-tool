package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.Team;
import com.cfyusacapraz.agiletool.dto.TeamDto;

import java.util.UUID;

public interface TeamRepository extends BaseEntityRepository<Team, TeamDto, UUID> {
}