package com.cfyusacapraz.agiletool.repository.spec;

import com.cfyusacapraz.agiletool.api.request.TeamFilterRequest;
import com.cfyusacapraz.agiletool.domain.Team;
import com.cfyusacapraz.agiletool.domain.Team_;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class TeamSpecification extends BaseEntitySpecification<Team, TeamFilterRequest> {

    public TeamSpecification(@NotNull TeamFilterRequest teamFilterRequest) {
        super(teamFilterRequest);
        specifications.add(nameLike());
        specifications.add(statusesIn());
    }

    @Contract(pure = true)
    private @Nullable Specification<Team> statusesIn() {
        return filterRequest.getStatuses() != null ?
                (root, query, criteriaBuilder) -> root.get(Team_.status).in(filterRequest.getStatuses())
                : null;
    }

    @Contract(pure = true)
    private @Nullable Specification<Team> nameLike() {
        return filterRequest.getName() != null
                ? (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get(Team_.name)),
                "%" + filterRequest.getName().toLowerCase() + "%"
        ) : null;
    }
}
