package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.RetroColumnDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "retro_columns")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RetroColumn extends BaseEntity<Long, RetroColumnDto> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retro_id", nullable = false)
    private Retro retro;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 7)
    private String color; // Hex color code (e.g., "#FF5733")

    @Column(nullable = false, name = "column_order")
    private int order;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<RetroItem> items = new HashSet<>();
}
