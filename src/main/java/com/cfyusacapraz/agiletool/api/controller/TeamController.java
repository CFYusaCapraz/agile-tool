package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.TeamCreateRequest;
import com.cfyusacapraz.agiletool.api.response.base.SaveEntityResponse;
import com.cfyusacapraz.agiletool.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = ApiEndpoints.TEAM_BASE_URL, produces = ApiEndpoints.RESPONSE_CONTENT_TYPE)
@RequiredArgsConstructor
@Validated
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public CompletableFuture<SaveEntityResponse<UUID>> createTeam(@Validated @RequestBody TeamCreateRequest teamCreateRequest) {
        return teamService.create(teamCreateRequest)
                .thenApply(SaveEntityResponse::new);
    }
}
