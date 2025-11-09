package com.cfyusacapraz.agiletool.api.response;

import com.cfyusacapraz.agiletool.dto.RetroDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RetroDetailResponse extends RetroListResponse implements Serializable {

    private Set<RetroColumnDetailResponse> columns = new HashSet<>();

    public RetroDetailResponse(@NotNull RetroDto retroDto) {
        super(retroDto);
        retroDto.getColumns().forEach(column -> this.columns.add(new RetroColumnDetailResponse(column)));
    }
}
