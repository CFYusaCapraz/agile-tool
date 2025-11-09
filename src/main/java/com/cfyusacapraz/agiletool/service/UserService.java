package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.UserCreateRequest;
import com.cfyusacapraz.agiletool.api.request.UserFilterRequest;
import com.cfyusacapraz.agiletool.api.request.UserUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.base.PageData;
import com.cfyusacapraz.agiletool.dto.UserDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    @PreAuthorize(value = "hasRole('ADMIN')")
    CompletableFuture<UserDto> create(@NotNull UserCreateRequest userCreateRequest);

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    CompletableFuture<UserDto> update(@NotNull UUID id, @NotNull UserUpdateRequest userUpdateRequest);

    @PreAuthorize("hasRole('ADMIN')")
    CompletableFuture<Void> delete(@NotNull UUID id);

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    CompletableFuture<UserDto> getById(@NotNull UUID id);

    @PreAuthorize("hasRole('ADMIN')")
    CompletableFuture<Pair<List<UserDto>, PageData>> getAll(@NotNull UserFilterRequest userFilterRequest);

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.email == #email")
    CompletableFuture<UserDto> getByEmail(@NotNull String email);
}
