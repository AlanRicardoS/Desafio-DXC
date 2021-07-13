package com.example.microsservico.service;

import com.example.microsservico.dto.UserDTO;
import com.example.microsservico.model.User;
import com.example.microsservico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Alan Ricardo
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDTO convertToUserDTO(User user) {
        return UserDTO.create(user);
    }
    public Page<UserDTO> findAll(Pageable pageable){
        var page = userRepository.findAll(pageable);
        return page.map(this::convertToUserDTO);
    }
    
}