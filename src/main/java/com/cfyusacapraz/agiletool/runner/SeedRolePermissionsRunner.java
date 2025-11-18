package com.cfyusacapraz.agiletool.runner;

import com.cfyusacapraz.agiletool.domain.Permission;
import com.cfyusacapraz.agiletool.domain.Role;
import com.cfyusacapraz.agiletool.domain.RolePermission;
import com.cfyusacapraz.agiletool.domain.enums.Permissions;
import com.cfyusacapraz.agiletool.domain.enums.Roles;
import com.cfyusacapraz.agiletool.repository.PermissionRepository;
import com.cfyusacapraz.agiletool.repository.RolePermissionRepository;
import com.cfyusacapraz.agiletool.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class SeedRolePermissionsRunner implements ApplicationRunner {

    private final RolePermissionRepository rolePermissionRepository;

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    @Override
    public void run(ApplicationArguments args) {
        Arrays.stream(Roles.values())
                .parallel()
                // map enum -> (Roles, Optional<Role>)
                .map(roleEnum -> new AbstractMap.SimpleEntry<>(roleEnum, roleRepository.findByName(roleEnum.name())))
                // keep only found roles
                .filter(entry -> entry.getValue().isPresent())
                // map -> (Roles, Role)
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().get()))
                // expand each role to its permissions -> stream of (Role, Permissions)
                .flatMap(entry -> entry.getKey().getPermissions().stream()
                        .map(permissionEnum -> new AbstractMap.SimpleEntry<>(entry.getValue(), permissionEnum)))
                // for each pair, resolve Permission entity and persist missing RolePermission
                .forEach(entry -> {
                    Role role = entry.getKey();
                    Permissions permissionEnum = entry.getValue();

                    Optional<Permission> optionalPermission = permissionRepository.findByName(permissionEnum.name());
                    if (optionalPermission.isEmpty()) {
                        log.warn("Permission `{}` not found in database, skipping.", permissionEnum.name());
                        return;
                    }

                    Permission permission = optionalPermission.get();
                    Optional<RolePermission> existing = rolePermissionRepository.findByRoleAndPermission(role, permission);
                    if (existing.isPresent()) {
                        return;
                    }

                    RolePermission rp = new RolePermission();
                    rp.setRole(role);
                    rp.setPermission(permission);
                    rolePermissionRepository.save(rp);
                    log.info("Seeded permission `{}` for role `{}`", permissionEnum.name(), role.getName());
                });
    }
}
