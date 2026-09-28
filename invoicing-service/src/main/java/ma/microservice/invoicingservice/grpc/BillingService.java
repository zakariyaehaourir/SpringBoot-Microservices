package ma.microservice.invoicingservice.grpc;

import invoicing.BillingRequest;
import invoicing.BillingResponse;
import invoicing.InvoicingServiceGrpc;
import io.grpc.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.grpc.stub.StreamObserver;
import ma.microservice.invoicingservice.model.Invoice;
import ma.microservice.invoicingservice.service.InvoiceService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillingService extends InvoicingServiceGrpc.InvoicingServiceImplBase {
    private final InvoiceService invoiceService;


    @Override
    public void createBillingAccount(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {
        log.info("gRPC request Payload: {}", request);
        try {

            Invoice savedInvoice = invoiceService.saveBillingAccount(request , 95);

            BillingResponse response = BillingResponse.newBuilder()
                    .setInvoiceId(savedInvoice.getId().toString())
                    .build();

            responseObserver.onNext(response);

            responseObserver.onCompleted();

        } catch (IllegalArgumentException e) {
            log.error("Error in payload data : {}", e.getMessage());
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("UUID invalid : " + e.getMessage())
                    .asRuntimeException());
        } catch (Exception e) {
            log.error("Error in gRPC server while saving invoice. : ", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error in gRPC server while saving invoice.")
                    .withCause(e)
                    .asRuntimeException());
        }

    }
}
