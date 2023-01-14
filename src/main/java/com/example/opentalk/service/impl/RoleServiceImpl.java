package com.example.opentalk.service.impl;

import com.example.opentalk.dto.RoleDTO;
import com.example.opentalk.entity.Role;
import com.example.opentalk.mapper.RoleMapper;
import com.example.opentalk.repository.RoleRepository;
import com.example.opentalk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> findAll() {
        List<RoleDTO> roles = new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            roles.add(roleMapper.entityToDTO(role));
        }
        return roles;
    }
}
