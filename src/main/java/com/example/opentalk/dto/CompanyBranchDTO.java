package com.example.opentalk.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.opentalk.entity.CompanyBranch} entity
 */
@Data
public class CompanyBranchDTO implements Serializable {
    private final Long id;
    private final String name;
    private final PageDTO page;
}