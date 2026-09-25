package ma.microservice.patientservice.dto.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ApiResponse<T>(
    Instant timestamp,
    int status,
    String message,
    T data,
    List<ApiError> errors
){

    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(Instant.now(), status, message, data, null);
    }
    public static <T> ApiResponse<T> success(int status, String message) {
        return new ApiResponse<>(Instant.now(), status, message, null, null);
    }
    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(Instant.now(), status, message, null, null);
    }


    public static <T> ApiResponse<T> error(int status, String message, List<ApiError> errors) {
        return new ApiResponse<>(Instant.now(), status, message, null, errors);
    }


}
