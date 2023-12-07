package com.github.reviewservice.exception;



public class SomethingWentWrong extends RuntimeException {
    public SomethingWentWrong(String msg){
        super(msg);
    }
}
