package com.example.opentalk.service.impl;

import com.example.opentalk.constant.DefaultPageEnum;
import com.example.opentalk.dto.OpentalkDTO;
import com.example.opentalk.dto.PageDTO;
import com.example.opentalk.entity.Opentalk;
import com.example.opentalk.repository.OpentalkRepository;
import com.example.opentalk.service.OpentalkService;
import com.example.opentalk.specification.OpentalkSpecification;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpentalkServiceImpl implements OpentalkService {

    @Autowired
    private OpentalkRepository opentalkRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OpentalkSpecification opentalkSpecification;

    @Override
    public OpentalkDTO get(OpentalkDTO criteria) {
        Integer selectedPage = 0;
        Integer maxResults = 0;

        try {
            maxResults = criteria.getPage().getSize();
            selectedPage = criteria.getPage().getPage();
        } catch (NumberFormatException | NullPointerException exception) {
            selectedPage = DefaultPageEnum.PAGE.val;
            maxResults = DefaultPageEnum.SIZE.val;
        }

        Page<OpentalkDTO> opentalkPage = opentalkRepository.findAll(opentalkSpecification.filter(criteria), PageRequest.of(selectedPage, maxResults)).map(item -> mapper.map(item, OpentalkDTO.class));

        criteria.setPage(PageDTO.builder().content(opentalkPage.getContent()).number(opentalkPage.getNumber())
                .numberOfElements(opentalkPage.getNumberOfElements()).page(opentalkPage.getNumber()).size(opentalkPage.getSize())
                .totalPages(opentalkPage.getTotalPages()).totalElements(opentalkPage.getTotalElements())
                .sortBy(criteria.getPage().getSortBy()).sortDirection(criteria.getPage().getSortDirection()).build());

        return criteria;
    }

    @Override
    public OpentalkDTO findByID(Long id) {
        Opentalk result = opentalkRepository.findById(id).orElse(null);
        if(result == null){
            return null;
        }

        return mapper.map(result, OpentalkDTO.class);
    }

    @Override
    public Boolean save(OpentalkDTO opentalk) {
        Opentalk opentalkEntity;

        opentalkEntity = mapper.map(opentalk, Opentalk.class);

        if(opentalk.getId() == null)
            opentalkEntity.setStatus(1);

        return opentalkRepository.save(opentalkEntity).getId() != null;
    }

    @Override
    public Boolean delete(Long id) {
        if(findByID(id) == null){
            throw new NumberFormatException();
        }

        opentalkRepository.deleteById(id);

        return !opentalkRepository.existsById(id);
    }


}
