package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.MapperConfig;

import java.io.Serializable;

@MapperConfig(uses = {AuditMetadataMapper.class})
public interface BaseEntityMapperConfig<D extends BaseEntityDto<ID>, ID extends Serializable> {

    BaseEntityDto<ID> toDto(BaseEntity<ID, D> entity, @Context CycleAvoidingMappingContext context);

    BaseEntity<ID, D> toEntity(BaseEntityDto<ID> dto, @Context CycleAvoidingMappingContext context);
}
