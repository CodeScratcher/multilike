package com.inot.multilike.entity;

import com.inot.multilike.coordinates.WorldCoordinates;
import com.inot.multilike.model.GameState;
import com.inot.multilike.textures.TextureID;

public class Player implements SpriteEntity {
    WorldCoordinates coords;
    public Player() {
        coords = new WorldCoordinates(0, 0);
    }
    @Override
    public void update(GameState state) {

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
