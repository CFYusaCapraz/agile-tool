package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import com.cfyusacapraz.agiletool.mapper.TeamMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "teams")
@NoArgsConstructor
@AllArgsConstructor
public class Team extends BaseEntity<UUID, TeamDto> {

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "team")
    private Set<User> members = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "scrum_master_id")
    private User scrumMaster;

    @Column(nullable = false)
    private String emergencyCode;

    @Override
    public TeamDto toDto() {
        return TeamMapper.INSTANCE.toDto(this);
    }

    @Override
    public Team fromDto(TeamDto dto) {
        return TeamMapper.INSTANCE.toEntity(dto);
    }
}