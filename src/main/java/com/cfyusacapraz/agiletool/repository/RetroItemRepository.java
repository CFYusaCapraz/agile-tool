package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.RetroItem;
import com.cfyusacapraz.agiletool.dto.RetroItemDto;
import org.springframework.stereotype.Repository;

@Repository
public interface RetroItemRepository extends BaseEntityRepository<RetroItem, RetroItemDto, Long> {
}