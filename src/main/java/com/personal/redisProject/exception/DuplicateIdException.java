package com.personal.redisProject.exception;

public class DuplicateIdException extends RuntimeException { //Runtime 시 exception 처리

    public DuplicateIdException(String msg){
        super(msg); // Runtime 시 상위 객체에게 던지겠다
    }
}
