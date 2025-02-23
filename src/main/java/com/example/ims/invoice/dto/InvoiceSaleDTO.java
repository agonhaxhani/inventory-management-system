package com.example.ims.invoice.dto;

import com.example.ims.sale.entity.Sale;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceSaleDTO {
    private Long id;
    private String productName;
    private Double productPrice;
    private int quantity;
    private Double totalAmount;

    public InvoiceSaleDTO(Sale sale) {
        this.id = sale.getId();
        this.productName = sale.getProduct().getName();
        this.productPrice = sale.getProduct().getPrice();
        this.quantity = sale.getQuantity();
        this.totalAmount = sale.getTotalAmount();
    }
}
