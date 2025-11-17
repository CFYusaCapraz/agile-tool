package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.UserDto;
import com.cfyusacapraz.agiletool.mapper.UserMapper;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity<UUID, UserDto> implements UserDetails {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    private Team team;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities =
                role.getRolePermissions().stream().map(RolePermission::getPermission)
                        .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                        .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public UserDto toDto() {
        return UserMapper.INSTANCE.toDTO(this, new CycleAvoidingMappingContext());
    }

    @Override
    public User fromDto(UserDto dto) {
        return UserMapper.INSTANCE.toEntity(dto, new CycleAvoidingMappingContext());
    }
}
