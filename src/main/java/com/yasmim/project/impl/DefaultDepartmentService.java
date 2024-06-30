package com.yasmim.project.impl;

import com.yasmim.project.entity.DepartmentData;
import com.yasmim.project.repository.DepartmentRepository;
import com.yasmim.project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultDepartmentService implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentData> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentData findDepartment(String name) {
        return departmentRepository.findByName(name);
    }
}
