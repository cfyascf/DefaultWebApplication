package com.yasmim.project.services.impl;

import com.yasmim.project.entities.DepartmentData;
import com.yasmim.project.repositories.DepartmentRepository;
import com.yasmim.project.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultDepartmentService implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentData> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentData findDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }
}
