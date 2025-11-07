package com.cfyusacapraz.agiletool.domain.enums;

public enum Permissions {
    // User permissions
    USER_CREATE,
    USER_READ,
    USER_UPDATE,
    USER_DELETE,

    // Team permissions
    TEAM_CREATE,
    TEAM_READ,
    TEAM_UPDATE,
    TEAM_DELETE,

    // Retro permissions
    RETRO_CREATE,
    RETRO_READ,
    RETRO_UPDATE,
    RETRO_DELETE,
    RETRO_PARTICIPATE,
    RETRO_REVEAL_ITEMS
    // More permissions will be added later (POKER_*, RETRO_*, etc.)
}
