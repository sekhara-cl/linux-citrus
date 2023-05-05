package citrusleaf.contact_form_submission.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Exception {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> userMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            userMap.put(error.getField(), error.getDefaultMessage());
        });
        return userMap;
    }
}
