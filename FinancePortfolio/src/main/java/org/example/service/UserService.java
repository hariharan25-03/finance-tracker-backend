package org.example.service;

import org.example.dto.req.SignUpDTO;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService  {

    User createUser(SignUpDTO user);
    User findUserByID(UUID userId);
    User findUserByEmail(String email);
}
