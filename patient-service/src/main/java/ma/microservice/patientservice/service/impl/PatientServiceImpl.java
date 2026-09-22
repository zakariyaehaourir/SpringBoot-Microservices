package ma.microservice.patientservice.service.impl;

import lombok.RequiredArgsConstructor;
import ma.microservice.patientservice.dto.PatientCreateRequest;
import ma.microservice.patientservice.dto.PatientCreatedResponse;
import ma.microservice.patientservice.dto.PatientList;
import ma.microservice.patientservice.dto.PatientUpdateRequest;
import ma.microservice.patientservice.exception.ResourceNotFoundException;
import ma.microservice.patientservice.mapper.PatientMapper;
import ma.microservice.patientservice.model.Patient;
import ma.microservice.patientservice.repository.PatientRepository;
import ma.microservice.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public List<PatientList> getAllPatients(){
        return PatientMapper.toPatientList(
          this.patientRepository.findAll()
        );

    }

    @Override
    public PatientCreatedResponse createPatient(PatientCreateRequest request) {
        Patient savedEntity = this.patientRepository.saveAndFlush(PatientMapper.toModal(request));

        return PatientMapper.toPatientCreatedResponse(savedEntity);
    }


    @Override
    public void updatePatient(UUID uuid, PatientUpdateRequest request) {
        Patient existingPatient = this.patientRepository.findById(uuid).
                orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + uuid));

        existingPatient.setName(request.getName());
        existingPatient.setAddress(request.getAddress());
        existingPatient.setEmail(request.getEmail());
        existingPatient.setBirthDate(request.getBirthDate());

        this.patientRepository.flush();


    }
}
