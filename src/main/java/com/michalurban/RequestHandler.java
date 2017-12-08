package com.michalurban;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import com.michalurban.WebRoute.METHOD;

class RequestHandler {

    HttpExchange httpExchange;
    String response;

    @WebRoute(path="/a", method=METHOD.GET)
    void a() {

        response = "dupa";
    }

    void setupExchange(HttpExchange httpExchange) {

        this.httpExchange = httpExchange;
    }

    void sendResponse() throws IOException {

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
