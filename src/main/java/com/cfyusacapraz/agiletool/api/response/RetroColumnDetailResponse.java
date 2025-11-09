package com.cfyusacapraz.agiletool.api.response;

import com.cfyusacapraz.agiletool.dto.RetroColumnDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetroColumnDetailResponse implements Serializable {

    private String title;

    private String color;

    private int order;

    private Set<RetroItemDetailResponse> items = new HashSet<>();

    public RetroColumnDetailResponse(@NotNull RetroColumnDto retroColumnDto) {
        this.title = retroColumnDto.getTitle();
        this.color = retroColumnDto.getColor();
        this.order = retroColumnDto.getOrder();
        retroColumnDto.getItems().forEach(item -> this.items.add(new RetroItemDetailResponse(item)));
    }
}
