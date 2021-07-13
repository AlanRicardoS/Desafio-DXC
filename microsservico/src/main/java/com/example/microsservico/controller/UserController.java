package com.example.microsservico.controller;

import com.example.microsservico.dto.UserDTO;
import com.example.microsservico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
/**
 * @author Alan Ricardo
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public UserDTO findById(@PathVariable("id") Long id){
        UserDTO userDTO = userService.findById(id);
        userDTO.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        return userDTO;
    }
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection =
                "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
        Page<UserDTO> users = userService.findAll(pageable);
        users.stream().forEach(user ->{
            user.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
        });
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public UserDTO create(@PathVariable UserDTO userDTO){
        UserDTO userDTOReturn = userService.createUser(userDTO);
        userDTOReturn.add(linkTo(methodOn(UserController.class).findById(userDTOReturn.getId())).withSelfRel());
        return userDTOReturn;
    }
    @PutMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public UserDTO update(@RequestBody UserDTO userDTO){
        UserDTO userDTOReturno = userService.updateUser(userDTO);
        userDTOReturno.add(linkTo(methodOn(UserController.class).findById(userDTO.getId())).withSelfRel());
        return userDTOReturno;
    }

}
