package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.*;
import com.cfyusacapraz.agiletool.domain.base.AuditMetadata;
import com.cfyusacapraz.agiletool.dto.*;
import com.cfyusacapraz.agiletool.dto.base.AuditMetadataDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class EntityMapper {

    public abstract AuditMetadata toEntity(AuditMetadataDto auditMetadataDto);

    public abstract Permission toEntity(PermissionDto permissionDto, @Context CycleAvoidingMappingContext context);

    public abstract RetroColumn toEntity(RetroColumnDto retroColumnDto, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "isVisible", source = "visible")
    public abstract RetroItem toEntity(RetroItemDto retroItemDto, @Context CycleAvoidingMappingContext context);

    public abstract Retro toEntity(RetroDto retroDto, @Context CycleAvoidingMappingContext context);

    public abstract Role toEntity(RoleDto roleDto, @Context CycleAvoidingMappingContext context);

    public abstract RolePermission toEntity(RolePermissionDto rolePermissionDto,
                                            @Context CycleAvoidingMappingContext context);

    public abstract Team toEntity(TeamDto teamDto, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "password", ignore = true)
    public abstract User toEntity(UserDto userDTO, @Context CycleAvoidingMappingContext context);
}
