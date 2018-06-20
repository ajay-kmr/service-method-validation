package com.ajay.serviceMethodValidation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Note 1:- When we need to validate third party DTO or class/DTO generated using some tool eg Apache Thrift/ Apache Avro,
 * then in that case we can create our own DTO class which extends the third party DTO and
 * add the required validation annotation on the
 * getter or setter methods as done for {@link OwnUserDtoInsteadOfThirdPartyUserDTO}
 */
public class OwnUserDtoInsteadOfThirdPartyUserDTO extends ThirdPartyUserDTO {

    public OwnUserDtoInsteadOfThirdPartyUserDTO(String email, String username, String password) {
        super(email, username, password);
    }

    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
