package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.dto.RoleDto;
import com.cfyusacapraz.agiletool.mapper.DtoMapper;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import com.cfyusacapraz.agiletool.repository.RoleRepository;
import com.cfyusacapraz.agiletool.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final DtoMapper dtoMapper;

    @Override
    @Transactional(readOnly = true)
    public RoleDto getById(@NotNull UUID id) {
        return dtoMapper.toDto(roleRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Role with id " + id + " not found")),
                new CycleAvoidingMappingContext());
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto getByName(@NotNull String name) {
        return dtoMapper.toDto(roleRepository.findByName(name)
                        .orElseThrow(() -> new IllegalArgumentException("Role with name " + name + " not found")),
                new CycleAvoidingMappingContext());
    }
}
