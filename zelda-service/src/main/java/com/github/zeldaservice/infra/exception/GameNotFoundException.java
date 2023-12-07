package com.github.zeldaservice.infra.exception;

public class GameNotFoundException extends Exception{
    public GameNotFoundException(String msg){
        super(msg);
    }
}
