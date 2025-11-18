package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.base.AuditMetadata;
import com.cfyusacapraz.agiletool.dto.base.AuditMetadataDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuditMetadataMapper {

    AuditMetadataDto toDto(AuditMetadata auditMetadata);

    AuditMetadata toEntity(AuditMetadataDto auditMetadataDto);
}
