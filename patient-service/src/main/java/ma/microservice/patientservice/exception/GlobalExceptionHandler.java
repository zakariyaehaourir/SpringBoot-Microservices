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



    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(Exception ex){
        ApiResponse<Void> response = ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(PatientEmailExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiResponse<Void>> handlePatientEmailExists(Exception ex){
        ApiResponse<Void> response = ApiResponse.error((HttpStatus.CONFLICT.value()) , ex.getMessage());
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<Void>> handleInternalServerError(Exception ex){
        ApiResponse<Void> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected internal error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
