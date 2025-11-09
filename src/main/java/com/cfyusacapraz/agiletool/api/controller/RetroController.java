package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.RetroCreateRequest;
import com.cfyusacapraz.agiletool.api.request.base.BasePagedApiRequest;
import com.cfyusacapraz.agiletool.api.response.RetroListResponse;
import com.cfyusacapraz.agiletool.api.response.base.PagedListResultResponse;
import com.cfyusacapraz.agiletool.api.response.base.SaveEntityResponse;
import com.cfyusacapraz.agiletool.service.RetroService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = ApiEndpoints.RETRO_BASE_URL, produces = ApiEndpoints.RESPONSE_CONTENT_TYPE)
@RequiredArgsConstructor
@Validated
public class RetroController {

    private final RetroService retroService;

    @PostMapping
    public CompletableFuture<SaveEntityResponse<UUID>> createRetrospectiveSession(@Validated @RequestBody RetroCreateRequest retroCreateRequest) {
        return retroService.create(retroCreateRequest)
                .thenApply(SaveEntityResponse::new);
    }

    @GetMapping
    public CompletableFuture<PagedListResultResponse<List<RetroListResponse>>> getAllRetrospectives(@Validated BasePagedApiRequest basePagedApiRequest) {
        return retroService.getAll(basePagedApiRequest)
                .thenApply(pair -> {
                    List<RetroListResponse> retroListResponse = pair.getFirst().stream().map(RetroListResponse::new).toList();
                    return new PagedListResultResponse<>(retroListResponse, pair.getSecond());
                });
    }
}
