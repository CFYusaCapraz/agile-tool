package com.cfyusacapraz.agiletool.repository.spec;

import com.cfyusacapraz.agiletool.api.request.base.BasePagedApiRequest;
import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntitySpecification<E extends BaseEntity<?, ?>, R extends BasePagedApiRequest> {

    protected final R filterRequest;

    protected final List<Specification<E>> specifications = new ArrayList<>();

    protected BaseEntitySpecification(R filterRequest) {
        this.filterRequest = filterRequest;
    }

    public Specification<E> toSpecification() {
        return Specification.allOf(specifications);
    }
}
