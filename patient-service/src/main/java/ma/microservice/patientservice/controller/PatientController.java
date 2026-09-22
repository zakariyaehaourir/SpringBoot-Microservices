package ma.microservice.patientservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.microservice.patientservice.dto.PatientCreateRequest;
import ma.microservice.patientservice.dto.PatientCreatedResponse;
import ma.microservice.patientservice.dto.PatientList;
import ma.microservice.patientservice.dto.PatientUpdateRequest;
import ma.microservice.patientservice.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping()
    public ResponseEntity<List<PatientList>> patients(){
        return ResponseEntity.ok(this.patientService.getAllPatients());
    }

    @PostMapping()
    public ResponseEntity<PatientCreatedResponse> create(@Valid @RequestBody PatientCreateRequest request){
        PatientCreatedResponse response = this.patientService.createPatient(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return  ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody PatientUpdateRequest request, @PathVariable  String id){
        this.patientService.updatePatient(UUID.fromString(id) , request);
        return ResponseEntity.noContent().build();
    }
}
