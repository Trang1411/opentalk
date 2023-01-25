package com.example.opentalk.controller;

import com.example.opentalk.dto. CompanyBranchDTO;
import com.example.opentalk.service. CompanyBranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/companyBranch")
public class CompanyBranchController {

    @Autowired
    private CompanyBranchService companyBranchService;

    @GetMapping(value="/api/get")
    public @ResponseBody ResponseEntity<?> get(){
        CompanyBranchDTO data = companyBranchService.get(new CompanyBranchDTO());

        if(data == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }

    @PostMapping(value="/api/search")
    public @ResponseBody ResponseEntity<?> search(@RequestBody CompanyBranchDTO criteria){
        CompanyBranchDTO data = companyBranchService.get(criteria);
        if(data == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }

    @GetMapping(value="/api/get/{id}")
    public @ResponseBody ResponseEntity<?> findByID(@PathVariable(value = "id") String idParameter){
        try {
            CompanyBranchDTO data = companyBranchService.findByID(Long.parseLong(idParameter));
            if(data != null)
                ResponseEntity.ok(data);

        } catch (NumberFormatException ignored) {

        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value="/api/save")
    public @ResponseBody ResponseEntity<Boolean> save(@RequestBody CompanyBranchDTO companyBranch){
        return ResponseEntity.ok(Boolean.TRUE.equals(companyBranchService.save(companyBranch)));
    }

    @DeleteMapping(value="/api/delete")
    public @ResponseBody ResponseEntity<?> delete(String idParameter){
        try {
            if(Boolean.TRUE.equals(companyBranchService.delete(Long.parseLong(idParameter))))
                ResponseEntity.ok("Xóa Thông Tin Thành Công");
        } catch (NumberFormatException ignored) {

        }

        return ResponseEntity.notFound().build();
    }
}
