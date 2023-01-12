package com.example.opentalk.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.example.opentalk.entity.Opentalk} entity
 */
@Data
public class OpentalkDTO implements Serializable {
    private final Long id;
    private final String topic;
    private final LocalDateTime time;
    private final String meeting;
    private final Integer status;
    private final EmployeeDTO host;

    private final String fromDate;
    private final String toDate;

    private PageDTO page;
}