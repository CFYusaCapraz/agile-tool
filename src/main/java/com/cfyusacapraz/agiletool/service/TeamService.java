package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.TeamCreateRequest;
import com.cfyusacapraz.agiletool.api.request.TeamFilterRequest;
import com.cfyusacapraz.agiletool.api.request.TeamUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.base.PageData;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface TeamService {

    @PreAuthorize("hasRole(ADMIN) or hasAuthority(TEAM_CREATE)")
    CompletableFuture<TeamDto> create(@NotNull TeamCreateRequest teamCreateRequest);

    @PreAuthorize("hasRole(ADMIN) or hasAuthority(TEAM_UPDATE)")
    CompletableFuture<TeamDto> update(@NotNull UUID id, @NotNull TeamUpdateRequest teamCreateRequest);

    @PreAuthorize("hasRole(ADMIN) or hasAuthority(TEAM_DELETE)")
    CompletableFuture<Void> delete(@NotNull UUID id);

    @PreAuthorize("hasRole(ADMIN) or hasAuthority(TEAM_READ)")
    CompletableFuture<TeamDto> getById(@NotNull UUID id);

    @PreAuthorize("hasRole(ADMIN) or hasAuthority(TEAM_READ)")
    CompletableFuture<Pair<List<TeamDto>, PageData>> getAll(@NotNull TeamFilterRequest teamFilterRequest);
}
