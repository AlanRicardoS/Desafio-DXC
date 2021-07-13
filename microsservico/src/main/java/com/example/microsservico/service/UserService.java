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
    public UserDTO createUser(UserDTO userDTO){
        UserDTO userDTOReturn = UserDTO.create(userRepository.save(User.create(userDTO)));
        return userDTOReturn;
    }
    public UserDTO findById(Long id){
        var user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum user com id" + id));
        return convertToUserDTO(user);
    }
    public UserDTO updateUser(UserDTO userDTO){
        var optionalUser = findById(userDTO.getId());
        return convertToUserDTO(userRepository.save(User.create(userDTO)));
    }
    public void delete(Long id){
        var userDTO = findById(id);
        userRepository.delete(User.create(userDTO));
    }
}
