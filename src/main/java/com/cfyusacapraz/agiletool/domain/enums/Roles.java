package com.cfyusacapraz.agiletool.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum Roles {
    ROLE_USER(Set.of(
            Permissions.TEAM_READ
    )),

    ROLE_SCRUM_MASTER(Set.of(
            Permissions.TEAM_READ,
            Permissions.TEAM_UPDATE
    )),

    ROLE_ADMIN(Set.of(
            Permissions.USER_CREATE,
            Permissions.USER_READ,
            Permissions.USER_UPDATE,
            Permissions.USER_DELETE,
            Permissions.TEAM_CREATE,
            Permissions.TEAM_READ,
            Permissions.TEAM_UPDATE,
            Permissions.TEAM_DELETE
    ));

    private final Set<Permissions> permissions;
}
