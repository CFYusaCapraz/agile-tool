package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.Team;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends BaseEntityRepository<Team, TeamDto, UUID> {

    @Query("select t from Team t where t.name = :name")
    Optional<Team> findByName(@Param("name") String name);
}