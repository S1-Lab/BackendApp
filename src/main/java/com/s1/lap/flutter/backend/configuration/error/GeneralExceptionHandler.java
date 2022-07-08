package com.s1.lap.flutter.backend.configuration.error;

import com.s1.lap.flutter.backend.configuration.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler {

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(Throwable throwable, HttpStatus status) {
        return newResponse(throwable.getMessage(), status);
    }

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(String message, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ApiUtils.error(message, status), headers, status);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        if (e instanceof MethodArgumentNotValidException) {
            return newResponse(
                    ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                    HttpStatus.BAD_REQUEST
            );
        } else if (e instanceof ConstraintViolationException) {
            Optional<ConstraintViolation<?>> constraintViolation = ((ConstraintViolationException) e).getConstraintViolations().stream().findFirst();
            return constraintViolation.map(c -> newResponse(c.getMessageTemplate(), HttpStatus.BAD_REQUEST))
                    .orElseGet(() -> newResponse(e, HttpStatus.BAD_REQUEST));
        }
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<?> handleHttpMediaTypeException(Exception e) {
        return newResponse(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(Exception e) {
        return newResponse(e, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
