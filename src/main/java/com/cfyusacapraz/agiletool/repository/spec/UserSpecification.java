package com.cfyusacapraz.agiletool.repository.spec;

import com.cfyusacapraz.agiletool.api.request.UserFilterRequest;
import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.domain.User_;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification extends BaseEntitySpecification<User, UserFilterRequest> {

    public UserSpecification(UserFilterRequest filterRequest) {
        super(filterRequest);

        specifications.add(emailLike());
        specifications.add(nameLike());
    }

    @Contract(pure = true)
    private @Nullable Specification<User> nameLike() {
        return filterRequest.getName() != null
                ? (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get(User_.name)),
                "%" + filterRequest.getName().toLowerCase() + "%"
        ) : null;
    }

    @Contract(pure = true)
    private @Nullable Specification<User> emailLike() {
        return filterRequest.getEmail() != null
                ? (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get(User_.email)),
                "%" + filterRequest.getEmail().toLowerCase() + "%"
        ) : null;
    }

}
