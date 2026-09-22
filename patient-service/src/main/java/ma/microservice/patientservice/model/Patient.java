package ma.microservice.patientservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @NotBlank
    private LocalDateTime registeredAt;



}
