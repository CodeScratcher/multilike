package com.inot.multilike.entity;

import com.inot.multilike.coordinates.WorldCoordinates;
import com.inot.multilike.textures.TextureID;

public interface SpriteEntity extends Entity {
    WorldCoordinates getCoordinates();
    TextureID getId();
}
