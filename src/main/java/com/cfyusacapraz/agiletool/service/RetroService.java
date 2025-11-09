package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.RetroCreateRequest;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.concurrent.CompletableFuture;

public interface RetroService {

    @PreAuthorize("hasRole('SCRUM_MASTER') or hasAuthority('RETRO_CREATE')")
    CompletableFuture<RetroDto> create(@NotNull RetroCreateRequest retroCreateRequest);
}
