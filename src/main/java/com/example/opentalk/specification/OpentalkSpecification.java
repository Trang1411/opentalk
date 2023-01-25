package com.example.opentalk.specification;

import com.example.opentalk.dto.OpentalkDTO;
import com.example.opentalk.entity.Opentalk;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Component
public class OpentalkSpecification {
    public Specification<Opentalk> filter(final OpentalkDTO criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(criteria.getStatus() != null) {
                predicates.add(cb.equal(root.get("enable"), criteria.getStatus()));
            }

            if(StringUtils.hasLength(criteria.getTopic())) {
                predicates.add(cb.like(cb.upper(root.get("topic")), "%" + criteria.getTopic().trim().toUpperCase()+ "%"));
            }

            if(criteria.getHost() != null) {
                predicates.add(cb.equal(root.get("host"), criteria.getHost().getId()));
            }

            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
                    .optionalStart()
                    .appendPattern(" HH:mm")
                    .optionalEnd()
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter();

            try {
                if (StringUtils.hasLength(criteria.getFromDate())) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), LocalDateTime.parse(criteria.getFromDate(), formatter)));
                }

                if (StringUtils.hasLength(criteria.getToDate())) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), LocalDateTime.parse(criteria.getToDate(), formatter)));
                }
            } catch (DateTimeParseException exception) {
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };

    }
}
