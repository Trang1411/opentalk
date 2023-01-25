package com.example.opentalk.service.impl;

import com.example.opentalk.dto.RoleDTO;
import com.example.opentalk.entity.Role;
import com.example.opentalk.repository.RoleRepository;
import com.example.opentalk.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<RoleDTO> findAll() {
        List<RoleDTO> roles = new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            roles.add(mapper.map(role, RoleDTO.class));
        }
        return roles;
    }
}
