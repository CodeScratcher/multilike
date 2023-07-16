package com.inot.multilike;

import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;

import java.util.ArrayList;
import java.util.List;

public class ServerLauncher {
    public static void main(String[] args) {
        HeadlessApplicationConfiguration headlessApplicationConfiguration = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new MultiLikeServer(), headlessApplicationConfiguration);
    }
}