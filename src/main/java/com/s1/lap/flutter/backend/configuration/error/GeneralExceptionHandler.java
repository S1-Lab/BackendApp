package com.s1.lap.flutter.backend.configuration.error;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.s1.lap.flutter.backend.configuration.util.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;
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
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class
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
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            return newResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        Throwable throwable = e.getMostSpecificCause();
        if (throwable instanceof InvalidFormatException) {
            return newResponse(
                    getInvalidFormatExceptionMessage((InvalidFormatException) throwable),
                    HttpStatus.BAD_REQUEST
            );
        } else if (throwable instanceof MismatchedInputException) {
            return newResponse(
                    getMismatchedInputExceptionMessage((MismatchedInputException) throwable),
                    HttpStatus.BAD_REQUEST
            );
        } else if (throwable instanceof JsonParseException) {
            return newResponse(
                    getJsonParseExceptionMessage((JsonParseException) throwable),
                    HttpStatus.BAD_REQUEST
            );
        } else if (throwable instanceof DateTimeParseException) {
            return newResponse(
                    getDateTimeParseExceptionMessage((DateTimeParseException) throwable),
                    HttpStatus.BAD_REQUEST
            );
        }
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getInvalidFormatExceptionMessage(InvalidFormatException invalidFormatException) {
        String message = "Can not deserialize value of type "
                + "[" + invalidFormatException.getTargetType().getSimpleName() + "]"
                + " from " + invalidFormatException.getValue().getClass().getSimpleName() + " "
                + "[" + invalidFormatException.getValue() + "]"
                + " at [line: " + invalidFormatException.getLocation().getLineNr()
                + ", column: " + invalidFormatException.getLocation().getColumnNr() + "]";

        if (!ObjectUtils.isEmpty(invalidFormatException.getPath()) && invalidFormatException.getPath().size() > 0) {
            JsonMappingException.Reference ref = invalidFormatException.getPath().get(invalidFormatException.getPath().size() - 1);
            message += ", Path: " + ref.getFrom().getClass().getSimpleName()
                    + "[" + ref.getFieldName() + "]";
        }
        return message;
    }

    private String getMismatchedInputExceptionMessage(MismatchedInputException mismatchedInputException) {
        return "Can not deserialize value of type "
                + "[" + mismatchedInputException.getTargetType().getSimpleName() + "]"
                + " at [line: " + mismatchedInputException.getLocation().getLineNr()
                + ", column: " + mismatchedInputException.getLocation().getColumnNr() + "]";
    }

    private String getJsonParseExceptionMessage(JsonParseException jsonParseException) {
        return "Can not deserialize value of type "
                + "[" + jsonParseException.getOriginalMessage() + "]"
                + " at [line: " + jsonParseException.getLocation().getLineNr()
                + ", column: " + jsonParseException.getLocation().getColumnNr() + "]";
    }

    private String getDateTimeParseExceptionMessage(DateTimeParseException dateTimeParseException) {
        return "Can not deserialize value of type "
                + "[" + dateTimeParseException.getMessage() + "]";
    }
}