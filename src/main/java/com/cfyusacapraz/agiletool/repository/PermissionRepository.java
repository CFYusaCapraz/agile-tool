package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.Permission;
import com.cfyusacapraz.agiletool.dto.PermissionDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends BaseEntityRepository<Permission, PermissionDto, UUID> {

    @Query("select p from Permission p where p.name = :name")
    Optional<Permission> findByName(@Param("name") String name);
}
