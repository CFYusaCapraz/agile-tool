package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.dto.RoleDto;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface RoleService {

    CompletableFuture<RoleDto> getById(@NotNull UUID id);

    CompletableFuture<RoleDto> getByName(@NotNull String name);
}
