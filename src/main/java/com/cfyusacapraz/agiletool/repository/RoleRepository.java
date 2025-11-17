package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.Role;
import com.cfyusacapraz.agiletool.dto.RoleDto;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends BaseEntityRepository<Role, RoleDto, UUID> {
}
