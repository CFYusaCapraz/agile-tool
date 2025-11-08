package com.cfyusacapraz.agiletool.api.response;

import com.cfyusacapraz.agiletool.domain.enums.TeamStatus;
import com.cfyusacapraz.agiletool.dto.TeamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse implements Serializable {

    private String name;

    private TeamStatus status;

    private Set<UUID> members = new HashSet<>();

    private UUID scrumMaster;

    public TeamResponse(@NotNull TeamDto teamDto) {
        this.name = teamDto.getName();
        this.status = teamDto.getStatus();
        teamDto.getMembers().forEach(userDto -> this.members.add(userDto.getId()));
        this.scrumMaster = teamDto.getScrumMaster().getId();
    }
}
