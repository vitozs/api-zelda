package com.github.zeldaservice.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ResourceExceptionHandler {
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
