package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "retro_columns")
public class RetroColumn extends BaseEntity<UUID> {

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
    private Set<RetroItem> items = new HashSet<>();
}
