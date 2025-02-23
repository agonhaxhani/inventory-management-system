package com.example.ims.invoice.dto;

import com.example.ims.invoice.entity.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class InvoiceDTO {
    private Long id;
    private String invoiceNo;
    private Double totalAmount;
    private List<InvoiceSaleDTO> saleProduct;

    public InvoiceDTO(Invoice invoice) {
        this.id = invoice.getId();
        this.invoiceNo = invoice.getInvoiceNumber();
        this.totalAmount = invoice.getTotalAmount();

        if (invoice.getSales() != null && !invoice.getSales().isEmpty()) {
            this.saleProduct = invoice.getSales().stream().map(InvoiceSaleDTO::new).collect(Collectors.toList());
        }
    }
}
