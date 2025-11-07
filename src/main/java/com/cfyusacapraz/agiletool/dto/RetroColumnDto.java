package com.cfyusacapraz.agiletool.dto;

import com.cfyusacapraz.agiletool.domain.Retro;
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
public class RetroColumnDto extends BaseEntityDto<Long> {

    private Retro retro;

    private String title;

    private String color;

    private int order;

    private Set<RetroItemDto> items = new HashSet<>();
}
