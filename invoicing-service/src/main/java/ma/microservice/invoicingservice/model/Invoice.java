package ma.microservice.invoicingservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String fullName;
    @Email
    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false , unique = true)
    private UUID patient_id;

    @Column(nullable = true)
    private  float amount;
    private LocalDateTime createdAt;
}
