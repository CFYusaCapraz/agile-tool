package com.cfyusacapraz.agiletool.api.response;

import com.cfyusacapraz.agiletool.dto.RetroItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetroItemDetailResponse implements Serializable {

    private String content;

    private int voteCount;

    private boolean isVisible;

    public RetroItemDetailResponse(@NotNull RetroItemDto retroItemDto) {
        this.content = retroItemDto.getContent();
        this.voteCount = retroItemDto.getVoteCount();
        this.isVisible = retroItemDto.isVisible();
    }
}
