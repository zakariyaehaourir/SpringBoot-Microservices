package ma.microservice.evaluationservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PatientCreatedEvent(
        UUID patientId,
        String name,
        String email,
        LocalDateTime createdAt
) {
}
