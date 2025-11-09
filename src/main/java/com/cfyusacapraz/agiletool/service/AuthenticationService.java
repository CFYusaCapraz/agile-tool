package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.AuthenticationRequest;
import com.cfyusacapraz.agiletool.api.request.RefreshTokenRequest;
import com.cfyusacapraz.agiletool.api.response.AuthenticationResponse;

import java.util.concurrent.CompletableFuture;

public interface AuthenticationService {

    CompletableFuture<AuthenticationResponse> authenticate(AuthenticationRequest request);

    CompletableFuture<AuthenticationResponse> refreshToken(RefreshTokenRequest request);
}
