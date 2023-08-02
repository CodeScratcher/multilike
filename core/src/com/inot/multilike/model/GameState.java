package com.inot.multilike.model;

import com.inot.multilike.EventType;
import com.inot.multilike.entity.Entity;
import lombok.Getter;

import java.util.*;

public class GameState {
    @Getter
    Map<UUID, Entity> entityList;

    public GameState() {
        entityList = new HashMap<>();
    }

    public UUID addEntity(Entity entity) {
        UUID id = UUID.randomUUID();
        entityList.put(id, entity);
        return id;
    }

    public Entity getEntity(UUID id) {
        return entityList.get(id);
    }

    public void update(List<EventType> events, float delta) {
        for (Entity entity : entityList.values()) {
            entity.update(this, events, delta);
        }
    }
}
