package com.cfyusacapraz.agiletool.api.request;

import com.cfyusacapraz.agiletool.domain.enums.TeamStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamCreateRequest implements Serializable {

    @NotBlank
    private String name;

    @NotNull
    private TeamStatus status;

    @NotNull
    private UUID scrumMaster;
}
