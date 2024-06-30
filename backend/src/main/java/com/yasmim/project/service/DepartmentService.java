package com.yasmim.project.service;

import com.yasmim.project.entity.DepartmentData;

import java.util.List;

public interface DepartmentService {
    public List<DepartmentData> getAllDepartments();
    public DepartmentData findDepartmentByName(String name);
}
