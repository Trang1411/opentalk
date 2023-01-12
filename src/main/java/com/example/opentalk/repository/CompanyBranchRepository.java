package com.example.opentalk.repository;

import com.example.opentalk.entity.CompanyBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyBranchRepository extends JpaRepository<CompanyBranch, Long>, JpaSpecificationExecutor<CompanyBranch> {
}