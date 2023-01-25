package com.example.opentalk.controller;

import com.example.opentalk.dto.OpentalkDTO;
import com.example.opentalk.service.OpentalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/opentalk")
public class OpentalkController {

    @Autowired
    private OpentalkService opentalkService;

    @GetMapping(value="/api/get")
    @ResponseBody
    public ResponseEntity<?> get(){
        OpentalkDTO data = opentalkService.get(new OpentalkDTO());

        if(data == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }

    @PostMapping(value="/api/search")
    @ResponseBody
    public ResponseEntity<?> search(@RequestBody OpentalkDTO criteria){
        OpentalkDTO data = opentalkService.get(criteria);

        if(data == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(data);
    }

    @GetMapping(value="/api/get/{id}")
    @ResponseBody
    public ResponseEntity<?> findByID(@PathVariable(value = "id") String idParameter){
        try {
            OpentalkDTO data = opentalkService.findByID(Long.parseLong(idParameter));

            if(data != null)
                ResponseEntity.ok(data);

        } catch (NumberFormatException ignored) {

        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value="/api/save")
    @ResponseBody
    public ResponseEntity<?> save(OpentalkDTO opentalk){
        if(!opentalkService.save(opentalk))
            return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok("Lưu Thông Tin Thành Công");
    }

    @DeleteMapping(value="/api/delete")
    @ResponseBody
    public ResponseEntity<?> delete(String idParameter){
        try {
            if(opentalkService.delete(Long.parseLong(idParameter)))
                ResponseEntity.ok("Xóa Thông Tin Thành Công");
        } catch (NumberFormatException ignored) {

        }

        return ResponseEntity.notFound().build();
    }
}
