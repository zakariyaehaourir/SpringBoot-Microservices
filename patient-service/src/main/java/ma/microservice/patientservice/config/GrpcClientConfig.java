package ma.microservice.patientservice.config;

import invoicing.InvoicingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {
    @Value("${spring.grpc.client.channels.invoicing-channel.address}")
    private String invoicingServerAddress;

    @Bean
    public InvoicingServiceGrpc.InvoicingServiceBlockingStub invoicingChannelStub() {

        String target = invoicingServerAddress.replace("static://", "");
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();

        return InvoicingServiceGrpc.newBlockingStub(channel);
    }
}
