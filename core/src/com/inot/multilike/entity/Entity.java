package com.inot.multilike.entity;

import com.inot.multilike.EventType;
import com.inot.multilike.model.GameState;

import java.util.List;

public interface Entity {
    void update(GameState state, List<EventType> events, float delta);
}
