package com.example.beekeeperpro.product.service;

import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.product.repository.ProductFinanceRepository;

import java.math.BigDecimal;
import java.util.List;

public class ProductFinanceService {

    private final ProductFinanceRepository financeRepository;

    public ProductFinanceService(ProductFinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    public void addFinanceRecord(int beehiveId, ProductType productType, BigDecimal extractedKg, BigDecimal soldKg) {
        financeRepository.insertFinance(beehiveId, productType, extractedKg, soldKg);
    }

    public void addFinanceRecordByBeehiveNumber(int beehiveNumber, ProductType productType, BigDecimal extractedKg, BigDecimal soldKg) {
        financeRepository.insertFinanceByBeehiveNumber(beehiveNumber, productType, extractedKg, soldKg);
    }

    public void updateSoldKg(int financeId, BigDecimal soldKg) {
        financeRepository.updateSoldKg(financeId, soldKg);
    }

    public List<Object[]> getFinanceByBeehiveNumber(int beehiveNumber) {
        return financeRepository.getFinanceByBeehiveNumber(beehiveNumber);
    }

    public List<Object[]> getFinanceByApiaryName(String apiaryName) {
        return financeRepository.getFinanceByApiaryName(apiaryName);
    }
}
