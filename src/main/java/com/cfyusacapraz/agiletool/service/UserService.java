package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.UserCreateRequest;
import com.cfyusacapraz.agiletool.api.request.UserUpdateRequest;
import com.cfyusacapraz.agiletool.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    @PreAuthorize(value = "hasRole(ADMIN)")
    CompletableFuture<UserDto> create(@NotNull UserCreateRequest userCreateRequest);

    @PreAuthorize("hasRole(ADMIN) or authentication.principal.id == #id")
    CompletableFuture<UserDto> update(@NotNull UUID id, @NotNull UserUpdateRequest userUpdateRequest);
}
