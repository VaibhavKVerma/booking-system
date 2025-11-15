package com.booking.common.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {
    private final String message;
    private final int status;
    private final String errorCode;
    private long timestamp;
    private final Map<String, String> errorDetails;
}
