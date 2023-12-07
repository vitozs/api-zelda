package com.github.zeldaservice.advice;

import com.github.zeldaservice.exception.TokenInexistenteException;
import com.github.zeldaservice.infra.exception.GameNotFoundException;
import com.github.zeldaservice.infra.exception.SomethingWentWrongException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandlerControll {


    @ExceptionHandler(Exception.class)
    public ProblemDetail exceptionHandler(Exception ex){
        ProblemDetail errorDetail = null;

        if(ex instanceof AccessDeniedException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("errorMessage", "Token Invaldo! Logue na conta e use o token");
        }

        return errorDetail;
    }


}
