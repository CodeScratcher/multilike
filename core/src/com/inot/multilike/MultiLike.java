package com.inot.multilike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MultiLike extends ApplicationAdapter {
	SpriteBatch batch;
	NetSocket socket;
	boolean ready = false;
	Texture img;
	Stage stage;
	Table table;

	@Override
	public void create () {


		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		TextureAtlas atlas = new TextureAtlas("skin/skin-composer-ui.atlas");
		Skin skin = new Skin(Gdx.files.internal("skin/skin-composer-ui.json"), atlas);

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		table = new Table();
		table.setFillParent(true);
		table.top();
		stage.addActor(table);

		Label incoming = new Label("", skin, "white");
		table.add(incoming);

		table.row();

		Label usernameLabel = new Label("username: ", skin, "white");
		table.add(usernameLabel);

		TextField username = new TextField("anon", skin);
		table.add(username);

		table.row();

		TextField textField = new TextField("", skin);
		table.add(textField);

		Button button = new TextButton("Send", skin);
		table.add(button);

		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (ready) socket.write("[" + username.getText() + "] " + textField.getText());
				textField.setText("");
			}
		});

		Vertx vertx = Vertx.vertx();

		NetClient client = vertx.createNetClient();

		client
				.connect(4321, "localhost")
				.onComplete(res -> {
					if (res.succeeded()) {
						log.info("Connected!");

						socket = res.result();

						socket.handler(buffer -> {
							System.out.println(buffer);
							incoming.setText(incoming.getText() + buffer.getString(0, buffer.length()) + "\n");
						});

						ready = true;
					} else {
						log.error("Failed to connect: " + res.cause().getMessage());
					}
				});
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		stage.draw();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
