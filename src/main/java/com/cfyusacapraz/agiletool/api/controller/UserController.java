package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.UserCreateRequest;
import com.cfyusacapraz.agiletool.api.request.UserUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.base.SaveEntityResponse;
import com.cfyusacapraz.agiletool.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = ApiEndpoints.USER_BASE_URL, produces = ApiEndpoints.RESPONSE_CONTENT_TYPE)
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public CompletableFuture<SaveEntityResponse<UUID>> createUser(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest)
                .thenApply(userDto -> new SaveEntityResponse<>(userDto.getId()));
    }

    @PutMapping(path = "/{userId}")
    public CompletableFuture<SaveEntityResponse<UUID>> updateUser(@PathVariable("userId") UUID id, @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.update(id, userUpdateRequest)
                .thenApply(userDto -> new SaveEntityResponse<>(userDto.getId()));
    }
}
