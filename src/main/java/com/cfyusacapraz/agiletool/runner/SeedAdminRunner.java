package com.cfyusacapraz.agiletool.runner;

import com.cfyusacapraz.agiletool.api.request.UserCreateRequest;
import com.cfyusacapraz.agiletool.domain.Role;
import com.cfyusacapraz.agiletool.domain.enums.Roles;
import com.cfyusacapraz.agiletool.dto.RoleDto;
import com.cfyusacapraz.agiletool.dto.UserDto;
import com.cfyusacapraz.agiletool.properties.SeedAdminProperties;
import com.cfyusacapraz.agiletool.service.RoleService;
import com.cfyusacapraz.agiletool.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnBooleanProperty(prefix = "agiletool.seed-admin", name = "enabled")
@RequiredArgsConstructor
@Slf4j
@Order(3)
public class SeedAdminRunner implements ApplicationRunner {

    private final UserService userService;

    private final RoleService roleService;

    private final SeedAdminProperties seedAdminProperties;

    @Override
    public void run(ApplicationArguments args) {
        SecurityContext previous = SecurityContextHolder.getContext();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("system", null,
                        List.of(new SimpleGrantedAuthority(Roles.ROLE_ADMIN.name())));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        Role role;

        try {
            RoleDto roleDto = roleService.getByName(Roles.ROLE_ADMIN.name());
            log.trace("Role with name {} found", roleDto.getName());
            role = new Role().fromDto(roleDto);
        } catch (Exception exception) {
            log.error("Role with name {} not found", Roles.ROLE_ADMIN.name(), exception);
            return;
        }

        String email = seedAdminProperties.getEmail().trim().toLowerCase();

        try {
            UserDto existing = userService.getByEmail(email);
            if (existing != null) {
                log.info("Default admin already exists: {}", email);
                return;
            }
        } catch (Exception e) {
            if (!isNotFound(e)) {
                log.error("Failed to check existing user for {}: {}", email, e.getMessage(), e);
                return;
            }
            log.debug("No existing user for {}, creating one", email);
        } finally {
            SecurityContextHolder.setContext(previous);
        }

        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setEmail(email);
        userCreateRequest.setPassword(seedAdminProperties.getPassword());
        userCreateRequest.setName(seedAdminProperties.getName());
        userCreateRequest.setRoleId(role.getId());


        try {
            userService.create(userCreateRequest);
            log.trace("Seeded default admin user: {}", email);
        } catch (Exception e) {
            log.error("Failed to seed default admin user: {}", email, e);
        } finally {
            SecurityContextHolder.setContext(previous);
        }
    }

    private boolean isNotFound(Throwable t) {
        Throwable cur = t;
        while (cur != null) {
            String className = cur.getClass().getSimpleName().toLowerCase();
            String msg = cur.getMessage() == null ? "" : cur.getMessage().toLowerCase();
            if (className.contains("notfound") || className.contains("nosuch") || msg.contains("not found") ||
                    msg.contains("no such")) {
                return true;
            }
            cur = cur.getCause();
        }
        return false;
    }
}
