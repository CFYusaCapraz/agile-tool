package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.domain.enums.RetroStatus;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "retros")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Retro extends BaseEntity<UUID, RetroDto> {

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private User createdBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private RetroStatus status = RetroStatus.DRAFT;

    @Column(name = "scheduled_date")
    private OffsetDateTime scheduledDate;

    @OneToMany(mappedBy = "retro", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("order ASC")
    @Builder.Default
    private Set<RetroColumn> columns = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "retro_participants", joinColumns = @JoinColumn(name = "retro_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Builder.Default
    private Set<User> participants = new HashSet<>();
}
