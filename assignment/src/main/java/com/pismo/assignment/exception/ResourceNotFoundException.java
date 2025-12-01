package com.pismo.assignment.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private int code;
    private String message;

    public ResourceNotFoundException(int code, Throwable t, String message){
        super(message,t);
        this.code=code;
    }

    public ResourceNotFoundException(int code, String message){
        super(message);
        this.code=code;
    }
}
