package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.api.request.UserCreateRequest;
import com.cfyusacapraz.agiletool.api.request.UserFilterRequest;
import com.cfyusacapraz.agiletool.api.request.UserUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.base.PageData;
import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.dto.RoleDto;
import com.cfyusacapraz.agiletool.dto.UserDto;
import com.cfyusacapraz.agiletool.mapper.DtoMapper;
import com.cfyusacapraz.agiletool.mapper.EntityMapper;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import com.cfyusacapraz.agiletool.repository.UserRepository;
import com.cfyusacapraz.agiletool.repository.spec.UserSpecification;
import com.cfyusacapraz.agiletool.service.RoleService;
import com.cfyusacapraz.agiletool.service.UserService;
import com.cfyusacapraz.agiletool.util.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private final DtoMapper dtoMapper;

    private final EntityMapper entityMapper;

    @Override
    @Transactional
    public UserDto create(@NotNull UserCreateRequest userCreateRequest) {
        log.info("Creating user with email: {}", userCreateRequest.getEmail());

        userRepository.findByEmail(userCreateRequest.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + userCreateRequest.getEmail() + " already exists");
        });

        RoleDto roleDto = roleService.getById(userCreateRequest.getRoleId());

        User user = User.builder().email(userCreateRequest.getEmail())
                .password(passwordEncoder.encode(userCreateRequest.getPassword())).name(userCreateRequest.getName())
                .role(entityMapper.toEntity(roleDto, new CycleAvoidingMappingContext())).build();
        User savedUser = userRepository.save(user);

        log.info("User created successfully with id: {}", user.getId());
        return dtoMapper.toDto(savedUser, new CycleAvoidingMappingContext());

    }

    @Override
    @Transactional
    public UserDto update(@NotNull UUID id, @NotNull UserUpdateRequest userUpdateRequest) {
        log.info("Updating user with id: {}", id);

        User user = getUserEntityById(id);

        RoleDto roleDto = roleService.getById(userUpdateRequest.getRoleId());

        user.setEmail(userUpdateRequest.getEmail());
        user.setName(userUpdateRequest.getName());
        user.setRole(entityMapper.toEntity(roleDto, new CycleAvoidingMappingContext()));

        User updatedUser = userRepository.save(user);

        log.info("User updated successfully with id: {}", user.getId());
        return dtoMapper.toDto(updatedUser, new CycleAvoidingMappingContext());
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID id) {
        log.info("Deleting user with id: {}", id);

        User user = getUserEntityById(id);
        userRepository.delete(user);

        log.info("User deleted successfully with id: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getById(@NotNull UUID id) {
        return dtoMapper.toDto(getUserEntityById(id), new CycleAvoidingMappingContext());
    }

    @Override
    @Transactional(readOnly = true)
    public Pair<List<UserDto>, PageData> getAll(@NotNull UserFilterRequest userFilterRequest) {
        Page<User> userPage =
                PaginationService.getPagedAndFilteredData(userRepository, userFilterRequest, UserSpecification.class);
        List<UserDto> userDtoList =
                userPage.stream().map(user -> dtoMapper.toDto(user, new CycleAvoidingMappingContext())).toList();
        PageData pageData =
                new PageData(userFilterRequest.getPageNumber(), userPage.getTotalElements(), userPage.getTotalPages());
        return Pair.of(userDtoList, pageData);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getByEmail(@NotNull String email) {
        return dtoMapper.toDto(userRepository.findByEmail(email)
                        .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found")),
                new CycleAvoidingMappingContext());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    private User getUserEntityById(@NotNull UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }
}
