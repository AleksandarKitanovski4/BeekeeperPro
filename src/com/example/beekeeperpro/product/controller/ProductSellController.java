package com.example.beekeeperpro.product.controller;

import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.product.service.ProductFinanceService;
import com.example.beekeeperpro.product.service.ProductSellService;
import com.example.beekeeperpro.product.service.ProductStockService;

import java.math.BigDecimal;
import java.util.Map;

public class ProductSellController {

    private final ProductSellService sellService;
    private final ProductFinanceService financeService;
    private final ProductStockService stockService;

    public ProductSellController(ProductSellService sellService,
                                 ProductFinanceService financeService,
                                 ProductStockService stockService) {
        this.sellService = sellService;
        this.financeService = financeService;
        this.stockService = stockService;
    }

    public void sellProduct(int beehiveNumber, ProductType productType, BigDecimal quantity) {
        try {
            sellService.sellProduct(beehiveNumber, productType, quantity);
            System.out.println("Product sold successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error during selling: " + e.getMessage());
        }
    }

    public void showAllStock() {
        Map<ProductType, BigDecimal[]> allStock = stockService.getAllStock();
        System.out.println("Full stock info: ");
        allStock.forEach((type, value) ->
                System.out.println(type +
                        " -> Extracted: " + value[0] +
                        ", Sold: " + value[1]));
    }

    public void showFinanceForBeehive(int beehiveNumber) {
        var list = financeService.getFinanceByBeehiveNumber(beehiveNumber);
        System.out.println("Finance records for beehive number: " + beehiveNumber + ":");
        list.forEach(entry -> System.out.println(
                "ID: " + entry[0] +
                        ", Type: " + entry[1] +
                        ", Extracted: " + entry[2] +
                        ", Sold: " + entry[3] +
                        ", Date: " + entry[4]
        ));
    }
}
