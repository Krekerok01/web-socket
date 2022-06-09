package com.krekerok.service;



import com.krekerok.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(String username);

    void addUserIfNotExists(String username);

    List<String> getAllUsernames();

    User findByUsername(String username);
}
