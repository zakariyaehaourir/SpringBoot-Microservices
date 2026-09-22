package ma.microservice.patientservice.dto.api;

import lombok.Builder;

import java.time.Instant;
import java.util.List;


@Builder
public record ApiResponse<T>(
    Instant timestamp,
    int status,
    String message,
    T data,
    List<ApiError> errors
){}
