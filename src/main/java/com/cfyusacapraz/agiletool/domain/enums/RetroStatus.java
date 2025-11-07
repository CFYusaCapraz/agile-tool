package com.cfyusacapraz.agiletool.domain.enums;

public enum RetroStatus {
    DRAFT,           // Retro is being set up
    COLLECTING,      // Participants adding items (items hidden)
    DISCUSSING,      // Items revealed, team discussing
    VOTING,          // Team voting on items
    COMPLETED        // Retro is finished
}