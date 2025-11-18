package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.RetroCreateRequest;
import com.cfyusacapraz.agiletool.api.request.base.BasePagedApiRequest;
import com.cfyusacapraz.agiletool.api.response.base.PageData;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

public interface RetroService {

    @PreAuthorize("hasRole('SCRUM_MASTER') or hasAuthority('RETRO_CREATE')")
    RetroDto create(@NotNull RetroCreateRequest retroCreateRequest);

    @PreAuthorize("hasAuthority('RETRO_READ')")
    Pair<List<RetroDto>, PageData> getAll(@NotNull BasePagedApiRequest basePagedApiRequest);

    @PreAuthorize("hasAuthority('RETRO_READ')")
    RetroDto getById(@NotNull UUID id);
}
