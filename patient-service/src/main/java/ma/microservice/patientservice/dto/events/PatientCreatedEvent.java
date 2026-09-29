package ma.microservice.patientservice.dto.events;
import java.time.LocalDateTime;
import java.util.UUID;

public record PatientCreatedEvent(
        UUID patientId,
        String name,
        String email,
        LocalDateTime createdAt
) {}