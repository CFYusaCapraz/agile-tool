package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.TeamCreateRequest;
import com.cfyusacapraz.agiletool.api.request.TeamUpdateRequest;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface TeamService {

    @PreAuthorize("hasRole(ADMIN)")
    CompletableFuture<TeamDto> create(@NotNull TeamCreateRequest teamCreateRequest);

    @PreAuthorize("hasRole(ADMIN) or hasAuthority(TEAM_UPDATE)")
    CompletableFuture<TeamDto> update(@NotNull UUID teamId, @NotNull TeamUpdateRequest teamCreateRequest);
}
