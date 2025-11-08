package com.cfyusacapraz.agiletool.mapper;

import com.cfyusacapraz.agiletool.domain.User;
import com.cfyusacapraz.agiletool.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseEntityMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {TeamMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDTO(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDTO);
}
