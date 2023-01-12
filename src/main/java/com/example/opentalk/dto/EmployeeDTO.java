package com.example.opentalk.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.opentalk.entity.Employee} entity
 */
@Data
@Builder
public class EmployeeDTO implements Serializable {
    private final Long id;
    private final String name;
    private final String username;
    private String password;
    private final String gender;
    private final String avatar;
    private final CompanyBranchDTO companyBranch;
    private final RoleDTO role;
    private final Boolean enable;
    private PageDTO page;
}