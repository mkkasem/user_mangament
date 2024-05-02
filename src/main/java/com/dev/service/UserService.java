package com.dev.service;

import com.dev.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsersExceptCurrentOne(String username);
}
