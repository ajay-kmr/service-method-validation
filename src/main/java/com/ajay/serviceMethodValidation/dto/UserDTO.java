package com.ajay.serviceMethodValidation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO {

    @NotBlank @Email String email;
    @NotBlank String username;
    @NotBlank String password;

    public UserDTO() {
    }

    public UserDTO(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
