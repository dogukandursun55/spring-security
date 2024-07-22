package com.example.authservice.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.authservice.authservice.domain.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    boolean existsByUsername(String username);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    boolean existsByEmailAddress(String emailAddress);
    boolean existsByPhoneNumber(String phoneNumber);
    
}
