package com.example.opentalk.mapper;

import com.example.opentalk.dto.OpentalkDTO;
import com.example.opentalk.entity.Opentalk;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpentalkMapper {
    Opentalk dtoToEntity(OpentalkDTO role);
    OpentalkDTO entityToDTO(Opentalk role);
}

