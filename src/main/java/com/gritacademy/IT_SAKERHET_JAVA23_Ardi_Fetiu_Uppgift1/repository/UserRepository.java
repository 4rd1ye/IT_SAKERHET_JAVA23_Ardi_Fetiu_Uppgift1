package com.gritacademy.IT_SAKERHET_JAVA23_Ardi_Fetiu_Uppgift1.repository;

import com.gritacademy.IT_SAKERHET_JAVA23_Ardi_Fetiu_Uppgift1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
