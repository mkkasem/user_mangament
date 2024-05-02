package com.dev.util;

import com.dev.dto.Response;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenerateResponse {


    private GenerateResponse() {
        // private constructor to forbids creating object from this class
    }

    public static ResponseEntity<?> success(String message, Object data) throws JsonProcessingException {
        return new ResponseEntity<>(new ObjectMapper().setSerializationInclusion(Include.ALWAYS).writeValueAsString(new Response(message, data, 200)), HttpStatus.OK);
    }

    public static ResponseEntity<?> created(String message, Object data) throws JsonProcessingException {
        return new ResponseEntity<>(new ObjectMapper().setSerializationInclusion(Include.ALWAYS).writeValueAsString(new Response(message, data, 201)), HttpStatus.CREATED);
    }

    public static ResponseEntity<?> error(String message, Object data) throws JsonProcessingException {
        return new ResponseEntity<>(new ObjectMapper().setSerializationInclusion(Include.ALWAYS).writeValueAsString(new Response(message, data, 500)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<?> badRequest(String message, Object data) throws JsonProcessingException {
        return new ResponseEntity<>(new ObjectMapper().setSerializationInclusion(Include.ALWAYS).writeValueAsString(new Response(message, data, 400)), HttpStatus.BAD_REQUEST);
    }

}

