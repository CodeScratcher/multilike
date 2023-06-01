package com.inot.multilike;

import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.Vertx;

public class ServerLauncher {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        NetServerOptions options = new NetServerOptions().setPort(4321);
        NetServer server = vertx.createNetServer(options);

        server
            .listen()
            .onComplete(res -> {
                if (res.succeeded()) {
                    System.out.println("Server is now listening!");
                }
                else {
                    System.out.println("Failed to bind!");
                }
                }
            );


    }
}