package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.api.request.UserCreationRequest;
import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.dto.UserDto;
import com.cfyusacapraz.agiletool.repository.UserRepository;
import com.cfyusacapraz.agiletool.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @Async
    public CompletableFuture<UserDto> createUser(@NotNull UserCreationRequest userCreationRequest) {
        log.info("Creating user with email: {}", userCreationRequest.getEmail());

        // Check if user with the same email already exists
        userRepository.findByEmail(userCreationRequest.getEmail())
                .thenAccept(optionalUser -> optionalUser.ifPresent(user -> {
                    throw new IllegalArgumentException("User with email " + userCreationRequest.getEmail() + " already exists");
                }))
                .join();

        // Create new user
        User user = User.builder()
                .email(userCreationRequest.getEmail())
                .password(passwordEncoder.encode(userCreationRequest.getPassword()))
                .name(userCreationRequest.getName())
                .role(userCreationRequest.getRole())
                .build();

        user = userRepository.save(user);
        log.info("User created successfully with id: {}", user.getId());

        return CompletableFuture.completedFuture(user.toDto());
    }
}
