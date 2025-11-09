package com.cfyusacapraz.agiletool.api.response;

import com.cfyusacapraz.agiletool.domain.enums.RetroStatus;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetroListResponse implements Serializable {

    private String title;

    private String description;

    private RetroStatus status = RetroStatus.DRAFT;

    private OffsetDateTime scheduledDate;

    public RetroListResponse(@NotNull RetroDto retroDto) {
        this.title = retroDto.getTitle();
        this.description = retroDto.getDescription();
        this.status = retroDto.getStatus();
        this.scheduledDate = retroDto.getScheduledDate();
    }
}
