package com.camping.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String message;
    private String detail;
    private String code;

    public ExceptionResponse(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = "";
    }
}
