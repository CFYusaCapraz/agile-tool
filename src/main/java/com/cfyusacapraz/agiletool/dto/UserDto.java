package com.cfyusacapraz.agiletool.dto;

import com.cfyusacapraz.agiletool.domain.enums.Roles;
import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseEntityDto<UUID> {

    private String email;

    private String name;

    private Roles role;

    private TeamDto team;
}
