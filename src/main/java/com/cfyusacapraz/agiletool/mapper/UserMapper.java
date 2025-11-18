package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.dto.UserDto;
import com.cfyusacapraz.agiletool.mapper.util.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, config = BaseEntityMapperConfig.class,
        unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {RoleMapper.class, TeamMapper.class})
public interface UserMapper {

    UserDto toDto(User user, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDTO, @Context CycleAvoidingMappingContext context);
}
