package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.Role;
import com.cfyusacapraz.agiletool.dto.RoleDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, config = BaseEntityMapperConfig.class,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR, uses = RolePermissionMapper.class)
public interface RoleMapper {

    RoleDto toDto(Role role, @Context CycleAvoidingMappingContext context);

    Role toEntity(RoleDto roleDto, @Context CycleAvoidingMappingContext context);
}
