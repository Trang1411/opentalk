package com.example.opentalk.mapper;

import com.example.opentalk.dto.RoleDTO;
import com.example.opentalk.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role dtoToEntity(RoleDTO role);
    RoleDTO entityToDTO(Role role);
}

