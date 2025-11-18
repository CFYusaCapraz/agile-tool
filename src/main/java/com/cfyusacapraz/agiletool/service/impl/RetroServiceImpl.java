package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.api.request.RetroCreateRequest;
import com.cfyusacapraz.agiletool.api.request.base.BasePagedApiRequest;
import com.cfyusacapraz.agiletool.api.response.base.PageData;
import com.cfyusacapraz.agiletool.domain.Retro;
import com.cfyusacapraz.agiletool.domain.Retro_;
import com.cfyusacapraz.agiletool.domain.Team_;
import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.domain.enums.Roles;
import com.cfyusacapraz.agiletool.dto.RetroDto;
import com.cfyusacapraz.agiletool.dto.UserDto;
import com.cfyusacapraz.agiletool.repository.RetroRepository;
import com.cfyusacapraz.agiletool.service.AuthenticationService;
import com.cfyusacapraz.agiletool.service.RetroService;
import com.cfyusacapraz.agiletool.util.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetroServiceImpl implements RetroService {

    private final RetroRepository retroRepository;

    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    public RetroDto create(@NotNull RetroCreateRequest retroCreateRequest) {
        log.info("Creating retrospective session with title: {}", retroCreateRequest.getTitle());

        UserDto currentUser = authenticationService.getCurrentUser();
        User user = new User().fromDto(currentUser);
        Retro retro =
                Retro.builder().title(retroCreateRequest.getTitle()).description(retroCreateRequest.getDescription())
                        .scheduledDate(retroCreateRequest.getScheduledDate()).createdBy(user).team(user.getTeam())
                        .build();

        Retro savedRetro = retroRepository.save(retro);
        log.info("Retrospective session created with ID: {}", savedRetro.getId());
        return savedRetro.toDto();

    }

    @Override
    @Transactional(readOnly = true)
    public Pair<List<RetroDto>, PageData> getAll(@NotNull BasePagedApiRequest basePagedApiRequest) {
        Specification<Retro> specification = buildTeamScopedRetroSpecification();

        Page<Retro> retroPage =
                PaginationService.getPagedAndFilteredData(retroRepository, basePagedApiRequest.toPaginationData(),
                        specification);
        List<RetroDto> retroDtoList = retroPage.map(Retro::toDto).getContent();
        PageData pageData =
                new PageData(retroPage.getNumber(), retroPage.getTotalElements(), retroPage.getTotalPages());
        return Pair.of(retroDtoList, pageData);

    }

    private @NotNull Specification<Retro> buildTeamScopedRetroSpecification() {
        UserDto currentUser = authenticationService.getCurrentUser();
        Object teamId;
        if (currentUser != null && currentUser.getTeam() != null) {
            teamId = currentUser.getTeam().getId();
        } else {
            teamId = null;
        }

        return (root, query, cb) -> {
            if (teamId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get(Retro_.team).get(Team_.id), teamId);
        };
    }

    @Override
    @Transactional(readOnly = true)
    public RetroDto getById(@NotNull UUID id) {
        UserDto currentUser = authenticationService.getCurrentUser();
        Retro retro = retroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Retrospective session not found with ID: " + id));
        assert currentUser != null;

        boolean isAdmin = currentUser.getRole().getName().equalsIgnoreCase(Roles.ROLE_ADMIN.name());
        boolean belongsToRetroTeam =
                currentUser.getTeam() != null && retro.getTeam() != null && currentUser.getTeam().getId() != null &&
                        currentUser.getTeam().getId().equals(retro.getTeam().getId());

        if (!isAdmin || !belongsToRetroTeam) {
            throw new SecurityException("User is not authorized to access this retrospective");
        }

        return retro.toDto();
    }
}
