package ma.microservice.patientservice.mapper;

import ma.microservice.patientservice.dto.PatientList;
import ma.microservice.patientservice.model.Patient;

import java.util.List;

public class PatientMapper {
    public static List<PatientList> toPatientList(List<Patient> patients) {

        return patients.stream()
                .map(patient -> PatientList.builder()
                        .id(patient.getId())
                        .name(patient.getName())
                        .address(patient.getAddress())
                        .email(patient.getEmail())
                        .birthDate(patient.getBirthDate())
                        .build()
                )
                .toList();
    }
}
