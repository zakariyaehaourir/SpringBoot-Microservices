package ma.microservice.patientservice.grpc;

import com.google.type.DateTime;
import invoicing.*;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceGrpcClient {


    private final InvoicingServiceGrpc.InvoicingServiceBlockingStub invoicingBlockingStub;

    public String createFactureForPatient(UUID patientId , String email , String fullName , LocalDateTime createdAt){
        DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId.toString())
                .setEmail(email)
                .setName(fullName)
                .setCreatedAt(createdAt.format(formateur))
                .build();

        try{
            BillingResponse response = invoicingBlockingStub.createBillingAccount(request);
            return  response.getInvoiceId();
        }catch (StatusRuntimeException e){
            log.error("GRPC error while sending request : {} , {}" , e.getStatus() , e.getMessage());
            throw  new RuntimeException("Error while processing patient invoice creating");
        }

    }
}
