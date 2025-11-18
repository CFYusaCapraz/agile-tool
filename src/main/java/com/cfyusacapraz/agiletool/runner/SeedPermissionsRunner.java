package com.cfyusacapraz.agiletool.runner;

import com.cfyusacapraz.agiletool.domain.Permission;
import com.cfyusacapraz.agiletool.domain.enums.Permissions;
import com.cfyusacapraz.agiletool.repository.PermissionRepository;
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
public class SeedPermissionsRunner implements ApplicationRunner {

    private final PermissionRepository permissionRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.trace("Seeding permissions.");
        Arrays.stream(Permissions.values()).parallel()
                .forEach(permissionEnum -> {
                    String permissionName = permissionEnum.name();
                    permissionRepository.findByName(permissionName).join().orElseGet(() -> {
                        log.trace("Seeding permission: {}", permissionName);
                        return permissionRepository.save(
                                Permission.builder()
                                        .name(permissionName)
                                        .build());
                    });
                });
        log.trace("Finished seeding permissions.");
    }
}
