package com.example.opentalk.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.opentalk.entity.Employee} entity
 */
@Data
public class EmployeeDTO implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String gender;
    private String avatar;
    private CompanyBranchDTO companyBranch;
    private RoleDTO role;
    private Boolean enable;
    private PageDTO page;

}