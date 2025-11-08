package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.Retro;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RetroRepository extends BaseEntityRepository<Retro, RetroDto, UUID> {
}