package com.example.ims.sale.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaleRequestDTO {
    List<SaleItemDTO> items;
}
