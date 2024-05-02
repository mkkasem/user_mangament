package com.dev.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponse {

    private long id;
    private String username;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private boolean isAdmin;
}

