package ma.microservice.invoicingservice.service.impl;

import invoicing.BillingRequest;
import lombok.RequiredArgsConstructor;
import ma.microservice.invoicingservice.model.Invoice;
import ma.microservice.invoicingservice.repository.InvoiceRepository;
import ma.microservice.invoicingservice.service.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Transactional
    @Override
    public Invoice saveBillingAccount(BillingRequest request, float amount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime parsedDate = LocalDateTime.parse(request.getCreatedAt(), formatter);

        Invoice invoice = new Invoice();
        invoice.setPatient_id(UUID.fromString(request.getPatientId()));
        invoice.setFullName(request.getName());
        invoice.setEmail(request.getEmail());
        invoice.setCreatedAt(parsedDate);
        invoice.setAmount(amount);

        return invoiceRepository.save(invoice);
    }
}
