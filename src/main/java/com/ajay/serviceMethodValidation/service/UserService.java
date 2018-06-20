package com.ajay.serviceMethodValidation.service;

import com.ajay.serviceMethodValidation.dto.OwnUserDtoInsteadOfThirdPartyUserDTO;
import com.ajay.serviceMethodValidation.dto.UserDTO;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * In order to indicate that a specific class is supposed to be validated at the method level,
 * it needs to be annotated with @Validated annotation at type level.
 * Methods applicable for validation must have JSR-303 constraint annotations on
 * their parameters and/or on their return values.
 * <p>
 * Beans annotated with @Validated annotation will be detected by MethodValidationPostProcessor
 * and validation functionality is delegated to JSR 303 provider.
 * When the validation fails ConstraintViolationException, with a set of constraint violations,
 * is thrown.
 */
@Service
@Validated
public class UserService {

    @Length(min = 3, max = 5)
    public String createUser(@NotBlank @Email String email,
                             @NotBlank String username,
                             @NotBlank String password) {
        return username;
    }

    /**
     * Note:- the {@param userDTO} will not get validated if you annotate it
     * with {@link org.springframework.validation.annotation.Validated} instead
     * of {@link javax.validation.Valid}
     *
     * @param userDTO
     * @return
     */
    public UserDTO createUser(@Valid UserDTO userDTO) {
//    public UserDTO createUser(@Validated UserDTO userDTO) {
        return userDTO;
    }

    /**
     * Note 1:- When we need to third party DTO or class/DTO generated using some tool eg Apache Thrift/ Apache Avro,
     * then in that case we can create our own DTO class which extends the third party DTO and
     * add the required validation annotation on the
     * getter or setter methods as done for {@link OwnUserDtoInsteadOfThirdPartyUserDTO}
     * <p>
     * Note:- the {@param userDTO} will not get validated if you annotate it
     * with {@link org.springframework.validation.annotation.Validated} instead
     * of {@link javax.validation.Valid}
     *
     * @param userDTO
     * @return
     */
    public OwnUserDtoInsteadOfThirdPartyUserDTO validateThirdPartyUserDTO(@Valid OwnUserDtoInsteadOfThirdPartyUserDTO userDTO) {
//    public UserDTO createUser(@Validated UserDTO userDTO) {
        return userDTO;
    }
}
