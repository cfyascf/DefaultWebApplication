package com.yasmim.project.services;

import com.yasmim.project.entities.DepartmentData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    public List<DepartmentData> getAllDepartments();
    public DepartmentData findDepartmentByName(String name);
}
