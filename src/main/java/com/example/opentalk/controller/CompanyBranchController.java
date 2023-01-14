package com.example.opentalk.controller;

import com.example.opentalk.dto. CompanyBranchDTO;
import com.example.opentalk.service. CompanyBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companyBranch")
public class CompanyBranchController {

    @Autowired
    private CompanyBranchService companyBranchService;

    @GetMapping(value="/api/get")
    @ResponseBody
    public ResponseEntity<?> get(){
        CompanyBranchDTO data = companyBranchService.get(new CompanyBranchDTO());

        if(data == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }

    @PostMapping(value="/api/search")
    @ResponseBody
    public ResponseEntity<?> search(@RequestBody CompanyBranchDTO criteria){
        CompanyBranchDTO data = companyBranchService.get(criteria);
        if(data == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }

    @GetMapping(value="/api/get/{id}")
    @ResponseBody
    public ResponseEntity<?> findByID(@PathVariable(value = "id") String idParameter){
        try {
            CompanyBranchDTO data = companyBranchService.findByID(Long.parseLong(idParameter));
            if(data != null)
                ResponseEntity.ok(data);

        } catch (NumberFormatException ignored) {

        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value="/api/save")
    @ResponseBody
    public ResponseEntity<?> save(CompanyBranchDTO companyBranch){
        if(!companyBranchService.save(companyBranch))
            return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok("Lưu Thông Tin Thành Công");
    }

    @DeleteMapping(value="/api/delete")
    @ResponseBody
    public ResponseEntity<?> delete(String idParameter){
        try {
            if(companyBranchService.delete(Long.parseLong(idParameter)))
                ResponseEntity.ok("Xóa Thông Tin Thành Công");
        } catch (NumberFormatException ignored) {

        }

        return ResponseEntity.notFound().build();
    }
}
