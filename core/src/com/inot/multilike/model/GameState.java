package com.inot.multilike.model;

import com.inot.multilike.Event;
import com.inot.multilike.entity.Entity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    @Getter
    List<Entity> entityList;

    public GameState() {
        entityList = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public void update(List<Event> events) {
        for (Entity entity : entityList) {
            entity.update(this, events);
        }
    }
}
