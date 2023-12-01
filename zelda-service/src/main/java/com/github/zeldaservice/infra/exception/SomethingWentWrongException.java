package com.github.zeldaservice.exception;

public class SomethingWentWrongException extends RuntimeException {
    public SomethingWentWrongException(String msg){
        super(msg);
    }
}
