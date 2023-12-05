package com.github.reviewservice.advice;


import com.github.reviewservice.exception.SomethingWentWrong;
import com.github.reviewservice.exception.TokenVazioOuInvalido;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionControll {

    @ExceptionHandler(SomethingWentWrong.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> algoDeuErrado(SomethingWentWrong ex){
        HashMap<String, String> errorMap = new HashMap<>();

        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler(TokenVazioOuInvalido.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> algoDeuErrado(TokenVazioOuInvalido ex){
        HashMap<String, String> errorMap = new HashMap<>();

        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }




}


