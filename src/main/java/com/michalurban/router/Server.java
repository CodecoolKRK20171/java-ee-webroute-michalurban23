package com.michalurban.router;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private HttpServer server;

    public void setup(int port) throws IOException {

        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RequestRouter());
        server.setExecutor(null);
    }

    public void start() {

        server.start();
    }

}
