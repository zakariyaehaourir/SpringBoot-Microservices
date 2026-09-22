package ma.microservice.patientservice.exception;


import ma.microservice.patientservice.dto.api.ApiError;
import ma.microservice.patientservice.dto.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex){
        List<ApiError> errors = ex.getBindingResult().getFieldErrors().stream().map(err->new ApiError(
                err.getField(),
                err.getField(),
                err.getDefaultMessage()
        )).toList();

        ApiResponse<Void> response = ApiResponse.error(HttpStatus.UNPROCESSABLE_CONTENT.value() , "Validation failed for one or more fields" , errors);
        return ResponseEntity.unprocessableContent().body(response);
    }
}
