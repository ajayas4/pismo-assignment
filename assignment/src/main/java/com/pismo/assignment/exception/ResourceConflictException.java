package com.pismo.assignment.exception;

import lombok.Getter;

@Getter
public class ResourceConflictException extends RuntimeException {

    private int code;
    private String message;

    public ResourceConflictException(int code, Throwable t, String message){
        super(message,t);
        this.code=code;
    }

    public ResourceConflictException(int code, String message){
        super(message);
        this.code=code;
    }
}
