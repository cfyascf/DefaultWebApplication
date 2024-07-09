package com.yasmim.project.repositories;

import com.yasmim.project.entities.ServiceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceData, Long> {
}
