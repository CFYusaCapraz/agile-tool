package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.domain.enums.TeamStatus;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "teams")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Team extends BaseEntity<UUID, TeamDto> {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TeamStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "team")
    @Builder.Default
    private Set<User> members = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "scrum_master_id")
    private User scrumMaster;

    @Column(nullable = false)
    private String emergencyCode;
}