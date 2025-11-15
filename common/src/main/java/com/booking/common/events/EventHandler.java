package com.booking.common.events;

public interface EventHandler<T> {
    void handle(EventEnvelope<T> event);
}