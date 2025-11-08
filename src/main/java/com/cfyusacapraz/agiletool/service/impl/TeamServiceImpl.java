package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.api.request.TeamCreateRequest;
import com.cfyusacapraz.agiletool.domain.Team;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import com.cfyusacapraz.agiletool.repository.TeamRepository;
import com.cfyusacapraz.agiletool.service.TeamService;
import com.cfyusacapraz.agiletool.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
