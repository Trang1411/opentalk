package com.example.opentalk.mapper;

import com.example.opentalk.dto.CompanyBranchDTO;
import com.example.opentalk.entity.CompanyBranch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyBranchMapper {
    CompanyBranch dtoToEntity(CompanyBranchDTO role);
    CompanyBranchDTO entityToDTO(CompanyBranch role);
}

