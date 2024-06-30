package com.yasmim.project.repository;

import com.yasmim.project.entity.DepartmentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentData, Long> {
    public DepartmentData findByName(String name);
}
