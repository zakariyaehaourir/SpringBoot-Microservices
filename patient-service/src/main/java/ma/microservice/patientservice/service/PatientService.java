package ma.microservice.patientservice.service;

import ma.microservice.patientservice.dto.PatientCreateRequest;
import ma.microservice.patientservice.dto.PatientCreatedResponse;
import ma.microservice.patientservice.dto.PatientList;
import ma.microservice.patientservice.dto.PatientUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    public List<PatientList> getAllPatients();

    public PatientCreatedResponse createPatient(PatientCreateRequest request);

    public void updatePatient(UUID uuid ,PatientUpdateRequest request);
}
