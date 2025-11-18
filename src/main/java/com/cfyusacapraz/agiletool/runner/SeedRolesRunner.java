package com.cfyusacapraz.agiletool.runner;

import com.cfyusacapraz.agiletool.domain.Role;
import com.cfyusacapraz.agiletool.domain.enums.Roles;
import com.cfyusacapraz.agiletool.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(0)
public class SeedRolesRunner implements ApplicationRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.trace("Seeding roles.");
        Arrays.stream(Roles.values()).parallel().forEach(roleEnum -> {
            String roleName = roleEnum.name();
            roleRepository.findByName(roleName).orElseGet(() -> {
                log.trace("Seeding role: {}", roleName);
                return roleRepository.save(Role.builder().name(roleName).build());
            });
        });
        log.trace("Finished seeding roles.");
    }
}
