package com.example.opentalk.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.opentalk.entity.Role} entity
 */
@Data
public class RoleDTO implements Serializable {
    private final Long id;
    private final String name;
    private final PageDTO page;
}