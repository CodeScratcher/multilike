package com.inot.multilike.coordinates;

import lombok.Data;
import lombok.Value;

@Value
public class WorldCoordinates {
    int x, y;

    public WorldCoordinates translate(int deltaX, int deltaY) {
        return new WorldCoordinates(x + deltaX, y + deltaY);
    }
}
