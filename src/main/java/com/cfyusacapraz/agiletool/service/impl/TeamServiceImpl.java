package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.api.request.TeamCreateRequest;
import com.cfyusacapraz.agiletool.api.request.TeamFilterRequest;
import com.cfyusacapraz.agiletool.api.request.TeamUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.base.PageData;
import com.cfyusacapraz.agiletool.domain.Team;
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
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final UserService userService;

    @Override
    @Transactional
    @Async
    public CompletableFuture<TeamDto> create(@NotNull TeamCreateRequest teamCreateRequest) {
        log.info("Creating team with name: {}", teamCreateRequest.getName());

        teamRepository.findByName(teamCreateRequest.getName())
                .thenAccept(optionalTeam -> optionalTeam.ifPresent(team -> {
                    throw new IllegalArgumentException("Team with name " + teamCreateRequest.getName() + " already exists");
                })).join();

        return userService.getById(teamCreateRequest.getScrumMaster())
                .thenApply(userDto -> Team.builder().build())
                .thenApply(team -> {
                    Team savedTeam = teamRepository.save(team);
                    log.info("Team created successfully with id: {}", savedTeam.getId());
                    return savedTeam;
                }).thenApply(Team::toDto);
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<TeamDto> update(@NotNull UUID id, @NotNull TeamUpdateRequest teamUpdateRequest) {
        log.info("Updating team with id: {}", id);

        return CompletableFuture.completedFuture(teamRepository.findById(id))
                .thenApply(optionalTeam -> optionalTeam.orElseThrow(() ->
                        new IllegalArgumentException("Team with id " + id + " not found")))
                .thenApply(team -> {
                    team.setName(teamUpdateRequest.getName());
                    team.setStatus(teamUpdateRequest.getStatus());

                    Team updatedTeam = teamRepository.save(team);
                    log.info("Team updated successfully with id: {}", updatedTeam.getId());
                    return updatedTeam;
                })
                .thenApply(Team::toDto);
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<Void> delete(@NotNull UUID id) {
        return CompletableFuture.completedFuture(teamRepository.findById(id))
                .thenApply(optionalTeam -> optionalTeam.orElseThrow(() ->
                        new IllegalArgumentException("Team with id " + id + " not found")))
                .thenAccept(team -> {
                    team.setStatus(TeamStatus.ARCHIVED);
                    teamRepository.save(team);
                    log.info("Team deleted successfully with id: {}", id);
                });
    }

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<TeamDto> getById(@NotNull UUID id) {
        return CompletableFuture.completedFuture(teamRepository.findById(id))
                .thenApply(optionalTeam -> optionalTeam.orElseThrow(() ->
                        new IllegalArgumentException("Team with id " + id + " not found")))
                .thenApply(Team::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<Pair<List<TeamDto>, PageData>> getAll(@NotNull TeamFilterRequest teamFilterRequest) {
        TeamSpecification teamSpecification = new TeamSpecification(teamFilterRequest);
        return PaginationService.getPagedAndFilteredData(teamRepository, teamFilterRequest, TeamSpecification.class)
                .thenApply(teamPage -> {
                    List<TeamDto> userDtoList = teamPage.stream()
                            .map(Team::toDto)
                            .toList();
                    PageData pageData = new PageData(teamFilterRequest.getPageNumber(), teamPage.getTotalElements(), teamPage.getTotalPages());
                    return Pair.of(userDtoList, pageData);
                });
    }
}
