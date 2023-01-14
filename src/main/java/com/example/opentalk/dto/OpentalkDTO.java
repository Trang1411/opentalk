package com.example.opentalk.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.example.opentalk.entity.Opentalk} entity
 */
@Data
public class OpentalkDTO implements Serializable {
    private Long id;
    private String topic;
    private LocalDateTime time;
    private String meeting;
    private Integer status;
    private EmployeeDTO host;

    private String fromDate;
    private String toDate;

    private PageDTO page;
}