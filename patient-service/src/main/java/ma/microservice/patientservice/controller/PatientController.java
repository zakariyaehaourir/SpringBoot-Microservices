package ma.microservice.patientservice.controller;

import lombok.RequiredArgsConstructor;
import ma.microservice.patientservice.dto.PatientList;
import ma.microservice.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping()
    public ResponseEntity<List<PatientList>> patients(){
        return ResponseEntity.ok(this.patientService.getAllPatients());
    }
}
