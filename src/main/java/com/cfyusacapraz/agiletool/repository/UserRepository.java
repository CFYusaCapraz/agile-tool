package com.cfyusacapraz.agiletool.repository;

import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.dto.UserDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseEntityRepository<User, UserDto, UUID> {

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}