package com.yasmim.project.controller;

import com.yasmim.project.dto.RegisterServiceData;
import com.yasmim.project.entity.ServiceData;
import com.yasmim.project.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/service")
    public ResponseEntity<String> registerService(@RequestBody RegisterServiceData obj) {
        return new ResponseEntity<>(
                serviceService.registerService(obj),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/service")
    public ResponseEntity<List<ServiceData>> getService(@RequestParam String query, @RequestParam Integer index, @RequestParam Integer size) {
        return new ResponseEntity<>(
                serviceService.getPaginatedServices(query, index, size),
                HttpStatus.OK);
    }
}
