package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.TeamCreateRequest;
import com.cfyusacapraz.agiletool.api.request.TeamFilterRequest;
import com.cfyusacapraz.agiletool.api.request.TeamUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.TeamResponse;
import com.cfyusacapraz.agiletool.api.response.base.BaseApiResponse;
import com.cfyusacapraz.agiletool.api.response.base.PagedListResultResponse;
import com.cfyusacapraz.agiletool.api.response.base.SaveEntityResponse;
import com.cfyusacapraz.agiletool.api.response.base.SingleResultResponse;
import com.cfyusacapraz.agiletool.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = ApiEndpoints.TEAM_BASE_URL, produces = ApiEndpoints.RESPONSE_CONTENT_TYPE)
@RequiredArgsConstructor
@Validated
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public CompletableFuture<SaveEntityResponse<UUID>> createTeam(
            @Validated @RequestBody TeamCreateRequest teamCreateRequest) {
        return teamService.create(teamCreateRequest)
                .thenApply(SaveEntityResponse::new);
    }

    @PutMapping(path = "/{teamId}")
    public CompletableFuture<SaveEntityResponse<UUID>> updateTeam(@PathVariable("teamId") UUID id,
                                                                  @Validated @RequestBody
                                                                  TeamUpdateRequest teamCreateRequest) {
        return teamService.update(id, teamCreateRequest)
                .thenApply(SaveEntityResponse::new);
    }

    @DeleteMapping(path = "/{teamId}")
    public CompletableFuture<BaseApiResponse> deleteTeam(@PathVariable("teamId") UUID id) {
        return teamService.delete(id)
                .thenApply(voidResult -> new BaseApiResponse(null, true));
    }

    @GetMapping(path = "/{teamId}")
    public CompletableFuture<SingleResultResponse<TeamResponse>> getTeam(@PathVariable("teamId") UUID id) {
        return teamService.getById(id)
                .thenApply(teamDto -> new SingleResultResponse<>(new TeamResponse(teamDto)));
    }

    @GetMapping
    public CompletableFuture<PagedListResultResponse<List<TeamResponse>>> getAllTeams(
            @Validated TeamFilterRequest teamFilterRequest) {
        return teamService.getAll(teamFilterRequest)
                .thenApply(pair -> {
                    List<TeamResponse> teamResponses = pair.getFirst().stream().map(TeamResponse::new).toList();
                    return new PagedListResultResponse<>(teamResponses, pair.getSecond());
                });
    }
}
