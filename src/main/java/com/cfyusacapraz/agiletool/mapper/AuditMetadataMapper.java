package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.base.AuditMetadata;
import com.cfyusacapraz.agiletool.dto.base.AuditMetadataDto;
import org.mapstruct.Mapper;

@Mapper
public interface AuditMetadataMapper {

    AuditMetadataDto toDto(AuditMetadata auditMetadata);

    AuditMetadata toEntity(AuditMetadataDto auditMetadataDto);
}
