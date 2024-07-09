package com.yasmim.project.controllers;

import com.yasmim.project.dto.RegisterServiceData;
import com.yasmim.project.entities.ServiceData;
import com.yasmim.project.services.JWTService;
import com.yasmim.project.services.ServiceService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    JWTService jwtService;

    @PostMapping("/service")
    public ResponseEntity<ServiceData> registerService(
            @Valid @RequestBody RegisterServiceData obj) {

        jwtService.verifyPermission(1);

        return ResponseEntity.ok(serviceService.registerService(obj));
    }

    @GetMapping("/service")
    public ResponseEntity<List<ServiceData>> getService(
            @RequestParam String query,
            @RequestParam Integer index,
            @RequestParam Integer size) {

        return new ResponseEntity<>(
                serviceService.getPaginatedServices(query, index, size),
                HttpStatus.OK);
    }
}
