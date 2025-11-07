package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.domain.enums.RetroStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "retros")
public class Retro extends BaseEntity<UUID> {

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
    private RetroStatus status = RetroStatus.DRAFT;

    @Column(name = "scheduled_date")
    private OffsetDateTime scheduledDate;

    @OneToMany(mappedBy = "retro", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("order ASC")
    private Set<RetroColumn> columns = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "retro_participants",
            joinColumns = @JoinColumn(name = "retro_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();
}
