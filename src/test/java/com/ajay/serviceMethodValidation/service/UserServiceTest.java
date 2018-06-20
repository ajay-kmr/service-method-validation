package com.ajay.serviceMethodValidation.service;

import com.ajay.serviceMethodValidation.ServiceMethodValidationApplication;
import com.ajay.serviceMethodValidation.dto.OwnUserDtoInsteadOfThirdPartyUserDTO;
import com.ajay.serviceMethodValidation.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


//@ContextConfiguration(classes = {UserServiceTest.Config.class})
@ContextConfiguration(classes = {ServiceMethodValidationApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Before
    public void setUp() throws Exception {
    }

    /*@Configuration
    public static class Config {
        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            return new MethodValidationPostProcessor();
        }

        @Bean
        public UserService userCreateService() {
            return new UserService();
        }
    }*/

    @Autowired
    private UserService service;


    @Test
    public void throwsViolationExceptionWhenAllArgumentsInvalid() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> service.createUser(null, null, null))
                .matches(e -> e.getConstraintViolations().size() == 3);
    }

    @Test
    public void throwsViolationExceptionWhen2ArgumentsInvalid() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> service.createUser(null, null, "valid"))
                .matches(e -> e.getConstraintViolations().size() == 2);

    }

    @Test
    public void throwsViolationExceptionWhenEmailInvalidArgumentsInvalid() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> service.createUser("invalid_email", "valid", "valid"))
                .matches(e -> e.getConstraintViolations().size() == 1)
                .matches(e -> e.getConstraintViolations().stream()
//                        .allMatch(v -> v.getMessage().equals("not a well-formed email address")));
                        .allMatch(v -> v.getMessage().equals("must be a well-formed email address")));

    }

    @Test
    public void throwsViolationExceptionWhenReturnValueTooLong() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> service.createUser("user@domain.com", "too_long_username", "valid"))
                .matches(e -> e.getConstraintViolations().size() == 1)
                .matches(e -> e.getConstraintViolations().stream()
                        .allMatch(v -> v.getMessage().equals("length must be between 3 and 5")));

    }

    @Test
    public void throwsViolationExceptionWhenUserDtoIsInvalid() {

        UserDTO userDTO = new UserDTO(null, null, "valid");

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> service.createUser(userDTO))
                .matches(e -> e.getConstraintViolations().size() == 2);


    }

    @Test
    public void validateThirdPartyDTO() {

        OwnUserDtoInsteadOfThirdPartyUserDTO userDTO = new OwnUserDtoInsteadOfThirdPartyUserDTO(null, null, "valid");

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> service.validateThirdPartyUserDTO(userDTO))
                .matches(e -> e.getConstraintViolations().size() == 2);


    }

    @Test
    public void createsUser() {
        service.createUser("user@domain.com", "valid", "valid");
    }

}