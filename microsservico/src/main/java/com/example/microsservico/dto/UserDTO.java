package com.example.microsservico.dto;

import com.example.microsservico.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Alan Ricardo
 */
@JsonPropertyOrder({"id", "name", "email", "dataNascimento"})
public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("dataNascimento")
    private Date dataNascimento;

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(final Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
