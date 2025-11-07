package com.cfyusacapraz.agiletool.dto;

import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto extends BaseEntityDto<UUID> {

    private String name;

    private Set<UserDto> members = new HashSet<>();

    private UserDto scrumMaster;

    private String emergencyCode;
}
