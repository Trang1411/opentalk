package com.example.opentalk.specification;

import com.example.opentalk.dto.EmployeeDTO;
import com.example.opentalk.entity.Employee;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeSpecification {
    public Specification<Employee> filter(final EmployeeDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(criteria.getEnable()) {
                predicates.add(cb.equal(root.get("enable"), true));
            }

            if(StringUtils.hasLength(criteria.getUsername())) {
                predicates.add(cb.like(root.get("username"), criteria.getUsername().trim()));
            }

            if(StringUtils.hasLength(criteria.getName())) {
                predicates.add(cb.like(cb.upper(root.get("name")), "%" + criteria.getName().trim().toUpperCase()+ "%"));
            }

            if(criteria.getRole() != null) {
                predicates.add(cb.equal(root.get("role"), criteria.getRole().getId()));
            }

            if(criteria.getCompanyBranch() != null) {
                predicates.add(cb.equal(root.get("company_branch"), criteria.getCompanyBranch().getId()));
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };

    }
}
