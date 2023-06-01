package com.inot.multilike;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.inot.multilike.MultiLike;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Vertx vertx = Vertx.vertx();

		NetClient client = vertx.createNetClient();

		client
				.connect(4321, "localhost")
				.onComplete(res -> {
					if (res.succeeded()) {
						System.out.println("Connected!");
						NetSocket socket = res.result();
						socket.write("some data");
					} else {
						System.out.println("Failed to connect: " + res.cause().getMessage());

					}
				});

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Multiplayer Roguelike");
		new Lwjgl3Application(new MultiLike(), config);



	}
}
