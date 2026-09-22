package ma.microservice.patientservice.dto.api;

import lombok.Builder;

@Builder
public record ApiError(
        String code,
        String message,
        String field
) {}
