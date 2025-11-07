package com.cfyusacapraz.agiletool.dto;

import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetroItemDto extends BaseEntityDto<Long> {

    private RetroColumnDto column;

    private UserDto createdBy;

    private String content;

    private int voteCount;

    private boolean isVisible;
}
