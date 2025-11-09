package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.AuthenticationRequest;
import com.cfyusacapraz.agiletool.api.request.RefreshTokenRequest;
import com.cfyusacapraz.agiletool.api.response.AuthenticationResponse;
import com.cfyusacapraz.agiletool.api.response.base.SingleResultResponse;
import com.cfyusacapraz.agiletool.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = ApiEndpoints.AUTH_BASE_URL, produces = ApiEndpoints.RESPONSE_CONTENT_TYPE)
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public CompletableFuture<SingleResultResponse<AuthenticationResponse>> login(@Validated @RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request)
                .thenApply(SingleResultResponse::new);
    }

    @PostMapping("/refresh")
    public CompletableFuture<SingleResultResponse<AuthenticationResponse>> refreshToken(@Validated @RequestBody RefreshTokenRequest request) {
        return authenticationService.refreshToken(request)
                .thenApply(SingleResultResponse::new);
    }
}
