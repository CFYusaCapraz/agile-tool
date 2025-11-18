package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.RetroCreateRequest;
import com.cfyusacapraz.agiletool.api.request.base.BasePagedApiRequest;
import com.cfyusacapraz.agiletool.api.response.RetroDetailResponse;
import com.cfyusacapraz.agiletool.api.response.RetroListResponse;
import com.cfyusacapraz.agiletool.api.response.base.PageData;
import com.cfyusacapraz.agiletool.api.response.base.PagedListResultResponse;
import com.cfyusacapraz.agiletool.api.response.base.SaveEntityResponse;
import com.cfyusacapraz.agiletool.api.response.base.SingleResultResponse;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import com.cfyusacapraz.agiletool.service.RetroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = ApiEndpoints.RETRO_BASE_URL, produces = ApiEndpoints.RESPONSE_CONTENT_TYPE)
@RequiredArgsConstructor
@Validated
public class RetroController {

    private final RetroService retroService;

    @PostMapping
    public SaveEntityResponse<UUID> createRetrospectiveSession(
            @Validated @RequestBody RetroCreateRequest retroCreateRequest) {
        RetroDto retroDto = retroService.create(retroCreateRequest);
        return new SaveEntityResponse<>(retroDto);
    }

    @GetMapping
    public PagedListResultResponse<List<RetroListResponse>> getAllRetrospectives(
            @Validated BasePagedApiRequest basePagedApiRequest) {
        Pair<List<RetroDto>, PageData> dataPair = retroService.getAll(basePagedApiRequest);
        List<RetroListResponse> retroListResponse = dataPair.getFirst().stream().map(RetroListResponse::new).toList();
        return new PagedListResultResponse<>(retroListResponse, dataPair.getSecond());
    }

    @GetMapping("/{retroId}")
    public SingleResultResponse<RetroDetailResponse> getRetrospectiveById(@PathVariable("retroId") UUID id) {
        RetroDto retroDto = retroService.getById(id);
        return new SingleResultResponse<>(new RetroDetailResponse(retroDto));
    }
}
