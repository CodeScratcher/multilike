package com.inot.multilike.model;

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

    public void update() {
        for (Entity entity : entityList) {
            entity.update(this);
        }
    }
}
