package ma.microservice.patientservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientUpdateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String address;


    @NotEmpty

    @Size(min = 5 , max = 70)
    private String email;

    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date doit être dans le passé")
    private LocalDate birthDate;

}
