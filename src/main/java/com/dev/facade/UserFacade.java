package com.dev.facade;

import com.dev.dto.UpdateUserRequest;
import com.dev.dto.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface UserFacade {


    ResponseEntity<?> createNewUser(UserRequest userRequest) throws JsonProcessingException;

    boolean isUsernameAlreadyTaken(String username);

    ResponseEntity<?> deleteUserById(long id) throws JsonProcessingException;

    ResponseEntity<?> getUserById(long id) throws JsonProcessingException;

    ResponseEntity<?> updateUser(UpdateUserRequest updateUserRequest) throws JsonProcessingException;
}
