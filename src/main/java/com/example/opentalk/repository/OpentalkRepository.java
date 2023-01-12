package com.example.opentalk.repository;

import com.example.opentalk.entity.Opentalk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OpentalkRepository extends JpaRepository<Opentalk, Long>, JpaSpecificationExecutor<Opentalk> {
}