package com.pismo.assignment.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class InternalServerException extends RuntimeException{

    private final int code;


    public InternalServerException(int code, Throwable t, String message){
        super(message,t);
        this.code=code;
    }

    public InternalServerException(int code, String message){
        super(message);
        this.code=code;
    }
}
