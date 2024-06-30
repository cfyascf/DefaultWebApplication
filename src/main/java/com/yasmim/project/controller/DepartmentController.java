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

    @Autowired
    private JWTService jwtService;

    @GetMapping("/department")
    public ResponseEntity<List<DepartmentData>> getAllDepartments(
            @RequestHeader("Authorization") String jwt) {

        if(!jwtService.verifyPermission(jwt, null)) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.FORBIDDEN
            );
        }

        return new ResponseEntity<>(
                departmentService.getAllDepartments(),
                HttpStatus.OK
        );
    }
}
