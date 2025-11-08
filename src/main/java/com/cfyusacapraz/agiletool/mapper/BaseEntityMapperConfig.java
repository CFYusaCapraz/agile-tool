package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import org.mapstruct.MapperConfig;

import java.io.Serializable;

@MapperConfig(uses = {AuditMetadataMapper.class})
public interface BaseEntityMapperConfig<D extends BaseEntityDto<ID>, ID extends Serializable> {

    BaseEntityDto<ID> toDto(BaseEntity<ID, D> entity);

    BaseEntity<ID, D> toEntity(BaseEntityDto<ID> dto);
}
