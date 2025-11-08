package com.cfyusacapraz.agiletool.util;

import com.cfyusacapraz.agiletool.api.request.base.BasePagedApiRequest;
import com.cfyusacapraz.agiletool.api.request.base.PaginationData;
import com.cfyusacapraz.agiletool.domain.base.BaseEntity;
import com.cfyusacapraz.agiletool.dto.base.BaseEntityDto;
import com.cfyusacapraz.agiletool.repository.BaseEntityRepository;
import com.cfyusacapraz.agiletool.repository.spec.BaseEntitySpecification;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PaginationService {

    public static @NotNull Pageable createPageable(@NotNull PaginationData paginationData) {
        List<Sort.Order> orders = new ArrayList<>();
        paginationData.getSortRules().forEach(sortRule -> {
            Sort.Order order = sortRule.contains("-") ? Sort.Order.desc(sortRule.substring(0, sortRule.length() - 1)) : Sort.Order.asc(sortRule);
            orders.add(order);
        });
        Sort sort = Sort.by(orders);
        return PageRequest.of(paginationData.getPageNumber(), paginationData.getPageSize(), sort);
    }

    public static <E extends BaseEntity<ID, D>, D extends BaseEntityDto<ID>, R extends BaseEntityRepository<E, D, ID>, ID extends Serializable>
    @NotNull CompletableFuture<Page<E>> getPagedData(@NotNull BaseEntityRepository<E, D, ID> repository, @NotNull PaginationData paginationData) {
        Pageable pageable = createPageable(paginationData);
        return CompletableFuture.completedFuture(repository.findAll(pageable));
    }

    public static <E extends BaseEntity<ID, D>, D extends BaseEntityDto<ID>, R extends BaseEntityRepository<E, D, ID>, ID extends Serializable>
    @NotNull CompletableFuture<Page<E>> getPagedAndFilteredData(@NotNull BaseEntityRepository<E, D, ID> repository, @NotNull PaginationData paginationData,
                                                                @NotNull Specification<E> specification) {
        Pageable pageable = createPageable(paginationData);
        return CompletableFuture.completedFuture(repository.findAll(specification, pageable));
    }

    @SneakyThrows
    public static <E extends BaseEntity<ID, D>, D extends BaseEntityDto<ID>, R extends BaseEntityRepository<E, D, ID>, ID extends Serializable, Q extends BasePagedApiRequest, S extends BaseEntitySpecification<E, Q>>
    @NotNull CompletableFuture<Page<E>> getPagedAndFilteredData(@NotNull BaseEntityRepository<E, D, ID> repository, @NotNull Q filterRequest, Class<S> specificationClass) {
        Pageable pageable = createPageable(filterRequest.toPaginationData());
        S specificationInstance = specificationClass.getDeclaredConstructor(filterRequest.getClass()).newInstance(filterRequest);
        Specification<E> specification = specificationInstance.toSpecification();
        return CompletableFuture.completedFuture(repository.findAll(specification, pageable));
    }
}
