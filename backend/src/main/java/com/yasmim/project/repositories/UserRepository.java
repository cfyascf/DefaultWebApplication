package com.yasmim.project.repositories;

import com.yasmim.project.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    public UserData findByUsername(String username);
    public UserData findByFullname(String fullname);
}
