package com.booking.common.audit;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@MappedSuperclass
abstract class Auditable {
    @CreatedDate
    Instant createdAt;
    @LastModifiedDate
    Instant updatedAt;
    String createdBy;
    String updatedBy;
}
