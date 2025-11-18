package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.RolePermissionDto;
import com.cfyusacapraz.agiletool.mapper.RolePermissionMapper;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "role_permissions")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RolePermission extends BaseEntity<Long, RolePermissionDto> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @Override
    public RolePermissionDto toDto() {
        return RolePermissionMapper.INSTANCE.toDto(this, new CycleAvoidingMappingContext());
    }

    @Override
    public RolePermission fromDto(RolePermissionDto dto) {
        return RolePermissionMapper.INSTANCE.toEntity(dto, new CycleAvoidingMappingContext());
    }
}
