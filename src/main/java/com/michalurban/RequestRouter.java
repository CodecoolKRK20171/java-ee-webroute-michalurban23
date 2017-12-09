package com.michalurban;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.michalurban.WebRoute.METHOD;

public class RequestRouter implements HttpHandler {

    private RequestHandler handler = new RequestHandler();
    private String requestPath;
    private String requestMethod;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        boolean routeFound = false;
        this.requestPath = httpExchange.getRequestURI().toString();
        this.requestMethod = httpExchange.getRequestMethod();

        handler.setupExchange(httpExchange);

        try {
            routeFound = routeRequest();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        if (routeFound) {
            handler.sendResponse();
        } else {
            handler.redirect();
        }
    }

    private boolean routeRequest() throws ReflectiveOperationException{

        Class requestHandler = RequestHandler.class;
        Method[] methods = requestHandler.getDeclaredMethods();
        List<Method> routes = getAnnotatedMethods(methods);

        for (Method route : routes) {
            if (checkIfRequestedMethod(route)) {
                route.invoke(handler);
                return true;
            }
        }
        return false;
    }

    private List<Method> getAnnotatedMethods(Method[] methods) {

        ArrayList<Method> routes = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(WebRoute.class))
                routes.add(method);
        }
        return routes;
    }

    private boolean checkIfRequestedMethod(Method method) {

        String webRoutePath = method.getAnnotation(WebRoute.class).path();
        METHOD webRouteMethod = method.getAnnotation(WebRoute.class).method();

        boolean pathMatches = requestPath.equals(webRoutePath);
        boolean methodMatches = requestMethod.equals(webRouteMethod.toString());

        return pathMatches && methodMatches;
    }

}
