package com.github.reviewservice.exception;

public class TokenVazioOuInvalido extends RuntimeException{
    public TokenVazioOuInvalido(String msg){
        super(msg);
    }
}
