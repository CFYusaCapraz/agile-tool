package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.AuthenticationRequest;
import com.cfyusacapraz.agiletool.api.request.RefreshTokenRequest;
import com.cfyusacapraz.agiletool.api.response.AuthenticationResponse;
import com.cfyusacapraz.agiletool.dto.UserDto;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request);

    UserDto getCurrentUser();
}
