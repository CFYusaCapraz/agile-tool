package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.Role;
import com.cfyusacapraz.agiletool.dto.RoleDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends BaseEntityRepository<Role, RoleDto, UUID> {

    @Query("select r from Role r where r.name = :name")
    Optional<Role> findByName(@Param("name") String name);
}
