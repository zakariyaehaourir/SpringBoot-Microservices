package ma.microservice.invoicingservice.service;

import invoicing.BillingRequest;
import ma.microservice.invoicingservice.model.Invoice;

public interface InvoiceService {
    Invoice saveBillingAccount(BillingRequest request , float amount);
}
