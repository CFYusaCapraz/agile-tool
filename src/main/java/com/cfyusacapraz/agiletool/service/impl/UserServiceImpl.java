package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.api.request.UserCreateRequest;
import com.cfyusacapraz.agiletool.api.request.UserFilterRequest;
import com.cfyusacapraz.agiletool.api.request.UserUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.base.PageData;
import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.dto.UserDto;
import com.cfyusacapraz.agiletool.repository.UserRepository;
import com.cfyusacapraz.agiletool.repository.spec.UserSpecification;
import com.cfyusacapraz.agiletool.service.UserService;
import com.cfyusacapraz.agiletool.util.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @Async
    public CompletableFuture<UserDto> create(@NotNull UserCreateRequest userCreateRequest) {
        log.info("Creating user with email: {}", userCreateRequest.getEmail());

        // Check if user with the same email already exists
        userRepository.findByEmail(userCreateRequest.getEmail())
                .thenAccept(optionalUser -> optionalUser.ifPresent(user -> {
                    throw new IllegalArgumentException("User with email " + userCreateRequest.getEmail() + " already exists");
                }))
                .join();

        // Create new user
        User user = User.builder()
                .email(userCreateRequest.getEmail())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .name(userCreateRequest.getName())
                .role(userCreateRequest.getRole())
                .build();

        user = userRepository.save(user);
        log.info("User created successfully with id: {}", user.getId());

        return CompletableFuture.completedFuture(user.toDto());
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<UserDto> update(@NotNull UUID id, @NotNull UserUpdateRequest userUpdateRequest) {
        log.info("Updating user with id: {}", id);

        return CompletableFuture.completedFuture(userRepository.findById(id))
                .thenApply(optionalUser -> optionalUser.orElseThrow(() ->
                        new IllegalArgumentException("User with id " + id + " not found")))
                .thenApply(user -> {
                    user.setEmail(userUpdateRequest.getEmail());
                    user.setName(userUpdateRequest.getName());
                    user.setRole(userUpdateRequest.getRole());
                    return user;
                })
                .thenApply(userRepository::save)
                .thenApply(user -> {
                    log.info("User updated successfully with id: {}", user.getId());
                    return user.toDto();
                });
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<Void> delete(@NotNull UUID id) {
        log.info("Deleting user with id: {}", id);

        return CompletableFuture.runAsync(() -> {
            if (!userRepository.existsById(id)) {
                throw new IllegalArgumentException("User with id " + id + " not found");
            }
            userRepository.deleteById(id);
            log.info("User deleted successfully with id: {}", id);
        });
    }

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<UserDto> getById(@NotNull UUID id) {
        return CompletableFuture.completedFuture(userRepository.findById(id))
                .thenApply(optionalUser -> optionalUser.orElseThrow(() ->
                        new IllegalArgumentException("User with id " + id + " not found")))
                .thenApply(User::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<Pair<List<UserDto>, PageData>> getAll(@NotNull UserFilterRequest userFilterRequest) {
        return PaginationService.getPagedAndFilteredData(userRepository, userFilterRequest, UserSpecification.class)
                .thenApply(userPage -> {
                    List<UserDto> userDtoList = userPage.stream()
                            .map(User::toDto)
                            .toList();
                    PageData pageData = new PageData(userFilterRequest.getPageNumber(), userPage.getTotalElements(), userPage.getTotalPages());
                    return Pair.of(userDtoList, pageData);
                });
    }
}
