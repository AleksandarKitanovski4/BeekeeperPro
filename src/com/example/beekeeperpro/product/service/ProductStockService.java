package com.example.beekeeperpro.product.service;

import com.example.beekeeperpro.product.enums.ProductType;
import com.example.beekeeperpro.product.repository.ProductStockRepository;

import java.math.BigDecimal;
import java.util.Map;

public class ProductStockService {

    private final ProductStockRepository stockRepository;

    public ProductStockService(ProductStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void addNewStock(ProductType productType, BigDecimal newTotalExtracted, BigDecimal newTotalSold) {
        stockRepository.insertStock(productType, newTotalExtracted, newTotalSold);
    }

    public void updateStock(ProductType productType, BigDecimal newTotalExtracted, BigDecimal newTotalSold) {
        stockRepository.updateStock(productType, newTotalExtracted, newTotalSold);
    }

    public void reduceStock(ProductType productType, BigDecimal quantity) {
        stockRepository.reduceStock(productType, quantity);
    }

    public BigDecimal[] getStockByProduct(ProductType productType) {
        return stockRepository.getStockByProductType(productType);
    }

    public Map<ProductType, BigDecimal[]> getAllStock() {
        return stockRepository.getAllStock();
    }
}
