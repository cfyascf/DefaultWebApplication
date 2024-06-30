package com.yasmim.project.repository;

import com.yasmim.project.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    public UserData findByUsername(String username);
}
