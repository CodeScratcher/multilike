package com.inot.multilike;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;
import com.inot.multilike.coordinates.Camera;
import com.inot.multilike.coordinates.ScreenCoordinates;
import com.inot.multilike.entity.Entity;
import com.inot.multilike.entity.Player;
import com.inot.multilike.entity.SpriteEntity;
import com.inot.multilike.model.GameState;
import com.inot.multilike.textures.TextureID;

import java.util.*;

public class GameScreen implements Screen {
    GameState state;

    Camera camera;

    Map<TextureID, Sprite> spritesheet;

    SpriteBatch spriteBatch;
    UUID player;

    @Override
    public void show() {
        state = new GameState();

        state.addEntity(new Player());

        camera = new Camera(0, 0);

        spritesheet = new HashMap<>();
        Texture playerTexture = new Texture("badlogic.jpg");
        Sprite playerSprite = new Sprite(playerTexture);
        spritesheet.put(TextureID.PLAYER, playerSprite);

        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

        List<Event> eventList = new ArrayList<>();

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            eventList.add(Event.LEFT);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            eventList.add(Event.RIGHT);
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            eventList.add(Event.UP);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            eventList.add(Event.DOWN);
        }

        state.update(eventList, delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();

        for (Entity entity : state.getEntityList().values()) {
            if (entity instanceof SpriteEntity) {
                SpriteEntity spriteEntity = (SpriteEntity) entity;

                Sprite sprite = spritesheet.get(spriteEntity.getId());
                ScreenCoordinates screenCoordinates = camera.renderWorldCoordinates(spriteEntity.getCoordinates());
                sprite.setPosition(screenCoordinates.getX(), screenCoordinates.getY());
                sprite.draw(spriteBatch);

            }
        }

        spriteBatch.end();
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
        spriteBatch.dispose();
    }
}
