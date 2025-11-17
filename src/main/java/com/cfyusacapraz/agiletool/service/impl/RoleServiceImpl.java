package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.domain.Role;
import com.cfyusacapraz.agiletool.dto.RoleDto;
import com.cfyusacapraz.agiletool.repository.RoleRepository;
import com.cfyusacapraz.agiletool.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    @Async
    public CompletableFuture<RoleDto> getById(@NotNull UUID id) {
        return CompletableFuture.completedFuture(roleRepository.findById(id))
                .thenApply(optionalRole -> optionalRole.orElseThrow(
                        () -> new IllegalArgumentException("Role with id " + id + " not found")))
                .thenApply(Role::toDto);
    }
}
