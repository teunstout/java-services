package teun.stout.sour.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import teun.stout.sour.common.exception.SourNotFoundException;
import teun.stout.sour.common.exception.SourParamException;

import java.util.Map;

@ControllerAdvice
public class SourControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(SourControllerAdvice.class);

    /**
     * This is the default exception handler.
     * It logs an error because these are unhandled errors
     *
     * @param e unhandled exception
     * @return internal server error response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleDefault(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiError.of(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
    }

    @ExceptionHandler(SourNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(SourNotFoundException ex) {
        log.info(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(SourParamException.class)
    public ResponseEntity<ApiError> handleSourParamException(SourNotFoundException ex) {
        log.info(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.of(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

}
