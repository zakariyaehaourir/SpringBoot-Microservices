package ma.microservice.evaluationservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.microservice.evaluationservice.dto.PatientCreatedEvent;
import ma.microservice.evaluationservice.service.PatientCreatedConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientEventsConsumerService implements PatientCreatedConsumer {

    private final ObjectMapper objectMapper;

    @Override
    @KafkaListener(topics = "${kafka.topics.patient-topic}" , groupId = "${spring.kafka.consumer.group-id}")
    public void consumeEvent(ConsumerRecord<String, byte[]> byteEvent) {
        String eventId = byteEvent.key();
        try{
            PatientCreatedEvent event = this.objectMapper.readValue(byteEvent.value() , PatientCreatedEvent.class);
            log.info("Event consumed successfully : {}" , event);

            String jsonOutput = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);

            Path filePath = Paths.get("evaluation-analytics.json");
            Files.writeString(filePath , jsonOutput , StandardOpenOption.CREATE , StandardOpenOption.APPEND);

        }catch(Exception e){
            log.error("Error while deserializing event : {}" , e.getMessage());
        }
    }
}
