package ma.microservice.patientservice.dto;

import lombok.Builder;

@Builder
public record ApiError(
        String code,
        String message,
        String field
) {}
