package com.cfyusacapraz.agiletool.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum RetroTemplate {
    STANDARD_4_COLUMN(List.of(
            new ColumnDefinition("What went well", "#4CAF50", 1),
            new ColumnDefinition("What didn't go well", "#F44336", 2),
            new ColumnDefinition("What can be improved", "#FF9800", 3),
            new ColumnDefinition("Action items", "#2196F3", 4)
    )),

    START_STOP_CONTINUE(List.of(
            new ColumnDefinition("Start doing", "#4CAF50", 1),
            new ColumnDefinition("Stop doing", "#F44336", 2),
            new ColumnDefinition("Continue doing", "#2196F3", 3)
    )),

    MAD_SAD_GLAD(List.of(
            new ColumnDefinition("Mad", "#F44336", 1),
            new ColumnDefinition("Sad", "#FF9800", 2),
            new ColumnDefinition("Glad", "#4CAF50", 3)
    ));

    private final List<ColumnDefinition> columns;

    public record ColumnDefinition(String title, String color, Integer order) {
    }
}
