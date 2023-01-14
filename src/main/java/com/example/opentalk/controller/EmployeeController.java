package com.example.opentalk.controller;

import com.example.opentalk.dto.EmployeeDTO;
import com.example.opentalk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value="/api/get")
    @ResponseBody
    public ResponseEntity<?> get(){
        EmployeeDTO data = employeeService.get(new EmployeeDTO());

        if(data == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }

    @PostMapping(value="/api/search")
    @ResponseBody
    public ResponseEntity<?> search(@RequestBody EmployeeDTO criteria){
        EmployeeDTO data = employeeService.get(criteria);

        if(data == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }

    @GetMapping(value="/api/get/{id}")
    @ResponseBody
    public ResponseEntity<?> findByID(@PathVariable(value = "id") String idParameter){
        try {
            EmployeeDTO data = employeeService.findByID(Long.parseLong(idParameter));

            if(data != null)
                ResponseEntity.ok(data);

        } catch (NumberFormatException ignored) {

        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value="/api/save")
    @ResponseBody
    public ResponseEntity<?> save(EmployeeDTO employee){
        if(!employeeService.save(employee))
            return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok("Lưu Thông Tin Thành Công");
    }

    @DeleteMapping(value="/api/delete")
    @ResponseBody
    public ResponseEntity<?> delete(String idParameter){
        try {
            if(employeeService.delete(Long.parseLong(idParameter)))
                ResponseEntity.ok("Xóa Thông Tin Thành Công");
        } catch (NumberFormatException ignored) {

        }

        return ResponseEntity.notFound().build();
    }
}
