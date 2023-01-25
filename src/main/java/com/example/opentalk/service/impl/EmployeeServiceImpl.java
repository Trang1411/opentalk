package com.example.opentalk.service.impl;

import com.example.opentalk.constant.DefaultPageEnum;
import com.example.opentalk.dto.EmployeeDTO;
import com.example.opentalk.dto.PageDTO;
import com.example.opentalk.entity.Employee;
import com.example.opentalk.repository.EmployeeRepository;
import com.example.opentalk.service.EmployeeService;
import com.example.opentalk.specification.EmployeeSpecification;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EmployeeSpecification employeeSpecification;

    @Override
    public EmployeeDTO get(EmployeeDTO criteria) {
        log.info(String.valueOf(criteria));
        Integer selectedPage = 0;
        Integer maxResults = 0;

        try {
            maxResults = criteria.getPage().getSize();
            selectedPage = criteria.getPage().getPage();
        } catch (NumberFormatException | NullPointerException exception) {
            selectedPage = DefaultPageEnum.PAGE.val;
            maxResults = DefaultPageEnum.SIZE.val;
        }

        Page<EmployeeDTO> employeePage = employeeRepository.findAll(employeeSpecification.filter(criteria), PageRequest.of(selectedPage, maxResults)).map(item -> mapper.map(item, EmployeeDTO.class));

        for (EmployeeDTO employee : employeePage.getContent()) {
            employee.setPassword(null);
        }

        log.info(employeePage.toString());

        criteria.setPage(PageDTO.builder().content(employeePage.getContent()).number(employeePage.getNumber())
                .numberOfElements(employeePage.getNumberOfElements()).page(employeePage.getNumber()).size(employeePage.getSize())
                .totalPages(employeePage.getTotalPages()).totalElements(employeePage.getTotalElements())
                .sortBy(criteria.getPage().getSortBy()).sortDirection(criteria.getPage().getSortDirection()).build());

        return criteria;
    }

    @Override
    public EmployeeDTO findByID(Long id) {
        Employee result = employeeRepository.findById(id).orElse(null);
        if(result == null){
            return null;
        }

        result.setPassword(null);

        return mapper.map(result, EmployeeDTO.class);
    }

    @Override
    public Boolean save(EmployeeDTO employee) {
        Employee employeeEntity;

        employeeEntity = mapper.map(employee, Employee.class);

        if(employee.getPassword().trim().length() == 0){
            try {
                employeeEntity.setPassword(employeeRepository.findByIdAndEnableTrue(employee.getId()).orElse(null).getPassword());
            } catch (NullPointerException exception){
                return false;
            }
        }

        employeeEntity.setEnable(true);

        return employeeRepository.save(employeeEntity).getId() != null;
    }

    @Override
    public Boolean delete(Long id) {
        if(findByID(id) == null){
            throw new NumberFormatException();
        }

        employeeRepository.deleteById(id);

        return !employeeRepository.existsById(id);
    }


}
