package com.yasmim.project.repositories;

import com.yasmim.project.entities.VerificationTokenData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenData, Long> {
}
