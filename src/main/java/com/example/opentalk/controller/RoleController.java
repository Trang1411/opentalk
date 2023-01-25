package com.example.opentalk.controller;

import com.example.opentalk.dto.RoleDTO;
import com.example.opentalk.service.RoleService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value="/api/get")
    public @ResponseBody ResponseEntity<?> get(){
        List<RoleDTO> data = roleService.findAll();

        if(data.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }
}
