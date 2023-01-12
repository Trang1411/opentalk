package com.example.opentalk.mapper;

import com.example.opentalk.dto.EmployeeDTO;
import com.example.opentalk.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee dtoToEntity(EmployeeDTO role);
    EmployeeDTO entityToDTO(Employee role);
}

