package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface UserRepository extends BaseEntityRepository<User, UUID> {

    @Query("select u from User u where u.email = :email")
    @Async
    CompletableFuture<Optional<User>> findByEmail(@Param("email") String email);
}