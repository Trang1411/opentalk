package com.example.opentalk.specification;

import com.example.opentalk.dto.CompanyBranchDTO;
import com.example.opentalk.entity.CompanyBranch;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyBranchSpecification {
    public Specification<CompanyBranch> filter(final CompanyBranchDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("enable"), true));

            if(StringUtils.hasLength(criteria.getName())) {
                predicates.add(cb.like(cb.upper(root.get("name")), "%" + criteria.getName().trim().toUpperCase()+ "%"));
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };

    }
}
