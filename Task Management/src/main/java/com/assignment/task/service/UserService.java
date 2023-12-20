package com.assignment.task.service;

import com.assignment.task.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Optional<UserDTO> getUserById(String id);
}
