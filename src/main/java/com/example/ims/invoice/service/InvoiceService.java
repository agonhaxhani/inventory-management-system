package com.example.ims.invoice.service;

import com.example.ims.invoice.dto.InvoiceDTO;
import com.example.ims.invoice.entity.Invoice;
import com.example.ims.invoice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll().stream().map(InvoiceDTO::new).collect(Collectors.toList());
    }

}
