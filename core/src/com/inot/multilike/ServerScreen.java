package com.inot.multilike;

import com.badlogic.gdx.Screen;
import com.inot.multilike.entity.Player;
import com.inot.multilike.model.GameState;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class ServerScreen implements Screen {
    Map<UUID, NetSocket> sockets;
    GameState state;
    @Override
    public void show() {
        Vertx vertx = Vertx.vertx();

        sockets = new HashMap<>();
        NetServerOptions options = new NetServerOptions().setPort(4321);
        NetServer server = vertx.createNetServer(options);

        ObjectMapper mapper = new ObjectMapper();

        server.connectHandler(socket -> {
            UUID uuid = state.addEntity(new Player());
            sockets.put(uuid, socket);
            socket.write(uuid.toString());
            try {
                socket.write(mapper.writeValueAsString(state));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            socket.handler(buffer -> {
                try {
                    mapper.readValue(buffer.toString(), EventType.class);

                } catch (IOException e) {
                    throw new RuntimeException(e);
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
        state.update(null, delta);
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
