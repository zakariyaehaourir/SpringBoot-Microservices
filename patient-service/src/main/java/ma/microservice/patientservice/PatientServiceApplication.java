package ma.microservice.patientservice;

import invoicing.InvoicingServiceGrpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.client.ImportGrpcClients;

@SpringBootApplication
@ImportGrpcClients(target = "invoicing-channel", types = InvoicingServiceGrpc.InvoicingServiceBlockingStub.class)

public class PatientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApplication.class, args);
    }
    @Bean
    public ApplicationRunner debugConfig(
            // 🎯 On demande à Spring d'injecter la clé exacte pour voir sa valeur
            @Value("${grpc.client.invoicing-channel.address:NON_TROUVE}") String grpcUrlField,
            @Value("${spring.grpc.client.channels.invoicing-channel.address:NON_TROUVE}") String grpcUrlChannels) {

        return args -> {
            System.out.println("==================================================");
            System.out.println("🔍 DEBOGAGE DE LA CONFIGURATION gRPC :");
            System.out.println("-> Valeur sous 'grpc.client.invoicing-channel.address' : " + grpcUrlField);
            System.out.println("-> Valeur sous 'spring.grpc.client.channels...'         : " + grpcUrlChannels);
            System.out.println("==================================================");
        };
    }
}
