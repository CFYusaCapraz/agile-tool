package com.cfyusacapraz.agiletool.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum Roles {
    ROLE_USER(Set.of(
            Permissions.TEAM_READ,
            Permissions.RETRO_READ,
            Permissions.RETRO_PARTICIPATE
    )),

    ROLE_SCRUM_MASTER(Set.of(
            Permissions.TEAM_READ,
            Permissions.TEAM_UPDATE,
            Permissions.RETRO_CREATE,
            Permissions.RETRO_READ,
            Permissions.RETRO_UPDATE,
            Permissions.RETRO_DELETE,
            Permissions.RETRO_PARTICIPATE,
            Permissions.RETRO_REVEAL_ITEMS
    )),

    ROLE_ADMIN(Set.of(
            Permissions.USER_CREATE,
            Permissions.USER_READ,
            Permissions.USER_UPDATE,
            Permissions.USER_DELETE,
            Permissions.TEAM_CREATE,
            Permissions.TEAM_READ,
            Permissions.TEAM_UPDATE,
            Permissions.TEAM_DELETE,
            Permissions.RETRO_CREATE,
            Permissions.RETRO_READ,
            Permissions.RETRO_UPDATE,
            Permissions.RETRO_DELETE,
            Permissions.RETRO_PARTICIPATE,
            Permissions.RETRO_REVEAL_ITEMS
    ));

    private final Set<Permissions> permissions;
}
