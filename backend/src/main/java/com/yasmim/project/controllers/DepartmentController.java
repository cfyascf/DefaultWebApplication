package com.yasmim.project.controllers;

import com.yasmim.project.entities.DepartmentData;
import com.yasmim.project.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
