package teun.stout.sour.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import teun.stout.sour.common.exception.SourNotFoundException;
import teun.stout.sour.common.exception.SourParamException;

import java.util.Map;

@ControllerAdvice
public class SourControllerAdvice {

    @ExceptionHandler(SourNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(SourNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(SourParamException.class)
    public ResponseEntity<ApiError> handleSourParamException(SourNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.of(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

}
