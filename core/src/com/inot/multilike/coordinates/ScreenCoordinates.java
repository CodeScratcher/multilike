package com.inot.multilike.coordinates;

import lombok.Value;

@Value
public class ScreenCoordinates {
    int x, y;
    Camera camera;

    WorldCoordinates toWorldCoordinates() {
        return new WorldCoordinates(x + camera.getX(), y + camera.getY());
    }
}
