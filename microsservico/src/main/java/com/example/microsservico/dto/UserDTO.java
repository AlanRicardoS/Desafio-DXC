package com.example.microsservico.dto;

import com.example.microsservico.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;

/**
 * @author Alan Ricardo
 */

public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {
    private Long id;
    private String name;
    private String email;

    public static UserDTO create(User user) {
        return new ModelMapper().map(user, UserDTO.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
