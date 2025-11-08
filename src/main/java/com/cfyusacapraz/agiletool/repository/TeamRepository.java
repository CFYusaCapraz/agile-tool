package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.Team;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends BaseEntityRepository<Team, TeamDto, UUID> {
}