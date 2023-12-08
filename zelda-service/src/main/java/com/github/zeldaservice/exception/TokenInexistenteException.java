package com.github.zeldaservice.exception;

public class TokenInexistenteException /*inexistent não existe, é nonExistent*/ extends RuntimeException{
    public TokenInexistenteException(String msg){
        super(msg);
    }
}
