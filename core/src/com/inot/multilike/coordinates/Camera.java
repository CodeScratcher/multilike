package com.inot.multilike.coordinates;

import lombok.Value;

@Value
public class Camera {
    int x, y;
    public ScreenCoordinates renderWorldCoordinates(WorldCoordinates worldCoordinates) {
        return new ScreenCoordinates(worldCoordinates.getX() - x, worldCoordinates.getY() - y, this);
    }

    public ScreenCoordinates shiftScreenCoordinates(ScreenCoordinates screenCoordinates) {
        return renderWorldCoordinates(screenCoordinates.toWorldCoordinates());
    }
}
