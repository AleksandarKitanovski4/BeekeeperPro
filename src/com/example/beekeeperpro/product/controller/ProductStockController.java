package com.example.beekeeperpro.product.controller;

import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.product.service.ProductStockService;

import java.math.BigDecimal;

public class ProductStockController {

    private final ProductStockService productStockService;

    public ProductStockController(ProductStockService productStockService) {
        this.productStockService = productStockService;
    }

    public void addNewStock(ProductType productType, BigDecimal extracted, BigDecimal sold) {
        productStockService.addNewStock(productType, extracted, sold);
        System.out.println("New stock added");
    }

    public void updateStock(ProductType productType, BigDecimal extracted, BigDecimal sold) {
        productStockService.updateStock(productType, extracted, sold);
        System.out.println("Stock updated");
    }

    public void showStock(ProductType productType) {
        BigDecimal[] stock = productStockService.getStockByProduct(productType);
        System.out.println("Stock for " + productType + ": Extracted = " + stock[0] + ", Sold = " + stock[1]);
    }
}
