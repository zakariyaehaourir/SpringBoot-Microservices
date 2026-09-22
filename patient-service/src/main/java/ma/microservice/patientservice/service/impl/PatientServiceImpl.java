package ma.microservice.patientservice.service.impl;

import lombok.RequiredArgsConstructor;
import ma.microservice.patientservice.dto.PatientList;
import ma.microservice.patientservice.mapper.PatientMapper;
import ma.microservice.patientservice.repository.PatientRepository;
import ma.microservice.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public List<PatientList> getAllPatients(){
        return PatientMapper.toPatientList(
          this.patientRepository.findAll()
        );

    }
}
