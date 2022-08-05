package com.example.grungeshop.model.mapper;

import com.example.grungeshop.model.DTO.UserRegisterDTO;
import com.example.grungeshop.model.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userDTOtoUserEntity(UserRegisterDTO registerDTO);
}
