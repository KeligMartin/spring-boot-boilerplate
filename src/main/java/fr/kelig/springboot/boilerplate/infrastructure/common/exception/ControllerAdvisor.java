package fr.kelig.springboot.boilerplate.infrastructure.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceWithIdNotFoundException.class)
    protected ResponseEntity<?> resourceWithIdNotFound(ResourceWithIdNotFoundException exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                exception.getErrors(),
                new HttpHeaders(),
                NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(ProfileWithUsernameNotFoundException.class)
    protected ResponseEntity<?> profileWithUsernameNotFound(ProfileWithUsernameNotFoundException exception,
                                                            WebRequest request) {
        return handleExceptionInternal(exception,
                exception.getErrors(),
                new HttpHeaders(),
                NOT_FOUND,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                             HttpHeaders httpHeaders,
                                                             HttpStatus httpStatus,
                                                             WebRequest request) {

        BindingResult bindingResult = exception.getBindingResult();
        List<Map<String, String>> errors = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", fieldError.getDefaultMessage());
                    response.put("object", fieldError.getObjectName());
                    response.put("code", fieldError.getCode());
                    response.put("field", fieldError.getField());
                    if (fieldError.getRejectedValue() != null) {
                        response.put("value", fieldError.getRejectedValue().toString());
                    }
                    return response;
                })
                .collect(Collectors.toList());

        log.error("Argument not valid");

        return handleExceptionInternal(exception, errors, new HttpHeaders(), BAD_REQUEST, request);
    }
}
