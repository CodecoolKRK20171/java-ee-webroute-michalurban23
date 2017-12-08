package com.michalurban;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class RequestRouter implements HttpHandler {

    RequestHandler handler = new RequestHandler();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        handler.setupExchange(httpExchange);

        handler.a();
        handler.sendResponse();
    }

}
