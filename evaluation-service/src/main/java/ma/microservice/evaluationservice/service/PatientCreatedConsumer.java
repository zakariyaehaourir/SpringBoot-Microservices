package ma.microservice.evaluationservice.service;

import ma.microservice.evaluationservice.dto.PatientCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface PatientCreatedConsumer {
    void consumeEvent(ConsumerRecord<String, byte[]> byteEvent);
}
