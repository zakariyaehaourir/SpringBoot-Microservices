package ma.microservice.patientservice.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PatientList {
    private UUID id;
    private String name;


    private String address;

    @Email
    private String email;
    private LocalDate birthDate;
}
