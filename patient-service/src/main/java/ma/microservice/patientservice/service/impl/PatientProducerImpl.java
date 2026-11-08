package ma.microservice.patientservice.service.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.microservice.patientservice.dto.events.PatientCreatedEvent;
import ma.microservice.patientservice.service.PatientProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@RequiredArgsConstructor
@Service
public class PatientProducerImpl implements PatientProducer {

    @Value("${kafka.topics.patient-topic}")
    private String patientTopic;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publishPatientCreatedEvent(PatientCreatedEvent event) {
        log.info("Start sending event into Kafka broker for : {}" , event.patientId());
        try {
            byte[] payloadBytes = objectMapper.writeValueAsBytes(event);

            String messageKey = event.patientId().toString();
            this.kafkaTemplate.send(patientTopic, messageKey, payloadBytes)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.error("Error while sending event to kafka broker : {}", ex.getMessage());
                        } else {
                            log.info("Event pushed to Kafka successfully metadata : {}", result.getRecordMetadata().offset());
                        }
                    });

        } catch (Exception e) {
            log.error("Failed to serialize event to bytes array", e);
            throw new RuntimeException("Error processing Kafka payload serialization", e);
        }
    }

}
