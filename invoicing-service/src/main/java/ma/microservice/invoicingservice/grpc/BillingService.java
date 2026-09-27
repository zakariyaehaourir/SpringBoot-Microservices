package ma.microservice.invoicingservice.grpc;

import invoicing.BillingRequest;
import invoicing.BillingResponse;
import invoicing.InvoicingServiceGrpc;
import org.springframework.grpc.server.service.GrpcService;
//import invoicing.*;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class BillingService extends InvoicingServiceGrpc.InvoicingServiceImplBase {
    @Override
    public void createBillingAccount(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {
        System.out.println("Rah ja message :" + request);
    }
}
