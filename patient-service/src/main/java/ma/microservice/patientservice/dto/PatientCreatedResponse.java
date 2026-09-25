package ma.microservice.patientservice.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class PatientCreatedResponse {
    private UUID id;
    private String name;


    private String address;


    private String email;
    private LocalDate birthDate;
}
