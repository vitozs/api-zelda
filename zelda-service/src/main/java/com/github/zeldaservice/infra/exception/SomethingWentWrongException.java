package com.github.zeldaservice.infra.exception;

public class SomethingWentWrongException extends RuntimeException {
    public SomethingWentWrongException(String msg){
        super(msg);
    }
}
