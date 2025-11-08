package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.RetroColumn;
import com.cfyusacapraz.agiletool.dto.RetroColumnDto;
import org.springframework.stereotype.Repository;

@Repository
public interface RetroColumnRepository extends BaseEntityRepository<RetroColumn, RetroColumnDto, Long> {
}