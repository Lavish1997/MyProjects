package com.ecomsec.repository;

import com.ecomsec.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameOrEmail(String usernameOrEmail, String orEmail);
    Optional<User> findByUsername(String username);

}
