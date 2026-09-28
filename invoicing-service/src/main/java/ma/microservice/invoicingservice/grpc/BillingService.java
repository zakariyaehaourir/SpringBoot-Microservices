package ma.microservice.invoicingservice.grpc;

import invoicing.BillingRequest;
import invoicing.BillingResponse;
import invoicing.InvoicingServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;
//import invoicing.*;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillingService extends InvoicingServiceGrpc.InvoicingServiceImplBase {
    @Override
    public void createBillingAccount(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {
        log.info("Rah ja message : {}" ,request);
    }
}
