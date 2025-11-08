package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.UserCreateRequest;
import com.cfyusacapraz.agiletool.api.request.UserFilterRequest;
import com.cfyusacapraz.agiletool.api.request.UserUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.UserResponse;
import com.cfyusacapraz.agiletool.api.response.base.BaseApiResponse;
import com.cfyusacapraz.agiletool.api.response.base.PagedListResultResponse;
import com.cfyusacapraz.agiletool.api.response.base.SaveEntityResponse;
import com.cfyusacapraz.agiletool.api.response.base.SingleResultResponse;
import com.cfyusacapraz.agiletool.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @DeleteMapping(path = "/{userId}")
    public CompletableFuture<BaseApiResponse> deleteUser(@PathVariable("userId") UUID id) {
        return userService.delete(id)
                .thenApply(aVoid -> new BaseApiResponse(null, true));
    }

    @GetMapping(path = "/{userId}")
    public CompletableFuture<SingleResultResponse<UserResponse>> getUser(@PathVariable("userId") UUID id) {
        return userService.getById(id)
                .thenApply(userDto -> new SingleResultResponse<>(new UserResponse(userDto)));
    }

    @GetMapping
    public CompletableFuture<PagedListResultResponse<List<UserResponse>>> getAllUsers(@Validated UserFilterRequest userFilterRequest) {
        return userService.getAll(userFilterRequest)
                .thenApply(pair -> {
                    List<UserResponse> userResponses = pair.getFirst().stream().map(UserResponse::new).toList();
                    return new PagedListResultResponse<>(userResponses, pair.getSecond());
                });
    }
}
