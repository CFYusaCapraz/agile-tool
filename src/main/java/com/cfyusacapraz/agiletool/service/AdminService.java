package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.api.request.UserCreationRequest;
import com.cfyusacapraz.agiletool.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.concurrent.CompletableFuture;

public interface AdminService {

    @PreAuthorize(value = "hasRole(ADMIN)")
    CompletableFuture<UserDto> createUser(@NotNull UserCreationRequest userCreationRequest);
}
