package com.example.ims.sale.service;

import com.example.ims.category.entity.Category;
import com.example.ims.exceptions.BadRequestException;
import com.example.ims.inventory.entity.Inventory;
import com.example.ims.inventory.service.InventoryService;
import com.example.ims.invoice.entity.Invoice;
import com.example.ims.invoice.service.InvoiceService;
import com.example.ims.sale.repository.SaleRepository;
import com.example.ims.product.dto.ProductDTO;
import com.example.ims.product.entity.Product;
import com.example.ims.product.service.ProductService;
import com.example.ims.sale.dto.SaleItemDTO;
import com.example.ims.sale.dto.SaleRequestDTO;
import com.example.ims.sale.entity.Sale;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final InventoryService inventoryService;
    private final ProductService productService;
    private final InvoiceService invoiceService;

    public SaleService(InventoryService inventoryService,
                       ProductService productService,
                       SaleRepository saleRepository,
                       InvoiceService invoiceService) {
        this.inventoryService = inventoryService;
        this.productService = productService;
        this.saleRepository = saleRepository;
        this.invoiceService = invoiceService;
    }

    @Transactional
    public void processSale(SaleRequestDTO saleRequest) {
        validateStock(saleRequest);

        double totalInvoiceAmount = 0;

        Invoice newInvoice = new Invoice();
        newInvoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
        newInvoice.setTotalAmount(totalInvoiceAmount);
        newInvoice = invoiceService.save(newInvoice);

        for (SaleItemDTO item : saleRequest.getItems()) {
            ProductDTO product = productService.getProductById(item.getProductId());

            Product p = new Product();
            p.setId(product.getId());
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setSubcategory(new Category(product.getSubcategoryId(), product.getName()));

            Sale sale = new Sale();
            sale.setProduct(p);
            sale.setInvoice(newInvoice);
            sale.setQuantity(item.getQuantity());
            sale.setPrice(product.getPrice());

            double saleTotal = product.getPrice() * item.getQuantity();
            sale.setTotalAmount(saleTotal);

            inventoryService.removeFromInventory(item.getProductId(), item.getQuantity());
            Sale savedSale = saleRepository.save(sale);
            newInvoice.getSales().add(savedSale);
            totalInvoiceAmount = totalInvoiceAmount + saleTotal;
        }


        newInvoice.setTotalAmount(totalInvoiceAmount);
        invoiceService.save(newInvoice);
    }

    private void validateStock(SaleRequestDTO saleRequest) {
        List<Long> productIds = saleRequest.getItems().stream()
                .map(SaleItemDTO::getProductId)
                .toList();

        List<Inventory> inventories = inventoryService.findByProductIds(productIds);

        Map<Long, Inventory> inventoryMap = inventories.stream()
                .collect(Collectors.toMap(inv -> inv.getProduct().getId(), inv -> inv));

        for (SaleItemDTO item : saleRequest.getItems()) {
            Inventory inventory = inventoryMap.get(item.getProductId());

            if (inventory == null || inventory.getQuantity() < item.getQuantity()) {
                throw new BadRequestException("Not enough stock for product: " + item.getProductId());
            }
        }
    }
}
