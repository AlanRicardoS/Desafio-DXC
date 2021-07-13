package com.example.microsservico.controller;

import com.example.microsservico.dto.UserDTO;
import com.example.microsservico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    

}
