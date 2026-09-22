package ma.microservice.patientservice.service;

import ma.microservice.patientservice.dto.PatientCreateRequest;
import ma.microservice.patientservice.dto.PatientCreatedResponse;
import ma.microservice.patientservice.dto.PatientList;

import java.util.List;

public interface PatientService {
    public List<PatientList> getAllPatients();

    public PatientCreatedResponse createPatient(PatientCreateRequest request);
}
