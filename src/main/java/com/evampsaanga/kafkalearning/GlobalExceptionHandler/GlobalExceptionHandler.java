package com.evampsaanga.kafkalearning.GlobalExceptionHandler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GlobalExceptionHandler to handle the exceptions globally from the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger exceptionLogger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = {ResourceAccessException.class})
    public ResponseEntity<ExceptionResponse> handleResourceAccessException(ResourceAccessException ex) {
        exceptionLogger.debug("ResourceAccessException started.");
        return prepareExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE, ex);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException ex) {
        exceptionLogger.debug("handleNullPointerException started.");
        return prepareExceptionResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<ExceptionResponse> handleHttpClientErrorException(HttpClientErrorException ex) {
        exceptionLogger.debug("handleHttpClientErrorException started.");
        return prepareExceptionResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        exceptionLogger.debug("handleException started.");
        return prepareExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    /**
     * Prepare a ExceptionResponse object which have all the required attributes to convey the exception meaning.
     */
    private ResponseEntity<ExceptionResponse> prepareExceptionResponse(HttpStatus httpStatus, Exception exception) {
        ExceptionResponse exceptionResponse = getExceptionResponse(httpStatus.value(), httpStatus.name(),
                ExceptionUtils.getMessage(exception));
        exceptionLogger.error("Exception StackTrace= {} | {}", ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(exceptionResponse, httpStatus);
    }

    /**
     * This method will return the object.
     *
     * @param statusCode  -- status code of exception (200,404 etc)
     * @param message     -- like BAD_REQUEST , INTERNAL_SERVER_ERROR
     * @param description -- actual exception reason/cause
     */
    public static ExceptionResponse getExceptionResponse(int statusCode, String message, String description) {
        String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        return new ExceptionResponse(statusCode, message, description, timestamp);
    }
}
