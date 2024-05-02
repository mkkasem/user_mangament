package com.dev.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateUserRequest {

    private long id;

    @NotEmpty(message = "username.validation.message")
    private String username;


    @Email(message = "email.validation.message")
    @NotEmpty(message = "email.validation.not.empty.message")
    private String email;

    @NotNull(message = "birthDate.validation.message")
    private Date birthDate;

    @Pattern(regexp = "^\\+[1-9]{1}[0-9]{9,14}$", message = "phoneNumber.validation.message")
    private String phoneNumber;

    private boolean isAdmin;
}
