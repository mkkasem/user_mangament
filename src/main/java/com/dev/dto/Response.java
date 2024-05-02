package com.dev.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String message;
    private Object data;
    private Integer code;

    public Response(String message, Object data, Integer code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }
}
