package com.michalurban;

public @interface WebRoute {

    enum METHOD {
        GET,
        POST
    }

    String path() default "/";
    METHOD method() default METHOD.GET;
}
