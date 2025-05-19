package com.example.beekeeperpro.product.controller;

import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.product.service.ProductFinanceService;

import java.math.BigDecimal;
import java.util.List;

public class ProductFinanceController {

    private final ProductFinanceService productFinanceService;

    public ProductFinanceController(ProductFinanceService productFinanceService) {
        this.productFinanceService = productFinanceService;
    }

    public void addFinanceRecord(int beehiveId, ProductType productType, BigDecimal extractedKg, BigDecimal soldKg) {
        productFinanceService.addFinanceRecord(beehiveId, productType, extractedKg, soldKg);
        System.out.println("Finance record added (by ID).");
    }

    public void addFinanceRecordByNumber(int beehiveNumber, ProductType productType, BigDecimal extractedKg, BigDecimal soldKg) {
        productFinanceService.addFinanceRecordByBeehiveNumber(beehiveNumber, productType, extractedKg, soldKg);
        System.out.println("Finance record added (by number).");
    }

    public void updateSoldKg(int financeId, BigDecimal newSoldKg) {
        productFinanceService.updateSoldKg(financeId, newSoldKg);
        System.out.println("Finance record updated for Finance ID: " + financeId);
    }

    public void showFinanceForBeehive(int beehiveNumber) {
        List<Object[]> list = productFinanceService.getFinanceByBeehiveNumber(beehiveNumber);
        System.out.println("Finance records for beehive number: " + beehiveNumber + ":");
        list.forEach(entry -> System.out.println(
                "ID: " + entry[0] +
                        ", Type: " + entry[1] +
                        ", Extracted Kg: " + entry[2] +
                        ", Sold Kg: " + entry[3] +
                        ", Date: " + entry[4]
        ));
    }

    public void showFinanceByApiary(String apiaryName) {
        List<Object[]> list = productFinanceService.getFinanceByApiaryName(apiaryName);
        System.out.println("Finance records for apiary: " + apiaryName + ":");
        list.forEach(entry -> System.out.println(
                "ID: " + entry[0] +
                        ", Type: " + entry[1] +
                        ", Extracted: " + entry[2] +
                        ", Sold: " + entry[3] +
                        ", Date: " + entry[4]
        ));
    }
}
