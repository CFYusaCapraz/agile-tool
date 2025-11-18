package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.Permission;
import com.cfyusacapraz.agiletool.dto.PermissionDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, config = BaseEntityMapperConfig.class,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR, uses = RolePermissionMapper.class)
public interface PermissionMapper {

    PermissionDto toDto(Permission permission, @Context CycleAvoidingMappingContext context);

    Permission toEntity(PermissionDto permissionDto, @Context CycleAvoidingMappingContext context);
}
