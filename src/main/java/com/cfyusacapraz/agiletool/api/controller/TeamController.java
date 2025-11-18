package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.TeamCreateRequest;
import com.cfyusacapraz.agiletool.api.request.TeamFilterRequest;
import com.cfyusacapraz.agiletool.api.request.TeamUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.TeamResponse;
import com.cfyusacapraz.agiletool.api.response.base.*;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import com.cfyusacapraz.agiletool.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = ApiEndpoints.TEAM_BASE_URL, produces = ApiEndpoints.RESPONSE_CONTENT_TYPE)
@RequiredArgsConstructor
@Validated
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public SaveEntityResponse<UUID> createTeam(@Validated @RequestBody TeamCreateRequest teamCreateRequest) {
        TeamDto teamDto = teamService.create(teamCreateRequest);
        return new SaveEntityResponse<>(teamDto);
    }

    @PutMapping(path = "/{teamId}")
    public SaveEntityResponse<UUID> updateTeam(@PathVariable("teamId") UUID id,
                                               @Validated @RequestBody TeamUpdateRequest teamCreateRequest) {
        TeamDto teamDto = teamService.update(id, teamCreateRequest);
        return new SaveEntityResponse<>(teamDto);
    }

    @DeleteMapping(path = "/{teamId}")
    public BaseApiResponse deleteTeam(@PathVariable("teamId") UUID id) {
        teamService.delete(id);
        return new BaseApiResponse(null, true);
    }

    @GetMapping(path = "/{teamId}")
    public SingleResultResponse<TeamResponse> getTeam(@PathVariable("teamId") UUID id) {
        TeamDto teamDto = teamService.getById(id);
        return new SingleResultResponse<>(new TeamResponse(teamDto));
    }

    @GetMapping
    public PagedListResultResponse<List<TeamResponse>> getAllTeams(@Validated TeamFilterRequest teamFilterRequest) {
        Pair<List<TeamDto>, PageData> dataPair = teamService.getAll(teamFilterRequest);
        List<TeamResponse> teamResponses = dataPair.getFirst().stream().map(TeamResponse::new).toList();
        return new PagedListResultResponse<>(teamResponses, dataPair.getSecond());
    }
}
