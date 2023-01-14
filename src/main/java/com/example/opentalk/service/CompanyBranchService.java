package com.example.opentalk.service;

import com.example.opentalk.dto.CompanyBranchDTO;

public interface CompanyBranchService {
    CompanyBranchDTO get(CompanyBranchDTO criteria);

    CompanyBranchDTO findByID(Long id);

    Boolean save(CompanyBranchDTO companyBranch);

    Boolean delete(Long id);
}
