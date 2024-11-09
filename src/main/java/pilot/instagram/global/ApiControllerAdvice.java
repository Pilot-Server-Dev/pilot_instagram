package pilot.instagram.global;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Object> argumentException(IllegalArgumentException e) {
        return ApiResponse.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> argumentException(MethodArgumentNotValidException e) {
        System.out.println(e.getMessage() + "aaaa");
        return ApiResponse.of(HttpStatus.BAD_REQUEST, e.getFieldError().getDefaultMessage(), null);
    }
}
