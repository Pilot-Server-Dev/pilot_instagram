package pilot.instagram.global;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Object> argumentException(IllegalArgumentException e) {
        return ApiResponse.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }
}
