package com.assignment.task.service;

import com.assignment.task.dto.UserDTO;
import com.assignment.task.model.User;
import com.assignment.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public Optional<UserDTO> getUserById(String id) {
        return Optional.ofNullable(modelMapper.map(userRepository.findById(id), UserDTO.class));
    }
}
