package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.TeamCreateRequest;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.concurrent.CompletableFuture;

public interface TeamService {

    @PreAuthorize("hasRole(ADMIN)")
    CompletableFuture<TeamDto> create(@NotNull TeamCreateRequest teamCreateRequest);
}
