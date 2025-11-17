package com.cfyusacapraz.agiletool.api.response;

import com.cfyusacapraz.agiletool.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    private String email;

    private String name;

    private String role;

    private UUID teamId;

    public UserResponse(@NotNull UserDto userDto) {
        this.email = userDto.getEmail();
        this.name = userDto.getName();
        this.role = userDto.getRole().getName();
        this.teamId = userDto.getTeam().getId();
    }
}
