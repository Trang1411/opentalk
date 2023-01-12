package com.example.opentalk.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
public class PageDTO {
    @SuppressWarnings("rawtypes")
    private List content;
    private long totalElements;
    private int number;
    private int numberOfElements;
    private int totalPages;

    private Integer page;
    private Integer size;

    private boolean first;
    private boolean last;

    private String sortBy;
    private String sortDirection;
}
