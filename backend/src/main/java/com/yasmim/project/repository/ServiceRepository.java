package com.yasmim.project.repository;

import com.yasmim.project.entity.ServiceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceData, Long> {
}
