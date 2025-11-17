package com.cfyusacapraz.agiletool.domain;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.PermissionDto;
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
@Table(name = "permissions")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission extends BaseEntity<UUID, PermissionDto> {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "permission")
    private Set<RolePermission> rolePermissions;

    @Override
    public PermissionDto toDto() {
        return null;
    }

    @Override
    public BaseEntity<UUID, PermissionDto> fromDto(PermissionDto dto) {
        return null;
    }
}
