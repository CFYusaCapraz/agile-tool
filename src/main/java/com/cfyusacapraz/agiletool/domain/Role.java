package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.RoleDto;
import com.cfyusacapraz.agiletool.mapper.RoleMapper;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity<UUID, RoleDto> {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<RolePermission> rolePermissions;

    @Override
    public RoleDto toDto() {
        return RoleMapper.INSTANCE.toDto(this, new CycleAvoidingMappingContext());
    }

    @Override
    public Role fromDto(RoleDto dto) {
        return RoleMapper.INSTANCE.toEntity(dto, new CycleAvoidingMappingContext());
    }
}
