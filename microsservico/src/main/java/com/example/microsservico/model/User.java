package com.example.microsservico.model;

import com.example.microsservico.dto.UserDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alan Ricardo
 */
@Entity
@Table(name = "user_tbl")
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    public static User create(UserDTO userDTO){
        return new ModelMapper().map(userDTO, User.class);
    }

    public User() {
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
