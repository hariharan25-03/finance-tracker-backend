package org.example.service.impl;

import org.example.dto.req.SignUpDTO;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(SignUpDTO userRequestDTO) {
        if(userRepository.findByEmail(userRequestDTO.getEmail()).isPresent())
        {
            throw new DuplicateKeyException("Provided Email is already Exists");
        }
        var user =  new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public User findUserByID(UUID userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
