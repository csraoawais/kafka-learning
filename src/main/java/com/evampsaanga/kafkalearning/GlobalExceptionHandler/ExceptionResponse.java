package com.evampsaanga.kafkalearning.GlobalExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private int statusCode;
    private String message;
    private String description;
    private String timestamp;

}
