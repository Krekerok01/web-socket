package com.krekerok.repository;


import com.krekerok.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    List<User> findAll();
}
