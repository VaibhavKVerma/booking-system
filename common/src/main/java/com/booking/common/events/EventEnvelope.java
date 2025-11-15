package com.booking.common.events;

import java.time.Instant;

public class EventEnvelope<T> {
    String id;
    String type;
    T payload;
    Instant occurredAt;
    String version;
}