package com.inot.multilike.entity;

import com.inot.multilike.Event;
import com.inot.multilike.model.GameState;

import java.util.List;

public interface Entity {
    void update(GameState state, List<Event> events);
}
