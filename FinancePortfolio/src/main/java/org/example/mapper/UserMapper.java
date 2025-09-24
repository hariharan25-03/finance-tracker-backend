package org.example.mapper;

import org.example.dto.req.SignUpDTO;
import org.example.dto.req.UserResponseDTO;
import org.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "portfolios", ignore = true)
    User toEntity(SignUpDTO dto);

    UserResponseDTO toDto(User user);
}
