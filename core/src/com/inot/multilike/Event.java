package com.inot.multilike;

import lombok.Data;

import java.util.UUID;

@Data
public class Event {
    EventType eventType;
    UUID target;
}
