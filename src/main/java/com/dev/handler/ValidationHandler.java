package com.dev.handler;

import com.dev.controller.UserController;
import com.dev.util.GenerateResponse;
import com.dev.util.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = UserController.class)
public class ValidationHandler {

    @Resource
    private Utils utils;
    private static final Logger LOGGER = LogManager.getLogger(ValidationHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();

            String errorMessage = utils.getMessageLocalized(error.getDefaultMessage());
            errors.put(fieldName, errorMessage);
        });
        LOGGER.info(errors);
        return GenerateResponse.badRequest("Invalid entered data", errors);
    }
}
