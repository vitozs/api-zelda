package com.github.zeldaservice.advice;

import com.github.zeldaservice.exception.GameNotFoundException;
import com.github.zeldaservice.exception.SomethingWentWrongException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerControll {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GameNotFoundException.class)
    public Map<String, String> gameNotFound(GameNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SomethingWentWrongException.class)
    public Map<String, String> somethingWentWrong(SomethingWentWrongException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }



}
