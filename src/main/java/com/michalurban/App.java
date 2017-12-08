package com.michalurban;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        int port = 8000;

        server.setup(port);
        server.start();
    }

}
