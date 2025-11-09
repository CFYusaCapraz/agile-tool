package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.api.request.AuthenticationRequest;
import com.cfyusacapraz.agiletool.api.request.RefreshTokenRequest;
import com.cfyusacapraz.agiletool.api.response.AuthenticationResponse;
import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.dto.UserDto;
import com.cfyusacapraz.agiletool.repository.UserRepository;
import com.cfyusacapraz.agiletool.service.AuthenticationService;
import com.cfyusacapraz.agiletool.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<AuthenticationResponse> authenticate(@NotNull AuthenticationRequest request) {
        String email = request.getEmail();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        request.getPassword()
                )
        );
        return userRepository.findByEmail(email)
                .thenApply(optionalUser -> optionalUser.orElseThrow(() ->
                        new IllegalArgumentException("User with email " + email + " not found")))
                .thenApply(user -> {
                    String accessToken = jwtService.generateToken(user);
                    String refreshToken = jwtService.generateRefreshToken(user);
                    return AuthenticationResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                });
    }

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<AuthenticationResponse> refreshToken(RefreshTokenRequest request) {
        final String userEmail = jwtService.extractUsername(request.getToken());
        return userRepository.findByEmail(userEmail)
                .thenApply(optionalUser -> optionalUser.orElseThrow(() ->
                        new IllegalArgumentException("User with email " + userEmail + " not found")))
                .thenApply(user -> {
                    if (jwtService.isTokenValid(request.getToken(), user)) {
                        String accessToken = jwtService.generateToken(user);
                        String refreshToken = jwtService.generateRefreshToken(user);
                        return AuthenticationResponse.builder()
                                .accessToken(accessToken)
                                .refreshToken(refreshToken)
                                .build();
                    }

                    throw new IllegalArgumentException("Invalid refresh token");
                });
    }

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<UserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            CompletableFuture<UserDto> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(new IllegalArgumentException("No authenticated user"));
            return failedFuture;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof User user) {
            return CompletableFuture.completedFuture(user.toDto());
        }

        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .thenApply(optionalUser -> optionalUser.orElseThrow(() ->
                        new IllegalArgumentException("User with email " + email + " not found")))
                .thenApply(User::toDto);

    }
}
