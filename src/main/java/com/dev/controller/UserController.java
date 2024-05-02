package com.dev.controller;

import com.dev.dto.UpdateUserRequest;
import com.dev.dto.UserRequest;
import com.dev.facade.UserFacade;
import com.dev.util.GenerateResponse;
import com.dev.util.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserFacade userFacade;

    @Resource
    private Utils utils;

    @PostMapping("/")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserRequest userRequest) throws JsonProcessingException {
        try {
            return userFacade.createNewUser(userRequest);
        } catch (Exception e) {
            LOG.error("createNewUser: ", e);
            return utils.generateInternalServerErrorMessage();
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateNewUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) throws JsonProcessingException {
        try {
            return userFacade.updateUser(updateUserRequest);
        } catch (Exception e) {
            LOG.error("updateNewUser: ", e);
            return utils.generateInternalServerErrorMessage();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") long id) throws JsonProcessingException {
        try {
            return userFacade.deleteUserById(id);
        } catch (Exception e) {
            LOG.error("deleteUser: ", e);
            return utils.generateInternalServerErrorMessage();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") long id) throws JsonProcessingException {
        try {
            return userFacade.getUserById(id);
        } catch (Exception e) {
            LOG.error("getUserById: ", e);
            return utils.generateInternalServerErrorMessage();
        }

    }


    @GetMapping("/exists/{username}")
    public ResponseEntity<?> isUserNameAlreadyTaken(@PathVariable(name = "username") String username) throws JsonProcessingException {
        try {
            var isUserNameAlreadyTaken = userFacade.isUsernameAlreadyTaken(username);
            if (isUserNameAlreadyTaken) {
                String responseMessage = utils.getMessageLocalized("username.validation.already.taken");
                return GenerateResponse.success(responseMessage, isUserNameAlreadyTaken);
            } else
                return GenerateResponse.success(null, isUserNameAlreadyTaken);
        } catch (Exception e) {
            LOG.error("isUserNameAlreadyTaken: ", e);
            return utils.generateInternalServerErrorMessage();
        }
    }

}
