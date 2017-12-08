package com.michalurban;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

class Server {

    private HttpServer server;

    void setup(int port) throws IOException {

        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RequestRouter());
        server.setExecutor(null);
    }

    void start() {

        server.start();
    }

}
