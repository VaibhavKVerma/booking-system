package com.booking.common.events;

public interface EventPublisher {
    <T> void publish(String topic, EventEnvelope<T> event);
}
