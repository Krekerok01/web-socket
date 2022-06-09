package com.krekerok.service;


import com.krekerok.entity.User;
import com.krekerok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(String username) {
        User user = User.builder()
                .username(username)
                .build();
        return userRepository.save(user);
    }

    @Override
    public void addUserIfNotExists(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) {
            saveUser(username);
        }
    }

    @Override
    public List<String> getAllUsernames() {
        return userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (!optionalUser.isPresent()){
            return saveUser(username);
        } else {
            return optionalUser.get();
        }
    }

}
