package com.cfyusacapraz.agiletool.api.controller;

import com.cfyusacapraz.agiletool.api.constants.ApiEndpoints;
import com.cfyusacapraz.agiletool.api.request.UserCreationRequest;
import com.cfyusacapraz.agiletool.api.response.base.SaveEntityResponse;
import com.cfyusacapraz.agiletool.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = ApiEndpoints.ADMIN_BASE_URL, produces = ApiEndpoints.RESPONSE_CONTENT_TYPE)
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final AdminService adminService;

    @PostMapping(path = "/create-user")
    public CompletableFuture<SaveEntityResponse<UUID>> createUser(@Validated @RequestBody UserCreationRequest userCreationRequest) {
        return adminService.createUser(userCreationRequest)
                .thenApply(userDto -> new SaveEntityResponse<>(userDto.getId()));
    }
}
