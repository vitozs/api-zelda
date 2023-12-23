package com.github.zeldaservice.exception;

public class TokenNonExistentException extends RuntimeException{
    public TokenNonExistentException(String msg){
        super(msg);
    }
}
