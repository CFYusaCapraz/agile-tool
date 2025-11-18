package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.RolePermission;
import com.cfyusacapraz.agiletool.dto.RolePermissionDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, config = BaseEntityMapperConfig.class,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR, uses = {RoleMapper.class, PermissionMapper.class})
public interface RolePermissionMapper {

    RolePermissionDto toDto(RolePermission rolePermission, @Context CycleAvoidingMappingContext context);

    RolePermission toEntity(RolePermissionDto rolePermissionDto, @Context CycleAvoidingMappingContext context);
}
