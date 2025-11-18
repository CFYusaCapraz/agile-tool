package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.UserCreateRequest;
import com.cfyusacapraz.agiletool.api.request.UserFilterRequest;
import com.cfyusacapraz.agiletool.api.request.UserUpdateRequest;
import com.cfyusacapraz.agiletool.api.response.UserResponse;
import com.cfyusacapraz.agiletool.api.response.base.*;
import com.cfyusacapraz.agiletool.dto.UserDto;
import com.cfyusacapraz.agiletool.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = ApiEndpoints.USER_BASE_URL, produces = ApiEndpoints.RESPONSE_CONTENT_TYPE)
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public SaveEntityResponse<UUID> createUser(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        UserDto userDto = userService.create(userCreateRequest);
        return new SaveEntityResponse<>(userDto);
    }

    @PutMapping(path = "/{userId}")
    public SaveEntityResponse<UUID> updateUser(@PathVariable("userId") UUID id,
                                               @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        UserDto userDto = userService.update(id, userUpdateRequest);
        return new SaveEntityResponse<>(userDto);
    }

    @DeleteMapping(path = "/{userId}")
    public BaseApiResponse deleteUser(@PathVariable("userId") UUID id) {
        userService.delete(id);
        return new BaseApiResponse(null, true);
    }

    @GetMapping(path = "/{userId}")
    public SingleResultResponse<UserResponse> getUser(@PathVariable("userId") UUID id) {
        UserDto userDto = userService.getById(id);
        return new SingleResultResponse<>(new UserResponse(userDto));
    }

    @GetMapping
    public PagedListResultResponse<List<UserResponse>> getAllUsers(@Validated UserFilterRequest userFilterRequest) {
        Pair<List<UserDto>, PageData> dataPair = userService.getAll(userFilterRequest);
        List<UserResponse> userResponses = dataPair.getFirst().stream().map(UserResponse::new).toList();
        return new PagedListResultResponse<>(userResponses, dataPair.getSecond());
    }
}
