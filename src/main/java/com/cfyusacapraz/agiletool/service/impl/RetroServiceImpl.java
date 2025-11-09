package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.api.request.RetroCreateRequest;
import com.cfyusacapraz.agiletool.domain.Retro;
import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import com.cfyusacapraz.agiletool.repository.RetroRepository;
import com.cfyusacapraz.agiletool.service.AuthenticationService;
import com.cfyusacapraz.agiletool.service.RetroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetroServiceImpl implements RetroService {

    private final RetroRepository retroRepository;

    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    @Async
    public CompletableFuture<RetroDto> create(@NotNull RetroCreateRequest retroCreateRequest) {
        log.info("Creating retrospective session with title: {}", retroCreateRequest.getTitle());

        return authenticationService.getCurrentUser()
                .thenApply(userDto -> {
                    User user = new User().fromDto(userDto);
                    Retro retro = Retro.builder()
                            .title(retroCreateRequest.getTitle())
                            .description(retroCreateRequest.getDescription())
                            .scheduledDate(retroCreateRequest.getScheduledDate())
                            .createdBy(user)
                            .team(user.getTeam())
                            .build();

                    Retro savedRetro = retroRepository.save(retro);
                    log.info("Retrospective session created with ID: {}", savedRetro.getId());
                                        return savedRetro;
                }).thenApply(Retro::toDto);
    }
}
