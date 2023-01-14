package com.example.opentalk.service.impl;

import com.example.opentalk.constant.DefaultPageEnum;
import com.example.opentalk.dto.CompanyBranchDTO;
import com.example.opentalk.dto.PageDTO;
import com.example.opentalk.entity.CompanyBranch;
import com.example.opentalk.mapper.CompanyBranchMapper;
import com.example.opentalk.repository.CompanyBranchRepository;
import com.example.opentalk.service.CompanyBranchService;
import com.example.opentalk.specification.CompanyBranchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class CompanyBranchServiceImpl implements CompanyBranchService {

    @Autowired
    private CompanyBranchRepository companyBranchRepository;

    @Autowired
    private CompanyBranchMapper companyBranchMapper;

    @Autowired
    private CompanyBranchSpecification companyBranchSpecification;

    @Override
    public CompanyBranchDTO get(CompanyBranchDTO criteria) {
        Integer selectedPage = 0;
        Integer maxResults = 0;

        try {
            maxResults = criteria.getPage().getSize();
            selectedPage = criteria.getPage().getPage();
        } catch (NumberFormatException | NullPointerException exception) {
            selectedPage = DefaultPageEnum.PAGE.val;
            maxResults = DefaultPageEnum.SIZE.val;
        }

        Page<CompanyBranchDTO> companyBranchPage = companyBranchRepository.findAll(companyBranchSpecification.filter(criteria), PageRequest.of(selectedPage, maxResults)).map(item -> companyBranchMapper.entityToDTO(item));

        criteria.setPage(PageDTO.builder().content(companyBranchPage.getContent()).number(companyBranchPage.getNumber())
                .numberOfElements(companyBranchPage.getNumberOfElements()).page(companyBranchPage.getNumber()).size(companyBranchPage.getSize())
                .totalPages(companyBranchPage.getTotalPages()).totalElements(companyBranchPage.getTotalElements())
                .sortBy(criteria.getPage().getSortBy()).sortDirection(criteria.getPage().getSortDirection()).build());

        return criteria;
    }

    @Override
    public CompanyBranchDTO findByID(Long id) {
        CompanyBranch result = companyBranchRepository.findById(id).orElse(null);

        if(result == null){
            return null;
        }

        return companyBranchMapper.entityToDTO(result);
    }

    @Override
    public Boolean save(CompanyBranchDTO companyBranch) {
        CompanyBranch companyBranchEntity;

        companyBranchEntity = companyBranchMapper.dtoToEntity(companyBranch);

        companyBranchEntity.setEnable(true);

        return companyBranchRepository.save(companyBranchEntity).getId() != null;
    }

    @Override
    public Boolean delete(Long id) {
        if(findByID(id) == null){
            throw new NumberFormatException();
        }

        companyBranchRepository.deleteById(id);

        return !companyBranchRepository.existsById(id);
    }


}
