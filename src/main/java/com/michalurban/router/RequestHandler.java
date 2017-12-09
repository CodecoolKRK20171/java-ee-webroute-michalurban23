package com.michalurban.router;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.URLDecoder;

import com.michalurban.router.WebRoute.METHOD;

class RequestHandler {

    private HttpExchange httpExchange;
    private HtmlPage display = new HtmlPage();
    private String response = null;

    @WebRoute(path="/form", method=METHOD.POST)
    void showResult() throws IOException {

        String message = getMessageFromForm();
        String reversed = reverseMessage(message);

        response = display.getResult(reversed);
    }

    @WebRoute(path="/form")
    void showForm() {

        response = display.getForm();
    }

    @WebRoute()
    void showMainPage() {

        response = display.getMainPage();
    }

    void setupExchange(HttpExchange httpExchange) {

        this.httpExchange = httpExchange;
    }

    void redirect() throws IOException {

        httpExchange.getResponseHeaders().set("Location", "/");
        httpExchange.sendResponseHeaders(302, -1);
    }

    void sendResponse() throws IOException {

        if (response == null) {
            redirect();
        }

        String fullResponse = display.getFullResponse(response);

        httpExchange.sendResponseHeaders(200, fullResponse.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(fullResponse.getBytes());
        os.close();
    }

    private String getMessageFromForm() throws IOException {

        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String formData = br.readLine();

        String message = formData.split("=")[1];

        return URLDecoder.decode(message, "UTF-8");
    }

    private String reverseMessage(String message) {

        StringBuilder reversedString = new StringBuilder();

        for (char c : message.toCharArray()) {
            reversedString.insert(0, c);
        }
        return reversedString.toString();
    }

}
