package com.personal.redisProject.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //Runtime 에 실행 되게
@Target(ElementType.METHOD)
public @interface LoginCheck {

    public static enum UserType{
        USER,ADMIN
    }

    UserType type();
}
