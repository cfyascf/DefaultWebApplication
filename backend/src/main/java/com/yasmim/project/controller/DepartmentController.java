package com.yasmim.project.controller;

import com.yasmim.project.entity.DepartmentData;
import com.yasmim.project.service.DepartmentService;
import com.yasmim.project.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentData>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}
