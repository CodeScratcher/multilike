package com.inot.multilike;

import com.badlogic.gdx.Screen;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;

import java.util.ArrayList;
import java.util.List;

public class ServerScreen implements Screen {
    List<NetSocket> sockets;
    @Override
    public void show() {
        Vertx vertx = Vertx.vertx();

        sockets = new ArrayList<>();
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

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
