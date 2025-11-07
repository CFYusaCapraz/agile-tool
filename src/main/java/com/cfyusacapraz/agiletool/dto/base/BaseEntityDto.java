package com.cfyusacapraz.agiletool.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntityDto<ID extends Serializable> {

    private ID id;

    private AuditMetadataDto auditMetadata;
}
