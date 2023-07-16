package com.inot.multilike.model;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.inot.multilike.Event;
import com.inot.multilike.entity.Entity;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

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

    public void update(List<Event> events) {
        for (Entity entity : entityList.values()) {
            entity.update(this, events);
        }
    }
}
