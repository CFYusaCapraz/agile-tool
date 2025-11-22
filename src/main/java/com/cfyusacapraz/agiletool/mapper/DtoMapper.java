package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.*;
import com.cfyusacapraz.agiletool.domain.base.AuditMetadata;
import com.cfyusacapraz.agiletool.dto.*;
import com.cfyusacapraz.agiletool.dto.base.AuditMetadataDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class DtoMapper {

    public abstract AuditMetadataDto toDto(AuditMetadata auditMetadata);

    public abstract PermissionDto toDto(Permission permission, @Context CycleAvoidingMappingContext context);

    public abstract RetroColumnDto toDto(RetroColumn retroColumn, @Context CycleAvoidingMappingContext context);

    public abstract RetroItemDto toDto(RetroItem retroItem, @Context CycleAvoidingMappingContext context);

    public abstract RetroDto toDto(Retro retro, @Context CycleAvoidingMappingContext context);

    public abstract RoleDto toDto(Role role, @Context CycleAvoidingMappingContext context);

    public abstract RolePermissionDto toDto(RolePermission rolePermission, @Context CycleAvoidingMappingContext context);

    public abstract TeamDto toDto(Team team, @Context CycleAvoidingMappingContext context);

    public abstract UserDto toDto(User user, @Context CycleAvoidingMappingContext context);
}
