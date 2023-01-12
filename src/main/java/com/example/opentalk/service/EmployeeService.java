package com.example.opentalk.service;

import com.example.opentalk.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO get(EmployeeDTO criteria);

    EmployeeDTO findByID(Long id);

    Boolean save(EmployeeDTO employee);

    Boolean delete(Long id);
}
