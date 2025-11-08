package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity<ID, D>, D extends BaseEntityDto<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
}