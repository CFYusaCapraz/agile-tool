package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.Permission;
import com.cfyusacapraz.agiletool.domain.Role;
import com.cfyusacapraz.agiletool.domain.RolePermission;
import com.cfyusacapraz.agiletool.dto.RolePermissionDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolePermissionRepository extends BaseEntityRepository<RolePermission, RolePermissionDto, Long> {

    @Query("select r from RolePermission r where r.role = :role and r.permission = :permission")
    Optional<RolePermission> findByRoleAndPermission(@Param("role") Role role,
                                                     @Param("permission") Permission permission);
}
