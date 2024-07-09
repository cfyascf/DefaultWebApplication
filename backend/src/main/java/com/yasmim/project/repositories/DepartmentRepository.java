package com.yasmim.project.repositories;

import com.yasmim.project.entities.DepartmentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentData, Long> {
    public DepartmentData findByName(String name);
}
