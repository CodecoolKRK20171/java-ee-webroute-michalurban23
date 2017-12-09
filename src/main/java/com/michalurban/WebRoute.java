package com.michalurban;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebRoute {

    enum METHOD {
        GET,
        POST
    }

    String path() default "/";
    METHOD method() default METHOD.GET;
}
