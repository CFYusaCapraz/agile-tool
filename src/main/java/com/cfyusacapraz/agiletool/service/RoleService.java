package com.cfyusacapraz.agiletool.service;

import com.cfyusacapraz.agiletool.dto.RoleDto;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface RoleService {

    RoleDto getById(@NotNull UUID id);

    RoleDto getByName(@NotNull String name);
}
