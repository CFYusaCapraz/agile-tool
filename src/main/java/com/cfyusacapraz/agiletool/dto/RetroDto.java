package com.cfyusacapraz.agiletool.dto;

import com.cfyusacapraz.agiletool.domain.enums.RetroStatus;
import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetroDto extends BaseEntityDto<UUID> {

    private String title;

    private String description;

    private TeamDto team;

    private UserDto createdBy;

    private RetroStatus status = RetroStatus.DRAFT;

    private OffsetDateTime scheduledDate;

    private Set<RetroColumnDto> columns = new HashSet<>();

    private Set<UserDto> participants = new HashSet<>();
}
