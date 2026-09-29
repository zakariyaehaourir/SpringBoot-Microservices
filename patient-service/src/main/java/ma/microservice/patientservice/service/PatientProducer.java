package ma.microservice.patientservice.service;

import ma.microservice.patientservice.dto.events.PatientCreatedEvent;

public interface PatientProducer {

    void publishPatientCreatedEvent(PatientCreatedEvent event);
}
