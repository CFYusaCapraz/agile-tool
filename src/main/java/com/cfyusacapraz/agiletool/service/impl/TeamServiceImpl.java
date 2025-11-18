package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.api.request.TeamCreateRequest;
import com.cfyusacapraz.agiletool.api.request.TeamFilterRequest;
import com.cfyusacapraz.agiletool.api.request.TeamUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.base.PageData;
import com.cfyusacapraz.agiletool.domain.Team;
import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.domain.enums.TeamStatus;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import com.cfyusacapraz.agiletool.repository.TeamRepository;
import com.cfyusacapraz.agiletool.repository.spec.TeamSpecification;
import com.cfyusacapraz.agiletool.service.TeamService;
import com.cfyusacapraz.agiletool.service.UserService;
import com.cfyusacapraz.agiletool.util.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final UserService userService;

    @Override
    @Transactional
    public TeamDto create(@NotNull TeamCreateRequest teamCreateRequest) {
        log.info("Creating team with name: {}", teamCreateRequest.getName());

        User scrumMaster = new User().fromDto(userService.getById(teamCreateRequest.getScrumMaster()));

        teamRepository.findByName(teamCreateRequest.getName()).orElseThrow(() -> new IllegalArgumentException(
                "Team with name " + teamCreateRequest.getName() + " already exists"));

        Team team = Team.builder().name(teamCreateRequest.getName()).scrumMaster(scrumMaster).status(TeamStatus.ACTIVE)
                .build();
        Team savedTeam = teamRepository.save(team);
        log.info("Team created successfully with id: {}", savedTeam.getId());
        return savedTeam.toDto();
    }

    @Override
    @Transactional
    public TeamDto update(@NotNull UUID id, @NotNull TeamUpdateRequest teamUpdateRequest) {
        log.info("Updating team with id: {}", id);
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team with id " + id + " not found"));
        team.setName(teamUpdateRequest.getName());
        team.setStatus(teamUpdateRequest.getStatus());
        Team updatedTeam = teamRepository.save(team);
        log.info("Team updated successfully with id: {}", updatedTeam.getId());
        return updatedTeam.toDto();
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team with id " + id + " not found"));
        team.setStatus(TeamStatus.ARCHIVED);
        teamRepository.save(team);
        log.info("Team deleted successfully with id: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDto getById(@NotNull UUID id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team with id " + id + " " + "not found")).toDto();
    }

    @Override
    @Transactional(readOnly = true)
    public Pair<List<TeamDto>, PageData> getAll(@NotNull TeamFilterRequest teamFilterRequest) {
        TeamSpecification teamSpecification = new TeamSpecification(teamFilterRequest);
        Page<Team> teamPage =
                PaginationService.getPagedAndFilteredData(teamRepository, teamFilterRequest, TeamSpecification.class);
        List<TeamDto> teamDtoList = teamPage.stream().map(Team::toDto).toList();
        PageData pageData =
                new PageData(teamFilterRequest.getPageNumber(), teamPage.getTotalElements(), teamPage.getTotalPages());
        return Pair.of(teamDtoList, pageData);
    }
}
