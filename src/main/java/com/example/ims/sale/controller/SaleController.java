package com.example.ims.sale.controller;

import com.example.ims.sale.dto.SaleRequestDTO;
import com.example.ims.sale.service.SaleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public void save(@RequestBody SaleRequestDTO saleRequestDTO) {
        saleService.processSale(saleRequestDTO);
    }
}
