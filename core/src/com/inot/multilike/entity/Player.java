package com.inot.multilike.entity;

import com.inot.multilike.Event;
import com.inot.multilike.coordinates.WorldCoordinates;
import com.inot.multilike.model.GameState;
import com.inot.multilike.textures.TextureID;

import java.util.ArrayList;
import java.util.List;

public class Player implements SpriteEntity {
    WorldCoordinates coords;
    public Player() {
        coords = new WorldCoordinates(0, 0);
    }
    @Override
    public void update(GameState state, List<Event> events) {
        for (Event i : events) {
            int deltaX = 0, deltaY = 0;
            switch (i) {
                case UP:
                    deltaY += 5;
                    break;
                case DOWN:
                    deltaY -= 5;
                    break;
                case LEFT:
                    deltaX += 5;
                    break;
                case RIGHT:
                    deltaX -= 5;
                    break;
                default:
                    break;
            }

            coords = coords.translate(deltaX, deltaY);
        }
    }

    @Override
    public WorldCoordinates getCoordinates() {
        return coords;
    }

    @Override
    public TextureID getId() {
        return TextureID.PLAYER;
    }
}
