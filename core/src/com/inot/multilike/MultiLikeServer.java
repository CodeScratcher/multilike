package com.inot.multilike;

import com.badlogic.gdx.Game;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;

import java.util.ArrayList;
import java.util.List;

public class MultiLikeServer extends Game {
    @Override
    public void create() {
        Vertx vertx = Vertx.vertx();

        List<NetSocket> sockets = new ArrayList<>();
        NetServerOptions options = new NetServerOptions().setPort(4321);
        NetServer server = vertx.createNetServer(options);

        server.connectHandler(socket -> {
            sockets.add(socket);
            socket.handler(buffer -> {

                for (NetSocket sock : sockets) {
                    System.out.println(buffer);
                    sock.write(buffer);
                }
            });
        });

        server
            .listen()
            .onComplete(res -> {
                if (res.succeeded()) {
                    System.out.println("Server is now listening!");
                } else {
                    System.out.println("Failed to bind!");
                }
            });
    }
}
