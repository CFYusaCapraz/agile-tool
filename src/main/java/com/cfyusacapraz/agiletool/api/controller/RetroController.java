package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.RetroCreateRequest;
import com.cfyusacapraz.agiletool.api.response.base.SaveEntityResponse;
import com.cfyusacapraz.agiletool.service.RetroService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
